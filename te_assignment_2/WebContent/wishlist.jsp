<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="edu.unsw.ass1.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
body {background-color:#F8F8F8}
h1   {color:#000099}
.right {
    position: absolute;
    right: 0px;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Wishlist</title>
</head>
<body>
<p style="text-align:left;">
	<a href=http://localhost:8080/te_assignment_2/index.jsp>Home</a>
	<span style="float:right;"><a href="edit">Welcome ${username} </a> </span>
</p>
<form action="logout.jsp" class=right>
	<INPUT type="submit" value="logout">
</form> 
<center>
<h1>Daily Auction</h1>
<h2>Wishlist</h2>
<!-- Code between the tags check whether a session exists or not -->
<% 
	String name = (String) session.getAttribute("username");
	if (name == null){
		response.sendRedirect("index.jsp");
	}
	
	ArrayList<String> itemNames = new ArrayList<String>();
	ArrayList<Integer> itemIDs = new ArrayList<Integer>();
	itemNames = (ArrayList<String>) request.getAttribute("itemNames");
	itemIDs = (ArrayList<Integer>) request.getAttribute("itemIDs");
	

	out.println("<table><tr>");
	for (String s : itemNames){
		int ID = itemIDs.get(itemNames.indexOf(s));
			out.println("<td>" + "<form action=\'itemdetails\' method=\'GET\'><input type=\'submit\' name=\'action\' value = \'" + s +"\'> <input type=\'hidden\' name=\'id\' value = \'" +  ID + "\'> </FORM></td>");
			//out.println("<td>" +"<p>" + s + "<a>" + "</td>");
			out.println("<td>" + "<form action=\'wishlist\' method=\'POST\'><input type=\'submit\' name=\'action\' value = \'Delete\'> <input type=\'hidden\' name=\'id\' value = \'" +  ID + "\'> </FORM></td>");
			out.println("</tr>");
	}
	out.println("</tr><br></table>");
%>


</center>
</body>
</html>