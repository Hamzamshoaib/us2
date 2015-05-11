package edu.unsw.comp9321;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ItemResult
 */
public class ItemResult extends HttpServlet {
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
		// TODO Auto-generated method stub
		String name = (String) request.getSession().getAttribute("username");
		if (name == null){
			response.sendRedirect("index.jsp");
			request.getSession().invalidate();
		}
		String search = request.getParameter("searchItem");

		
		Connection conn = null;
		String url = "jdbc:derby://localhost:1527/cast;create=true";
		String dbUserName = "cast_db";
		String dbPassword = "hamza";
		String driver = "org.apache.derby.jdbc.ClientDriver";
		
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url, dbUserName, dbPassword);
			String strQuery = "select * from cast_db.Items where Name = '" + search  +"' OR Category='" + search + "'OR Description LIKE '%" + search + "%'"  ;
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(strQuery);
			while (rs.next()) {
				System.out.println("Title = " + rs.getString("Name"));
				//System.out.println("searched for " + search );
			}
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