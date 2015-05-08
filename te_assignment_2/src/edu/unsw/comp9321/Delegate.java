package edu.unsw.comp9321;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Delegate
 */
public class Delegate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Delegate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("in the delegate");
		String formDelegate = request.getParameter("action");
		String user = request.getParameter("username");
		if ("login".equals(formDelegate)){
			response.sendRedirect("login");
		} else {
			request.setAttribute("username", user);
			request.getRequestDispatcher("register.jsp").forward(request, response);
			
		}
	}

}
