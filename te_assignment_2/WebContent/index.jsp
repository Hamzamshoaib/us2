<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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