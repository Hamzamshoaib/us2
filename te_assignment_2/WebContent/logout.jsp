<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
   <!-- Code below ends the session -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
body {background-color:#F8F8F8}
h1   {color:#000099}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Log Out</title>
</head>
<body>

<% 
	String name = (String) session.getAttribute("username");
	if (name == null){
		request.setAttribute("message", "Need to login!");
		response.sendRedirect("index.jsp");
	}
%>
    <% session.invalidate(); %>
You have been logged out
<form action="index.jsp">
	<INPUT type="submit" value="Home">
</form>
<CENTER>
<h1>Daily Auction</h1>
You have been logged out
</CENTER>
</body>
</html>