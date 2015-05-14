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
<title>Daily Auction - Add Item</title>
</head>
<body>
<a href=http://localhost:8080/te_assignment_2/index.jsp>Home</a>
<h1>Add an Item for Auction</h1>
	<FORM NAME="additem" ACTION="additem" METHOD="POST">
		<p>Title: </p>
		<INPUT type="text" name="title">
		<br>
		<p>Description: </p>
		<INPUT type="text" name="description">
		<br>
		<p>Picture (URL): </p>
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
		<select>
  		<option value="default">Default (10)</option>
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
</body>
</html>
