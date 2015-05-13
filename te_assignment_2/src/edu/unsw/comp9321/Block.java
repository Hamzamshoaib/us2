package edu.unsw.comp9321;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Block
 */
public class Block extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Block() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
//
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String formDelegate = request.getParameter("action");
		String username =  request.getParameter("id");
		AdminController verify = new AdminController();
		if ("block".equals(formDelegate)){
			verify.banUser(username);
			response.sendRedirect("adminhome.jsp");
		} else if ("unblock".equals(formDelegate)){
			//System.out.println("in the else statement");
			/*response.sendRedirect("register.jsp");*/
			verify.unbanUser(username);
			response.sendRedirect("adminhome.jsp");
		}
	}

}
