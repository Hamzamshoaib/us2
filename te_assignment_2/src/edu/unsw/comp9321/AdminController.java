package edu.unsw.comp9321;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AdminController {
	
	Connection conn;
	String url;
	String dbUserName;
	String dbPassword;
	String driver;
	
	public AdminController(){
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
	
	//Bans user given username
	public void banUser(String username){
		
		try
		{
			String strQuery = "update cast_db.Users SET Verified=?  WHERE UserName=?";
			PreparedStatement ps = conn.prepareStatement(strQuery);
			String banned = "blocked";
			ps.setString(1,banned);
			ps.setString(2,username);
			
			ps.executeUpdate();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void unbanUser(String username){
		
		try
		{
			String strQuery = "update cast_db.Users SET Verified=?  WHERE UserName=?";
			PreparedStatement ps = conn.prepareStatement(strQuery);
			String unban = "verified";
			ps.setString(1,unban);
			ps.setString(2,username);
			
			ps.executeUpdate();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

}
