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
.left {
    position: absolute;
    left: 0px;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Daily Auction - Add Item</title>
</head>
<body>
<p style="text-align:left;">
	<a href=http://localhost:8080/te_assignment_2/index.jsp>Home</a>
	<span style="float:right;"><a href="edit">Welcome ${username} </a></span>
</p>
<form action="logout.jsp" class=right>
	<INPUT type="submit" value="logout">
</form> 


<CENTER>
<h1>Daily Auction</h1>
<h2>Add a Product to Sell</h2>
<%
String message = (String) request.getAttribute("message");
if (message != null)
{
	out.println("<font size=\"3\" color=\"red\">" + message +"</font>");
}
%>
	<FORM NAME="additem" ACTION="additem" METHOD="POST">
		<p>Title: </p>
		<INPUT type="text" name="title">
		<br>
		<p>Category: </p>
		<INPUT type="text" name="category">
		<br>
		<p>Description: </p>
		<INPUT type="text" name="description">
		<br>
		<p>Picture (URL: only accepts .png .jpeg and .jpg): </p>
		<INPUT type="text" name="picture">
		<br>
		<p>Reserve Price: </p>
		<INPUT type="text" name="reserveprice">
		<br>
		<p>Starting Price: </p>
		<INPUT type="text" name="startingprice">
		<br>
		<p>Duration of Auction (In Minutes): </p>
		<!--<INPUT type="text" name="duration">-->
		<select name = "duration">
  		<option value="10">Default (10)</option>
  		<%
  		int i = 3;
  		while (i <= 60)
  		{
  			out.println("<option value=\"" + i + "\">"+ i +"</option>");
  			i++;
  		}
  		%>
		</select> 
		<br>
		<p>Address: </p>
		<INPUT type="text" name="address">
		<br>
		<INPUT TYPE="submit" name="" value="Add Item">
	</FORM>
</CENTER>	
</body>
</html>
