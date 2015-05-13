<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="com.sun.org.apache.xerces.internal.parsers.DOMParser,org.w3c.dom.*,
javax.xml.parsers.DocumentBuilderFactory,javax.xml.parsers.DocumentBuilder"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="edu.unsw.comp9321.*"%>
<%@ page import="edu.unsw.ass1.*"%>
<%@ page import="java.util.ArrayList" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Results</title>
</head>
<body>
<CENTER>
<h1>Daily Auction</h1>
Search for an item
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