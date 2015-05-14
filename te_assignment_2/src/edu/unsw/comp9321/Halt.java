package edu.unsw.comp9321;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Halt
 */
public class Halt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Halt() {
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
		String formDelegate = request.getParameter("action");
		int item_id =  Integer.parseInt(request.getParameter("id"));
		Integer.parseInt("1234");
		BiddingController verify = new BiddingController();
		if ("Halt".equals(formDelegate)){
			verify.haltAuction(item_id);
			response.sendRedirect("adminhome.jsp");
		} else if ("Delete".equals(formDelegate)){
			//System.out.println("in the else statement");
			/*response.sendRedirect("register.jsp");*/
			verify.deleteItem(item_id);
			response.sendRedirect("adminhome.jsp");
		}
	}

}
