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
 * Servlet implementation class Register
 */
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
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
		String un = request.getParameter("username");
		String pw = request.getParameter("password");
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String nickname = request.getParameter("nickname");
		String email = request.getParameter("email");
		String birthyear = request.getParameter("birthyear");
		String address = request.getParameter("address");
		String creditcard = request.getParameter("creditcard");
		
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
				String strQuery = "INSERT INTO cast_db.Users VALUES('" + un +"','" + pw + "','" + firstname + "','" + lastname + "','" + email + "','" + address + "','" + "1993-02-24" + "')";
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
