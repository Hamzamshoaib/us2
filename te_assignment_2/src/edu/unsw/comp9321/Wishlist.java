package edu.unsw.comp9321;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
		String itemID;
		int Item_ID = 0;
		if ((itemID = request.getParameter("id")) != null){
			Item_ID =  Integer.parseInt(itemID);
		}
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
			
			ArrayList<String> itemNames = new ArrayList<String>();
			ArrayList<Integer> itemIDs = new ArrayList<Integer>();

			BiddingController bc = new BiddingController();
			
			strQuery = "SELECT Item_ID FROM cast_db.WishList WHERE UserName=?";
			PreparedStatement ps = conn.prepareStatement(strQuery);
			ps.setString(1,user);
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
				String itemString = rs.getString(1);
				if(itemString != null){
					int item = Integer.parseInt(itemString);
					itemIDs.add(item);
					itemNames.add(bc.getItemName(item));
				} else {
					break;
				}
			}
			

			
			if ("Add to Wishlist".equals(formDelegate) && !itemIDs.contains(Item_ID))
			{
				strQuery = "INSERT INTO cast_db.WishList VALUES ('"+ user +"',"+ Item_ID +")";
				st.executeUpdate(strQuery);
				itemIDs.add(Item_ID);
				itemNames.add(bc.getItemName(Item_ID));
				
			}
			else if ("Delete".equals(formDelegate))
			{
				strQuery = "DELETE FROM cast_db.WishList WHERE Item_ID = "+ Item_ID +" and UserName = '"+ user +"'";
				st.executeUpdate(strQuery);
				
				int index = itemIDs.indexOf(Item_ID);
				itemNames.remove(index);
				itemIDs.remove(index);
				
			} 
			
			rs.close();
			ps.close();
			
			request.setAttribute("itemNames", itemNames);
			request.setAttribute("itemIDs", itemIDs);
			request.getRequestDispatcher("wishlist.jsp").forward(request, response);
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