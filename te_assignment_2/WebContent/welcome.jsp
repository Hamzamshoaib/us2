<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import="java.util.ArrayList" %>
  <%@ page import="java.lang.String" %>
 <%@ page import="java.util.Iterator" %>
 <%@ page import="edu.unsw.comp9321.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
body {background-color:lightgrey}
h1   {color:blue}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome!</title>
</head>
<body>
<CENTER>
<!-- Code between the tags check whether a session exists or not -->
<% 
	String name = (String) session.getAttribute("username");
	if (name == null){
		session.invalidate();
		response.sendRedirect("index.jsp");
	}
	else if (name.equals("Admin")) {
		response.sendRedirect("adminhome.jsp");
	}
%>
<a href="http://localhost:8080/te_assignment_2/additem.jsp">Add Item</a>
<h1>Welcome ${username}</h1>
Session ID: <%=session.getId()%>
<form action="logout.jsp">
	<INPUT type="submit" value="logout">
</form>
<H1>Daily Auction</H1>
Search for an item
<FORM ACTION='itemresult' METHOD='GET'>
<br>
<INPUT type="text" name="searchItem">
<br>
<INPUT TYPE='submit' VALUE='search'>
</FORM><br>
<a href="advance.jsp">Advanced Search</a><br>

</CENTER>
</body>
</html>