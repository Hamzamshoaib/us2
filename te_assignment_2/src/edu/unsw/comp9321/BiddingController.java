package edu.unsw.comp9321;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


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
			String strQuery = "select MAX(BidPrice) FROM cast_db.BiddingPrice where Item_ID = " + Item_ID;
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(strQuery);
			int winningBid = -1;
			
			while (rs.next()){
				String bidString = rs.getString(1);
				winningBid = Integer.parseInt(bidString);
				System.out.println(winningBid);
			}

			rs.close();
			st.close();
			
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
	
}
