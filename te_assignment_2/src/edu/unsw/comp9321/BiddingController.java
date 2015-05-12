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
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url, dbUserName, dbPassword);
			
			int winningBid = -1;
			String strQuery = "select MAX(BidPrice) FROM cast_db.BiddingPrice where Item_ID=?";
			PreparedStatement ps = conn.prepareStatement(strQuery);
			ps.setInt(1,Item_ID);
			
			ResultSet rs = ps.executeQuery();

			
			while (rs.next()){
				String bidString = rs.getString(1);
				winningBid = Integer.parseInt(bidString);
			}

			rs.close();
			
			return winningBid;
			
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	//Returns the highest bidder for any item
	public String winningBidder(int Item_ID){
		try
		{
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url, dbUserName, dbPassword);
			
			String strQuery = "select UserName from cast_db.BiddingPrice where BidPrice=(select MAX(BidPrice) from cast_db.BiddingPrice where Item_ID=?)";
			PreparedStatement ps = conn.prepareStatement(strQuery);
			ps.setInt(1,Item_ID);
			
			ResultSet rs = ps.executeQuery();
			rs.next();
			String userString = rs.getString(1);
			rs.close();
			ps.close();
			
			return userString;
			
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//Returns 1 if success
	//Returns 0 if bid is less than current bid + increment
	//Returns -1 if bid is 0 or less
	//Returns -2 if some other failure
	public int placeBid(String user, int Item_ID, int bid){
		//Bids MUST be greater than 0
		if (bid <= 0){
			return -1;
		}
		
		try
		{
			int success = 1;
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url, dbUserName, dbPassword);
			
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
			return success; 
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return -1; //return error
	}
	
	public ArrayList<String> listOfLosers(int Item_ID){
		ArrayList<String> losers = new ArrayList<String>();
		
		try
		{
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url, dbUserName, dbPassword);
			
			String strQuery = "select UserName from cast_db.BiddingPrice where BidPrice!=(select MAX(BidPrice) from cast_db.BiddingPrice where Item_ID=?)";
			PreparedStatement ps = conn.prepareStatement(strQuery);
			ps.setInt(1,Item_ID);
			ResultSet rs = ps.executeQuery();

			while (rs.next()){
				losers.add(rs.getString(1));
			}
			
			rs.close();
			ps.close();
			
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return losers;
	}
	
	public String loser(int Item_ID){
		String loser = new String();
		
		try
		{
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url, dbUserName, dbPassword);
			
			String strQuery = "select UserName from cast_db.BiddingPrice where BidPrice=(select MAX(BidPrice) from cast_db.BiddingPrice where BidPrice < (select MAX(BidPrice) from cast_db.BiddingPrice where Item_ID=?))";
			PreparedStatement ps = conn.prepareStatement(strQuery);
			ps.setInt(1,Item_ID);
			ResultSet rs = ps.executeQuery();

			rs.next();
			loser = rs.getString(1);
			
			rs.close();
			ps.close();
			
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return loser;
	}
	
	public ArrayList<Integer> getDoneAuctions(){
		ArrayList<Integer> doneItems = new ArrayList<Integer>();
		try
		{
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url, dbUserName, dbPassword);
			
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
			
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return doneItems;
	}
	
}
