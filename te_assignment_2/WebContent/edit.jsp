<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Daily Auction - Account</title>
</head>
<body>
<%
	String name = (String) session.getAttribute("username");
	if (name == null){
		response.sendRedirect("index.jsp");
	}
%>
<h1>Account Info</h1>
${session.getAttribute("username")}
	<FORM NAME="registrationform" ACTION="edit" METHOD="POST">
		<br>
		<p>UserName:  <% String user = (String) session.getAttribute("username"); out.println(user); %></p>
		<p>Password: </p>
		<INPUT type="password" name="password" value=${password}>
		<br>
		<p>First Name: </p>
		<INPUT type="text" name="firstname" value=${firstname}>
		<br>
		<p>Last Name: </p>
		<INPUT type="text" name="lastname" value=${lastname}>
		<br>
		<p>Email: </p>
		<INPUT type="text" name="email" value=${email}>
		<br>
		<p>Address: </p>
		<INPUT type="text" name="address" value=${address}>
		<br>
		<INPUT TYPE="submit" name="" value="Save">
	</FORM>
</body>
</html>