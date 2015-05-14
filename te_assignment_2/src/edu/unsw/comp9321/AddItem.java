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
		String category = request.getParameter("category");
//		System.out.println("connecting to database");
		


		if (!InputCheck.isURL(picture)) {
			request.setAttribute("message", "Incorret URL");
			request.getRequestDispatcher("additem.jsp").forward(request, response);
		}
		else if (!InputCheck.isNum(resprice)) {
			request.setAttribute("message", "Reserve Price Can Only Contain Numbers");
			request.getRequestDispatcher("additem.jsp").forward(request, response);
		}	
		else if (!InputCheck.isNum(startprice)) {
			request.setAttribute("message", "Start Price Can Only Contain Numbers");
			request.getRequestDispatcher("additem.jsp").forward(request, response);
		}
		else if (!InputCheck.isLetters(category)) {
			request.setAttribute("message", "Category Can Only Contain Letters!");
			request.getRequestDispatcher("additem.jsp").forward(request, response);
		}
				
		UserController uc = new UserController();
		uc.addItem(title,owner,description,category,picture,resprice,startprice,duration, address);
				
	}
		

}
