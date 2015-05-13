<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="edu.unsw.ass1.*"%>
<%@ page import="edu.unsw.comp9321.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Item Details</title>
</head>
<body>
<center>
<a href=http://localhost:8080/te_assignment_2/index.jsp>Home</a>
<% 
	String name = (String) session.getAttribute("username");
	if (name == null){
		response.sendRedirect("index.jsp");
	}
	
	ArrayList<ArrayList<String>> table = new ArrayList<ArrayList<String>>();
	table = (ArrayList<ArrayList<String>>) request.getAttribute("table");
	
	/*
	Order of Item details
	Name
	Picture
	Description
	Category
	Owner
	EndTime
	ReservePrice
	*/
	
	out.println("<h1>" + table.get(0).get(0) + "</h1>");
	out.println("<table>");
	out.println("<tr><td>" + "<img src=\"" + table.get(0).get(1) + "\" width = \"150\" height = \"150px\">" + "</td></tr>");
	out.println("<tr><td> Description: " + table.get(0).get(2) + "</td></tr>");
	out.println("<tr><td> Category: " + table.get(0).get(3) + "</td></tr>");
	out.println("<tr><td> Owner: " + table.get(0).get(4) + "</td></tr>");
	out.println("<tr><td> Ending Time: " + table.get(0).get(5) + "</td></tr>");
	if (name.equals(table.get(0).get(4)))
	{
		out.println("<td> Reserve Price: " + table.get(0).get(6) + "</td><br>");
	}
	out.println("</tr>");
	out.println("</table>");
	
	int Item_ID = Integer.parseInt(table.get(0).get(7));
	BiddingController bc = new BiddingController();
	bc.currentWinningBid(Item_ID);
%>
</center>
</body>
</html>
