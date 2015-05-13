package edu.unsw.comp9321;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class AdminController {
	
	Connection conn;
	String url;
	String dbUserName;
	String dbPassword;
	String driver;
	
	public AdminController(){
		conn = null;
		url = "jdbc:derby://localhost:1527/cast;create=true";
		dbUserName = "cast_db";
		dbPassword = "hamza";
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
			Statement st = conn.createStatement();
			String strQuery = new String();
			strQuery = "update cast_db.Users SET Verified='blocked'  WHERE UserName='" + username + "'";
			st.executeUpdate(strQuery);
			st.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void unbanUser(String username){
		
		try
		{
			Statement st = conn.createStatement();
			String strQuery = new String();
			strQuery = "update cast_db.Users SET Verified='verified'  WHERE UserName='" + username + "'";
			st.executeUpdate(strQuery);
			st.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
//
}
