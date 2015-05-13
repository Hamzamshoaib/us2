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
	
	
	
	//get list of users
	//get everyone's status
	
	public ArrayList<String> getAllUsernames(){
		ArrayList<String> allUsers = new ArrayList<String>();
		
		try
		{
			String strQuery = "select UserName FROM cast_db.Username";
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
	
	//Returns 1 if blocked
	//Returns 0 if verified
	//Returns -1 if unverified
	public int isBlocked(String username){
		int isBlocked = -1;
		
		try
		{
			String strQuery = "select Verified FROM cast_db.Users where UserName=?";
			PreparedStatement ps = conn.prepareStatement(strQuery);
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			String verField = rs.getString(1);
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
	
}
	