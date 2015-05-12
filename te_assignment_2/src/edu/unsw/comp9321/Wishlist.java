package edu.unsw.comp9321;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Wishlist
 */
public class Wishlist extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Wishlist() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		//String formDelegate = request.getParameter("value");
		//String Item_ID =  request.getParameter("Add to Wishlist");
		String user = (String) request.getSession().getAttribute("username");
		String formDelegate = request.getParameter("action");
		int Item_ID =  Integer.parseInt(request.getParameter("id"));
		
		Connection conn = null;
		String url = "jdbc:derby://localhost:1527/cast;create=true";
		String dbUserName = "test";
		String dbPassword = "test";
		String driver = "org.apache.derby.jdbc.ClientDriver";
		
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url, dbUserName, dbPassword);
			Statement st = conn.createStatement();
			String strQuery = new String();
			if ("Add to Wishlist".equals(formDelegate))
			{
				strQuery = "INSERT INTO cast_db.WishList VALUES ('"+ user +"',"+ Item_ID +")";
				st.executeUpdate(strQuery);
			}
			else if ("Delete".equals(formDelegate))
			{
				strQuery = "DELETE FROM cast_db.WishList WHERE Item_ID = "+ Item_ID +" and UserName = '"+ user +"'";
				st.executeUpdate(strQuery);
			}
			//System.out.println(strQuery);
			
			strQuery = "SELECT cast_db.Items.Name, cast_db.WishList.Item_ID FROM cast_db.WishList JOIN cast_db.Items ON "
					+ "cast_db.WishList.UserName = '"+ user +"' AND cast_db.Items.Owner = '"+ user +"' AND cast_db.WishList.Item_ID = cast_db.Items.Item_ID";
			System.out.println(strQuery);
			ResultSet rs = st.executeQuery(strQuery);
			ArrayList<ArrayList<String>> table = new ArrayList<ArrayList<String>>();
			while (rs.next())
			{
				ArrayList<String> columns = new ArrayList<String>();
				String itemName = rs.getString(1);
				String itemID = Integer.toString(rs.getInt(2));
				columns.add(itemName);
				columns.add(itemID);
				table.add(columns);
			}
			request.setAttribute("table", table);
			request.getRequestDispatcher("wishlist.jsp").forward(request, response);
			rs.close();
			st.close();
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

}