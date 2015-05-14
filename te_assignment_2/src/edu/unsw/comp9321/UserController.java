package edu.unsw.comp9321;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserController {
	
	Connection conn;
	String url;
	String dbUserName;
	String dbPassword;
	String driver;
	//
	public UserController(){
		conn = null;
		url = "jdbc:derby://localhost:1527/cast;create=true";
		dbUserName = "user";
		dbPassword = "test";
		driver = "org.apache.derby.jdbc.ClientDriver";

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
	
	//Given the username, returns the email of the user
	public String getEmail(String username){
		String email = new String();
		
		try
		{
			
			String strQuery = "select Email FROM cast_db.Users where UserName=?";
			PreparedStatement ps = conn.prepareStatement(strQuery);
			ps.setString(1,username);
			
			ResultSet rs = ps.executeQuery();
			rs.next();
			email = rs.getString(1);

			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return email;
	}
	
	//Given Item_ID, returns the itemName
	public String getItemName(int Item_ID){
		String itemName = new String();
		
		try
		{
			String strQuery = "select Name FROM cast_db.Items where Item_ID=?";
			PreparedStatement ps = conn.prepareStatement(strQuery);
			ps.setInt(1,Item_ID);
			
			ResultSet rs = ps.executeQuery();
			rs.next();
			itemName = rs.getString(1);

			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return itemName;
	}
	
	public String geturl(int item_ID){
		String itemurl = new String();
		
		try
		{
			
			String strQuery = "select Picture FROM cast_db.Items where Item_ID=?";
			PreparedStatement ps = conn.prepareStatement(strQuery);
			ps.setInt(1,item_ID);
			
			ResultSet rs = ps.executeQuery();
			rs.next();
			itemurl = rs.getString(1);

			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return itemurl;
	}
	
	//get list of users
	//get everyone's status
	
	public ArrayList<String> getAllUsernames(){
		ArrayList<String> allUsers = new ArrayList<String>();
		
		try
		{
			String strQuery = "select UserName FROM cast_db.Users";
			PreparedStatement ps = conn.prepareStatement(strQuery);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()){
				allUsers.add(rs.getString(1));
			}

			rs.close();
			ps.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allUsers;
	}
	
	public ArrayList<Integer> getAllItems(){
		ArrayList<Integer> allItems = new ArrayList<Integer>();
		
		try
		{
			String strQuery = "select Item_ID FROM cast_db.Items";
			PreparedStatement ps = conn.prepareStatement(strQuery);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next() && (rs.getInt(1) != 0)){
				allItems.add(rs.getInt(1));
			}

			rs.close();
			ps.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allItems;
		
	}

	//Returns 1 if blocked
	//Returns 0 if verified
	//Returns -1 if unverified
	public int isBlocked(String username){
		int isBlocked = -1;
		try
		{
			String strQuery = "select Verified FROM cast_db.Users where UserName='" + username + "'";
			PreparedStatement ps = conn.prepareStatement(strQuery);
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			String verField = rs.getString("Verified");
			if (verField.equals("blocked")){
				isBlocked = 1;
			} else if (verField.equals("verified")){
				isBlocked = 0;
			}
			
			rs.close();
			ps.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isBlocked;
	}

	public String getItemOwner(int Item_ID){
		String itemOwner = new String();
		
		try
		{
			String strQuery = "select Owner FROM cast_db.Items where Item_ID=?";
			PreparedStatement ps = conn.prepareStatement(strQuery);
			ps.setInt(1,Item_ID);
			
			ResultSet rs = ps.executeQuery();
			rs.next();
			itemOwner = rs.getString(1);

			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return itemOwner;
	}
	
	public Boolean updatePassword(String username, String password){
		Boolean done = false;
		
		try
		{
			String strQuery = "update cast_db.Users SET Password=? WHERE UserName=?";
			PreparedStatement ps = conn.prepareStatement(strQuery);
			ps.setString(1,password);
			ps.setString(2,username);
			ps.executeUpdate();
			
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return done;
	}
	
	public Boolean updateFirstName(String username, String firstName){
		Boolean done = false;
		
		try
		{
			String strQuery = "update cast_db.Users SET FirstName=? WHERE UserName=?";
			PreparedStatement ps = conn.prepareStatement(strQuery);
			ps.setString(1,firstName);
			ps.setString(2,username);
			ps.executeUpdate();
			
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return done;
	}
	
	public Boolean updateLastName(String username, String lastName){
		Boolean done = false;
		
		try
		{
			String strQuery = "update cast_db.Users SET LastName=? WHERE UserName=?";
			PreparedStatement ps = conn.prepareStatement(strQuery);
			ps.setString(1,lastName);
			ps.setString(2,username);
			ps.executeUpdate();
			
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return done;
	}
	
	public Boolean updateEmail(String username, String email){
		Boolean done = false;
		
		try
		{
			String strQuery = "update cast_db.Users SET Email=? WHERE UserName=?";
			PreparedStatement ps = conn.prepareStatement(strQuery);
			ps.setString(1,email);
			ps.setString(2,username);
			ps.executeUpdate();
			
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return done;
	}
	
	public Boolean updateAddress(String username, String address){
		Boolean done = false;
		
		try
		{
			String strQuery = "update cast_db.Users SET Address=? WHERE UserName=?";
			PreparedStatement ps = conn.prepareStatement(strQuery);
			ps.setString(1,address);
			ps.setString(2,username);
			ps.executeUpdate();
			
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return done;
	}
	
	public int addItem(String name, String owner, String desc, String category, String pic, String resp, String startp, String duration, String address){
		int itemID = 0;
		
		try
		{
			String strQuery = "insert into cast_db.Items values (DEFAULT,?,?,?,?,?,?,?,?,DEFAULT,?)";
			PreparedStatement ps = conn.prepareStatement(strQuery,PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1,name);
			ps.setString(2,owner);
			ps.setString(3, desc);
			ps.setString(4,category);
			ps.setString(5,pic);
			ps.setInt(6,Integer.parseInt(resp));
			ps.setInt(7,Integer.parseInt(startp));
			ps.setString(8, TimeController.setAuctTimer(Integer.parseInt(duration)));
			ps.setString(9,address);
			
			ps.execute();
			
			ResultSet rs = ps.getGeneratedKeys();  
		    rs.next();  
			itemID = rs.getInt(1);  
			
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return itemID;
		
	}
	
}
	