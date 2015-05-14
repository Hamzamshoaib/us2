package edu.unsw.comp9321;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GetUserDetails {

	Connection conn;
	String url;
	String dbUserName;
	String dbPassword;
	String driver;
	//
	public GetUserDetails(){
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
	
	
	public String getPassword(String username) {
		String password = new String();

		try
		{

			String strQuery = "select Password FROM cast_db.Users where UserName=?";
			PreparedStatement ps = conn.prepareStatement(strQuery);
			ps.setString(1,username);

			ResultSet rs = ps.executeQuery();
			rs.next();
			password = rs.getString(1);

			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return password;

	}
	
	public String getFirstName(String username) {
		String firstName = new String();

		try
		{

			String strQuery = "select FirstName FROM cast_db.Users where UserName=?";
			PreparedStatement ps = conn.prepareStatement(strQuery);
			ps.setString(1,username);

			ResultSet rs = ps.executeQuery();
			rs.next();
			firstName = rs.getString(1);

			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return firstName;

	}
	
	public String getLastName(String username) {
		String lastName = new String();

		try
		{

			String strQuery = "select LastName FROM cast_db.Users where UserName=?";
			PreparedStatement ps = conn.prepareStatement(strQuery);
			ps.setString(1,username);

			ResultSet rs = ps.executeQuery();
			rs.next();
			lastName = rs.getString(1);

			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lastName;
		
	}
	
	public String getAddress(String username) {
		String address = new String();

		try
		{

			String strQuery = "select Address FROM cast_db.Users where UserName=?";
			PreparedStatement ps = conn.prepareStatement(strQuery);
			ps.setString(1,username);

			ResultSet rs = ps.executeQuery();
			rs.next();
			address = rs.getString(1);

			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return address;
		
	}
	

}