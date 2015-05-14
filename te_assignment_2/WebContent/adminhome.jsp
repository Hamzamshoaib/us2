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
<title>Admin - Home</title>
</head>
<body>
<%// 
	String name = (String) session.getAttribute("username");
	if (name == null){
		response.sendRedirect("index.jsp");
	}
%>
<form action="logout.jsp">
	<INPUT type="submit" value="logout">
</form>
<FORM NAME="delegate" ACTION="admin.jsp" METHOD="POST">
	<INPUT TYPE="submit" name="action" value="Users">
	<INPUT TYPE="submit" name="action" value="Items">
</FORM>
</body>
</html>
