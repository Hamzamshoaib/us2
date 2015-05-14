package edu.unsw.comp9321;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Edit
 */
public class Edit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Edit() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String un = (String) request.getSession().getAttribute("username");
		GetUserDetails userdetails = new GetUserDetails();
		
		String pw = userdetails.getPassword(un);
		String firstname = userdetails.getFirstName(un);
		String lastname = userdetails.getLastName(un);
		String email = userdetails.getEmail(un);
		String address = userdetails.getAddress(un);
		
		request.setAttribute("password", pw);
		request.setAttribute("firstname", firstname);
		request.setAttribute("lastname", lastname);
		request.setAttribute("email", email);
		request.setAttribute("address", address);
		request.getRequestDispatcher("edit.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserController uscontrol = new UserController();
		String username = (String) request.getSession().getAttribute("username");
		String pw = request.getParameter("password");
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		int error = 0;
		if (!InputCheck.isLetters(firstname)) {
			request.setAttribute("message", "FirstName Can Only Contain Letters");
			//request.getRequestDispatcher("index.jsp").forward(request, response);
			error = 1;
		}
		else if (!InputCheck.isLetters(lastname)) {
			request.setAttribute("message", "LastName Can Only Contain Letters");
			//request.getRequestDispatcher("index.jsp").forward(request, response);
			error = 1;
		}
		else if (!InputCheck.isEmail(email)) {
			request.setAttribute("message", "Email Format is Incorrect!");
			//request.getRequestDispatcher("index.jsp").forward(request, response);
			error = 1;
		}
			
		if (error == 0) {
			uscontrol.updatePassword(username, pw);
			uscontrol.updateEmail(username, email);
			uscontrol.updateFirstName(username, firstname);
			uscontrol.updateLastName(username, lastname);
			uscontrol.updateAddress(username, address);

			request.setAttribute("password", pw);
			request.setAttribute("firstname", firstname);
			request.setAttribute("lastname", lastname);
			request.setAttribute("email", email);
			request.setAttribute("address", address);
		}
		
		request.getRequestDispatcher("welcome.jsp").forward(request, response);
	}

}
