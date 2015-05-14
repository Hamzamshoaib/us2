<%@page import="edu.unsw.comp9321.BiddingController"%>
<%@page import="edu.unsw.comp9321.UserController"%>
<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
body {background-color:lightgrey}
h1   {color:blue}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Thou Admin</title>
</head>
<body>
<form action="index.jsp">
	<INPUT type="submit" value="Home">
</form>
<% 
	String name = (String) session.getAttribute("username");
	if (name == null){
		response.sendRedirect("index.jsp");
	}
	
	UserController uscontroller = new UserController();
	BiddingController bidcontrol = new BiddingController();
	String formDelegate = request.getParameter("action");
	if ("Users".equals(formDelegate)){
		ArrayList<String> table = new ArrayList<String>();
		table = uscontroller.getAllUsernames();
		//out.println("<table>");
		for (int i = 0; i < table.size(); i++){
			if ("Admin".equals(table.get(i))){
				continue;
			}
			String val = "block";
			String username = table.get(i);
			if (uscontroller.isBlocked(username) == 1) {
				val = "unblock";
			}//
			out.println("<table><tr>");
			out.println("<td>" + username + "</td>");
			out.println("<td>" + "<form action=\'" + "block" + "\' method=\'POST\'><input type=\'submit\' name=\'action\' value = \'" + val + "\'> <input type=\'hidden\' name=\'id\' value = \'" +  username + "\'> </FORM></td>");
			out.println("</tr></table><br>");
		}
		//out.println("</table>");
	} else if ("Items".equals(formDelegate)){
		ArrayList<Integer> table = new ArrayList<Integer>();
		table = uscontroller.getAllItems();
		//out.println("<table>");
		for (int i = 0; i < table.size(); i++){
			String halt = "halt";
			String delete = "delete";
			
			out.println("<table><tr>");
			out.println("<td>" + "<img src=\"" + table.get(i) + "\" width = \"50\" height = \"50px\">" + "</td>");
			out.println("<td>" + "<form action=\'itemdetails\' method=\'POST\'><input type=\'submit\' name=\'action\' value = \'" + uscontroller.getItemName(table.get(i)) +"\'> <input type=\'hidden\' name=\'id\' value = \'" +  table.get(i) + "\'> </FORM></td>");
			if (!bidcontrol.isHalted(table.get(i))){
				out.println("<td>" + "<form action=\'halt\' method=\'POST\'><input type=\'submit\' name=\'action\' value = \'" + "Halt" +"\'> <input type=\'hidden\' name=\'id\' value = \'" +  table.get(i) + "\'> </FORM></td>");
			}
			out.println("<td>" + "<form action=\'halt\' method=\'POST\'><input type=\'submit\' name=\'action\' value = \'" + "Delete" +"\'> <input type=\'hidden\' name=\'id\' value = \'" +  table.get(i) + "\'> </FORM></td>");
			out.println("</tr><br></table>");
			
		/*	
			
			out.println("<table><tr>");
			out.println("<td>" +"<a href=\"itemDetails.jsp\" id=\"" + i + "\">" + table.get(i) + "</a>" + "</td>");
			out.println("<td>" + "<form action=\'halt\' method=\'POST\'>");
			out.println("<input type=\'submit\' name=\'action\' value = \'Delete\'> <input type=\'hidden\' name=\'id\' value = \'" +  table.get(i) + "\'> </FORM></td>");
			out.println("</tr><br>></table");*/
		}
		//out.println("</table>");
	}
%>
</body>
</html>
