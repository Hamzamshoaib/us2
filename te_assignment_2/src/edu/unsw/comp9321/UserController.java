package edu.unsw.comp9321;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserController {
	
	//Making the assumption that all items have the same bidding increment	
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
	}
	
	//Given the username, returns the email of the user
	public String getEmail(String username){
		String email = new String();
		
		try
		{
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url, dbUserName, dbPassword);
			
			String strQuery = "select Email FROM cast_db.Users where UserName=?";
			PreparedStatement ps = conn.prepareStatement(strQuery);
			ps.setString(1,username);
			
			ResultSet rs = ps.executeQuery();
			rs.next();
			email = rs.getString(1);

			rs.close();
			
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url, dbUserName, dbPassword);
			
			String strQuery = "select Name FROM cast_db.Items where Item_ID=?";
			PreparedStatement ps = conn.prepareStatement(strQuery);
			ps.setInt(1,Item_ID);
			
			ResultSet rs = ps.executeQuery();
			rs.next();
			itemName = rs.getString(1);

			rs.close();
			
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return itemName;
	}
}
	