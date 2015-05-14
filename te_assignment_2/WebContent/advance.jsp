<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
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
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Let's Advance Search</title>
</head>
<body>
<p style="text-align:left;">
	<a href=http://localhost:8080/te_assignment_2/index.jsp>Home</a>
	<span style="float:right;">Welcome ${username} </span>
</p>
<form action="logout.jsp" class=right>
	<INPUT type="submit" value="logout">
</form> 

<center>
	<h1>Advance Search - Daily Auction</h1>
	<h2>Advance Search</h2>
	<FORM ACTION='menu.jsp' METHOD='POST'>
	<br>
	Title: <INPUT type="text" name="heading">
	<br>
	Category: <INPUT type="text" name="category">
	<br>
	Description: <INPUT type="text" name="description">
	<br> 
	<INPUT TYPE='submit' VALUE='search'>
	</FORM>
</center>
</body>
</html>