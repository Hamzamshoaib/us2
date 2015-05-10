package edu.unsw.comp9321;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddItem
 */
public class AddItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddItem() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		String owner = (String) request.getSession().getAttribute("username");
		String picture = request.getParameter("picture");
		String resprice = request.getParameter("reserveprice");
		String startprice = request.getParameter("startingprice");
		String duration = request.getParameter("duration");
		String address = request.getParameter("address");
		String itemid = "NULL";
		String incre = "NULL";
		
		String msg = " ";
		Connection conn = null;
		String url = "jdbc:derby://localhost:1527/cast;create=true";
		String dbUserName = "cast_db";
		String dbPassword = "hamza";
		String driver = "org.apache.derby.jdbc.ClientDriver";
		System.out.println("connecting to database");
			try {
				Class.forName(driver).newInstance();
				conn = DriverManager.getConnection(url, dbUserName, dbPassword);
				Statement st = conn.createStatement();
				//String strQuery = "select * from Users where UserName='" + un +"' and  Password='" + pw + "'";
				String strQuery = "INSERT INTO cast_db.Items(Name, Owner, Description, Picture, ReservePrice, StartingPrice, Duration, Address) "
						+ "VALUES('" + title +"','" + owner + "','" + description + "','" 
						+ picture + "'," + resprice + "," + startprice + "," + duration + ",'" + address + "')";
				System.out.println(strQuery);
				st.executeUpdate(strQuery);
				System.out.println("did something");
				/*ResultSet rs = st.executeQuery(strQuery);
				if (rs.next()) {
					/*msg="Hello " + un + "! Your login is successful";
					HttpSession session = request.getSession(true);
					session.setAttribute("username", un);
					response.sendRedirect("welcome.jsp");
				} else {
					request.setAttribute("message", "Invalid Username or Password");
					request.getRequestDispatcher("index.jsp").forward(request, response);
					/*msg="Hello Your login failed";
				}*/
				//rs.close();
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
