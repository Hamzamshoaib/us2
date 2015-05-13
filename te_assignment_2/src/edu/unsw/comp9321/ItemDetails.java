package edu.unsw.comp9321;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ItemDetails
 */
public class ItemDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ItemDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String name = (String) request.getSession().getAttribute("username");
		if (name == null){
			response.sendRedirect("index.jsp");
			request.getSession().invalidate();
		}
		int Item_ID =  Integer.parseInt(request.getParameter("id"));
		
//		//sets new bid price if there is a bid made
//		if (request.getParameter("bid") != null)
//		{
//			System.out.println(request.getParameter("bid"));
//			request.setAttribute("newBid", request.getParameter("bids"));
//		}
		
		Connection conn = null;
		String url = "jdbc:derby://localhost:1527/cast;create=true";
		String dbUserName = "test";
		String dbPassword = "test";
		String driver = "org.apache.derby.jdbc.ClientDriver";
		
		try
		{
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url, dbUserName, dbPassword);
			/*
			Order of Item details
			Name
			Picture
			Description
			Category
			Owner
			EndTime
			ReservePrice
			*/
			String strQuery = "select Name, Picture, Description, Category, Owner, EndTime, ReservePrice, Item_ID, StartingPrice FROM cast_db.Items where Item_ID = " + Item_ID;
			//System.out.println(strQuery);
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(strQuery);
			ArrayList<ArrayList<String>> table = new ArrayList<ArrayList<String>>();
			while (rs.next())
			{
				ArrayList<String> columns = new ArrayList<String>();
				columns.add(rs.getString(1));
				columns.add(rs.getString(2));
				columns.add(rs.getString(3));
				columns.add(rs.getString(4));
				columns.add(rs.getString(5));
				columns.add(rs.getString(6));
				columns.add(Integer.toString(rs.getInt(7)));
				columns.add(Integer.toString(rs.getInt(8)));
				columns.add(Integer.toString(rs.getInt(9)));
				table.add(columns);
			}
			request.setAttribute("table", table);
			request.getRequestDispatcher("itemdetails.jsp").forward(request, response);
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
