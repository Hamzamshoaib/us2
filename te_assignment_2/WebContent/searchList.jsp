<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="edu.unsw.comp9321.*"%>
<%@ page import="edu.unsw.ass1.*"%>
<%@ page import="java.util.ArrayList" %>

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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Results</title>
</head>
<p style="text-align:left;">
	<a href=http://localhost:8080/te_assignment_2/index.jsp>Home</a>
	<span style="float:right;">Welcome ${username} </span>
</p>
<form action="logout.jsp" class=right>
	<INPUT type="submit" value="logout">
</form> 
<CENTER>
<h1>Daily Auction</h1>
<h2>Product Search</h2>
<FORM ACTION='itemresult' METHOD='GET'>
<br>
<INPUT type="text" name="searchItem">
<br>
<INPUT TYPE='submit' VALUE='search'>
</FORM><br>
<a href="advance.jsp">Advanced Search</a><br>
<%
	ArrayList<ArrayList<String>> table = new ArrayList<ArrayList<String>>();
	table = (ArrayList<ArrayList<String>>) request.getAttribute("table");
	
	for (int i = 0; i < table.size(); i++)
	{
		out.println("<table><tr>");
		int j = 0;
		while (j < table.get(i).size())
		{
			out.println("<td>" + "<img src=\"" + table.get(i).get(j++) + "\" width = \"50\" height = \"50px\">" + "</td>");
			//out.println("<td>" +"<a href=\"http://localhost:8080/te_assignment_2/itemdetails\" name = \"ID\" value=\"" + table.get(i).get(j+1) +"\">" + table.get(i).get(j++) + "</a>" + "</td>");
			out.println("<td>" + "<form action=\'itemdetails\' method=\'POST\'><input type=\'submit\' name=\'action\' value = \'" + table.get(i).get(j++) +"\'> <input type=\'hidden\' name=\'id\' value = \'" +  table.get(i).get(j) + "\'> </FORM></td>");
			out.println("<td>" + "<form action=\'wishlist\' method=\'POST\'><input type=\'submit\' name=\'action\' value = \'Add to Wishlist\'> <input type=\'hidden\' name=\'id\' value = \'" +  table.get(i).get(j++) + "\'> </FORM></td>");
		}
		out.println("</tr><br></table>");
	}
%>

</CENTER>
</body>
</html>