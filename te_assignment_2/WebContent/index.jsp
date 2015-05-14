<%@page import="edu.unsw.comp9321.Notifications"%>
<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
body {background-color:#F8F8F8}
h1   {color:#000099}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>	Daily Auction</title>
</head>
<body>
<CENTER>
<h1>Daily Auction</h1>
<% 
	String name = (String) session.getAttribute("username");
	if (name != null){
		response.sendRedirect("welcome.jsp");
	}
	Notifications n = new Notifications();
	n.acceptOffer(100, 10, "iphone");
%>
${message}

<FORM NAME="loginform" ACTION="delegate" METHOD="POST">
<br>
<p>UserName: </p>
<INPUT type="text" name="username">
<br>
<p>Password: </p>
<INPUT type="password" name="password">
<br><br>
<INPUT TYPE="submit" name="action" value="login">
<INPUT TYPE="submit" name="action" value="register">

</FORM>
</CENTER>
</body>
</html>