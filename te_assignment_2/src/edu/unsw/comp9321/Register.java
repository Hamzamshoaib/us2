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
		String session = (String)request.getSession().getId();
		request.getSession().invalidate();
		
		if (!InputCheck.isLetters(firstname)) {
			request.setAttribute("message", "FirstName Can Only Contain Letters");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		else if (!InputCheck.isLetters(lastname)) {
			request.setAttribute("message", "LastName Can Only Contain Letters");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		else if (!InputCheck.isLetters(nickname)) {
			request.setAttribute("message", "NickName Can Only Contain Letters");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		else if (!InputCheck.isNum(birthyear)) {
			request.setAttribute("message", "Birth Year Can Only Contain Numbers");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}	
		else if (!InputCheck.isEmail(email)) {
			request.setAttribute("message", "Email Format is Incorrect!");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		else if (!InputCheck.isNum(birthyear)) {
			request.setAttribute("message", "Birth Year Can Only Contain Numbers!");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
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
				
				//Check if the userName exists
				String strQuery = "SELECT UserName FROM cast_db.Users WHERE UserName ='" + un + "'";
				ResultSet rs = st.executeQuery(strQuery);
				if (rs.next()) {
					request.setAttribute("message", "UserName Already Exists!");
					request.getRequestDispatcher("index.jsp").forward(request, response);
				}
				else {
					//String strQuery = "select * from Users where UserName='" + un +"' and  Password='" + pw + "'";
					strQuery = "INSERT INTO cast_db.Users VALUES('" + un +"','" + pw + "','" + firstname + "','" + lastname + "','" 
							+ email + "','" + address + "','" + "1993-02-24" + "','" + session + "')";
					System.out.println(strQuery);
					st.executeUpdate(strQuery);
					Email emailV = new Email();
					emailV.sendVerifyEmail(email,session);
					System.out.println("did something");
					request.getRequestDispatcher("index.jsp").forward(request, response);
					
				}
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
