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
<FORM ACTION='menu.jsp' METHOD='POST'>
<br>
<INPUT type="text" name="searchItem">
<br>
<INPUT TYPE='submit' VALUE='search'>
</FORM><br>
<a href="advance.jsp">Advanced Search</a><br><br><br><br>
<%
	String search = request.getParameter("searchItem");
	String heading = request.getParameter("heading");
	String category = request.getParameter("category");
	String description = request.getParameter("description");
	//out.println("you search for " + category);

	if (search != null && !search.isEmpty()) {
		ArrayList<AuctionItems> values = SearchFile.search(search);
		for (int i = 0; i < values.size(); i++) {
			AuctionItems item = values.get(i);
			out.println("<tr>");
			out.println("<td>" + "<img src=\"" + item.getPicture() + "\" width = \"50\" height = \"50px\">" + "</td>");
			out.println("<td>" +"<a href=\"menu.jsp\" id=\"" + i + "\">" + item.getTitle() + "</a>" + "</td>");
			out.println("<td>" + "<form action=\'welcome.jsp\' method=\'POST\'>" + "<br><INPUT TYPE=\'submit\' VALUE=\'Add Wishlist\'></FORM>" + "</td>");
			out.println("</tr>");
		}
	}
	if ((heading != null && !heading.isEmpty()) || (category != null && !category.isEmpty()) ||
			(description != null && !description.isEmpty())) {
		ArrayList<AuctionItems> values = AdvanceSearch.search(heading, category, description);
		for (int i = 0; i < values.size(); i++) {
			AuctionItems item = values.get(i);
			out.println("<tr>");
			out.println("<td>" + "<img src=\"" + item.getPicture() + "\" width = \"50\" height = \"50px\">" + "</td>");
			out.println("<td>" +"<a href=\"menu.jsp\" id=\"" + i + "\">" + item.getTitle() + "</a>" + "</td>");
			out.println("<td>" + "<form action=\'welcome.jsp\' method=\'POST\'>" + "<br><INPUT TYPE=\'submit\' VALUE=\'Add Wishlist\'></FORM>" + "</td>");
			out.println("</tr>");
		}
	}
	
%>

</CENTER>
</body>
</html>