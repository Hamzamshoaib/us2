package edu.unsw.comp9321;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ItemResult
 */
public class ItemResult extends HttpServlet
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ItemResult() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = (String) request.getSession().getAttribute("username");
		if (name == null){
			response.sendRedirect("index.jsp");
			request.getSession().invalidate();
		}
		String search = request.getParameter("searchItem");
		//System.out.println(search);
		
		Connection conn = null;
		String url = "jdbc:derby://localhost:1527/cast;create=true";
		String dbUserName = "test";
		String dbPassword = "test";
		String driver = "org.apache.derby.jdbc.ClientDriver";
		
		try
		{
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url, dbUserName, dbPassword);
			String strQuery = "select Name, Picture, Item_ID FROM cast_db.Items where Name = '" + search  +"' OR Category='" + search + "'OR Description LIKE '%" + search + "%'";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(strQuery);
			ArrayList<ArrayList<String>> table = new ArrayList<ArrayList<String>>();
			while (rs.next())
			{
				ArrayList<String> columns = new ArrayList<String>();
				String itemName = rs.getString(1);
				String picture = rs.getString(2);
				String itemID = Integer.toString(rs.getInt(3));
				columns.add(picture);
				columns.add(itemName);
				columns.add(itemID);
				table.add(columns);
			}
			request.setAttribute("table", table);
			request.getRequestDispatcher("searchList.jsp").forward(request, response);
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


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
