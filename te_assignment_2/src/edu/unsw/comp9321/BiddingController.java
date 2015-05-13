package edu.unsw.comp9321;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BiddingController {
	
	//Making the assumption that all items have the same bidding increment	
	int increment;
	Connection conn;
	String url;
	String dbUserName;
	String dbPassword;
	String driver;
	
	//Create class by setting the increment
	public BiddingController(){
		this(1);
	}
	
	public BiddingController(int increment){
		conn = null;
		url = "jdbc:derby://localhost:1527/cast;create=true";
		dbUserName = "user";
		dbPassword = "test";
		driver = "org.apache.derby.jdbc.ClientDriver";
		this.increment = increment;
		
		try
		{
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url, dbUserName, dbPassword);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public float getIncrement(){
		return this.increment;
	}
	
	//Returns current winning bid
	//Returns -1 if error
	public int currentWinningBid(int Item_ID){
		try
		{
			int winningBid = -1;
			String strQuery = "select MAX(BidPrice) FROM cast_db.BiddingPrice where Item_ID=?";
			PreparedStatement ps = conn.prepareStatement(strQuery);
			ps.setInt(1,Item_ID);
			
			ResultSet rs = ps.executeQuery();

			
			while (rs.next()){
				String bidString = rs.getString(1);
				if(bidString == null){
					winningBid = getStartingPrice(Item_ID);
				} else {
					winningBid = Integer.parseInt(bidString);
				}
			}

			rs.close();
			ps.close();
			
			return winningBid;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	//Returns the highest bidder for any item
	public String winningBidder(int Item_ID){
		try
		{
			String strQuery = "select UserName from cast_db.BiddingPrice where BidPrice=(select MAX(BidPrice) from cast_db.BiddingPrice where Item_ID=?)";
			PreparedStatement ps = conn.prepareStatement(strQuery);
			ps.setInt(1,Item_ID);
			
			ResultSet rs = ps.executeQuery();
			rs.next();
			String userString = rs.getString(1);
			rs.close();
			ps.close();
			
			return userString;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//Returns 1 if success
	//Returns 0 if bid is less than current bid + increment
	//Returns -1 if bid is 0 or less
	//Returns -2 if some other failure
	//Returns -3 if auction is already done
	//Returns -4 if auction is halted
	public int placeBid(String user, int Item_ID, int bid){
		//Bids MUST be greater than 0
		if (bid <= 0){
			return -1;
		}
		//can't bid if auction is done
		if(isAuctionDone(Item_ID)){
			return -3;
		}
		//Ensure item isn't halted
		if(isHalted(Item_ID)){
			return -4;
		}
		
		try
		{
			int success = 1;

			//Check whether bid is valid
			String strQuery = "select MAX(BidPrice) FROM cast_db.BiddingPrice where Item_ID =?";
			PreparedStatement ps = conn.prepareStatement(strQuery);
			ps.setInt(1,Item_ID);
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			String bidString = rs.getString(1);
			int highestBid = Integer.parseInt(bidString);
			rs.close();
			
			//If bid is valid then update, otherwise return 0
			if (bid >= (highestBid + increment)){
				strQuery = "update cast_db.BiddingPrice SET BidPrice=?  WHERE Item_ID =? AND UserName=?";
				
				ps = conn.prepareStatement(strQuery);
				ps.setInt(1,bid);
				ps.setInt(2,Item_ID);
				ps.setString(3, user);
				ps.executeUpdate();
			} else {
				success = 0;
			}
			
			ps.close();
			Email email = new Email();
			email.newBidEmails(Item_ID);
			return success; 
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1; //return error
	}
	
	public ArrayList<String> listOfLosers(int Item_ID){
		ArrayList<String> losers = new ArrayList<String>();
		
		try
		{
			String strQuery = "select UserName from cast_db.BiddingPrice where BidPrice!=(select MAX(BidPrice) from cast_db.BiddingPrice where Item_ID=?)";
			PreparedStatement ps = conn.prepareStatement(strQuery);
			ps.setInt(1,Item_ID);
			ResultSet rs = ps.executeQuery();

			while (rs.next()){
				losers.add(rs.getString(1));
			}
			
			rs.close();
			ps.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return losers;
	}
	
	public String loser(int Item_ID){
		String loser = new String();
		
		try
		{
			String strQuery = "select UserName from cast_db.BiddingPrice where BidPrice=(select MAX(BidPrice) from cast_db.BiddingPrice where BidPrice < (select MAX(BidPrice) from cast_db.BiddingPrice where Item_ID=?))";
			PreparedStatement ps = conn.prepareStatement(strQuery);
			ps.setInt(1,Item_ID);
			ResultSet rs = ps.executeQuery();

			rs.next();
			loser = rs.getString(1);
			
			rs.close();
			ps.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return loser;
	}
	
	public ArrayList<Integer> getDoneAuctions(){
		ArrayList<Integer> doneItems = new ArrayList<Integer>();
		try
		{
			String strQuery = "select Item_ID, EndTime from cast_db.Items";
			PreparedStatement ps = conn.prepareStatement(strQuery);
			ResultSet rs = ps.executeQuery();
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
			Date rightNow = new Date();
			
			while (rs.next()){
				Date endTime = dateFormat.parse(rs.getString(2));
				if (endTime.before(rightNow)){
					doneItems.add(Integer.parseInt(rs.getString(1)));
				}
			}
			
			rs.close();
			ps.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return doneItems;
	}
	
	public Boolean isAuctionDone(int Item_ID){
		Boolean ret = false;
		
		try
		{
			String strQuery = "select Item_ID, EndTime from cast_db.Items where Item_ID=?";
			
			PreparedStatement ps = conn.prepareStatement(strQuery);
			ResultSet rs = ps.executeQuery();
			ps.setInt(1,Item_ID);

			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
			Date rightNow = new Date();
			
			rs.next();
			Date endTime = dateFormat.parse(rs.getString(2));
			if (endTime.before(rightNow)){
				ret = true;
			}
			
			rs.close();
			ps.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return ret;
	}
	
	//get done auctions
	//for auctions with winning bid less than reserved price -> email owner with 2 bids
	
	public Boolean bidLessThanReserved(int Item_ID){
		return (currentWinningBid(Item_ID) < getReservePrice(Item_ID));
	}
	
	public int getReservePrice(int Item_ID){
		int retVal = 0;
		try
		{
			String strQuery = "select ReservePrice from cast_db.Items where Item_ID=?";
			PreparedStatement ps = conn.prepareStatement(strQuery);
			ResultSet rs = ps.executeQuery();
			ps.setInt(1,Item_ID);

			rs.next();
			retVal = Integer.parseInt(rs.getString(1));
			
			rs.close();
			ps.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retVal;
	}
	
	public int getStartingPrice(int Item_ID){
		int retVal = 0;
		try
		{
			String strQuery = "select StartingPrice from cast_db.Items where Item_ID=?";
			PreparedStatement ps = conn.prepareStatement(strQuery);
			ResultSet rs = ps.executeQuery();
			ps.setInt(1,Item_ID);

			rs.next();
			retVal = Integer.parseInt(rs.getString(1));
			
			rs.close();
			ps.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retVal;
	}
	
	public Boolean haltAuction(int Item_ID){
		Boolean done = false;
		
		try
		{
			String strQuery = "update cast_db.Halted SET Halt=?  WHERE Item_ID=?";
			PreparedStatement ps = conn.prepareStatement(strQuery);
			ps.setInt(1,1);
			ps.setInt(2,Item_ID);
			ps.executeUpdate();
			
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return done;
	}
	
	public Boolean isHalted(int Item_ID){
		Boolean isHalted = false;
		
		try
		{
			String strQuery = "select Halt from cast_db.Halted where Item_ID=?";
			PreparedStatement ps = conn.prepareStatement(strQuery);
			ResultSet rs = ps.executeQuery();
			ps.setInt(1,Item_ID);

			rs.next();
			isHalted = (Integer.parseInt(rs.getString(1)) == 1) ? true : false;
			
			rs.close();
			ps.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return isHalted;
	}
	
	public Boolean deleteItem(int Item_ID){
		Boolean done = false;
		
		//Delete item from wishlist
		//Delete Item from BiddingItem
		//Delete Item from Item_ID
		
		try
		{
			String strQuery = "DELETE FROM cast_db.Halted WHERE Item_ID=?";
			PreparedStatement ps = conn.prepareStatement(strQuery);
			ps.setInt(1,Item_ID);
			ps.execute();
			
			strQuery = "DELETE FROM cast_db.WishList WHERE Item_ID=?";
			ps = conn.prepareStatement(strQuery);
			ps.setInt(1,Item_ID);
			ps.execute();
			
			strQuery = "DELETE FROM cast_db.BiddingPrice WHERE Item_ID=?";
			ps = conn.prepareStatement(strQuery);
			ps.setInt(1,Item_ID);
			ps.execute();
			
			strQuery = "DELETE FROM cast_db.Items WHERE Item_ID=?";
			ps = conn.prepareStatement(strQuery);
			ps.setInt(1,Item_ID);
			ps.execute();
			
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return done;
	}
	
	public void acceptOffer(){
		//send email of winning
		//delete item
	}
	
}