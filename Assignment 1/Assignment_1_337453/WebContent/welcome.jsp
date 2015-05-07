<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import="java.util.ArrayList" %>
 <%@ page import="java.util.Iterator" %>
 <%@ page import="edu.unsw.comp9321.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome!</title>
</head>
<body>
<CENTER>
<H1>Daily Auction</H1>
Search for an item
<FORM ACTION='menu.jsp' METHOD='POST'>
<br>
<INPUT type="text" name="searchItem">
<br>
<INPUT TYPE='submit' VALUE='search'>
</FORM><br>
<a href="advance.jsp">Advanced Search</a><br>
<table>
<%
		ArrayList<AuctionItems> values = RandomFunc.randElements();
		for (int i = 0; i < values.size(); i++) {
			AuctionItems item = values.get(i);
			out.println("<tr>");
			out.println("<td>" + "<img src=\"" + item.getPicture() + "\" width = \"50\" height = \"50px\">" + "</td>");
			out.println("<td>" +"<a href=\"menu.jsp\" id=\"" + i + "\">" + item.getTitle() + "</a>" + "</td>");
			out.println("<td>" + "<form action=\'welcome.jsp\' method=\'POST\'>" + "<br><INPUT TYPE=\'submit\' VALUE=\'Add Wishlist\'></FORM>" + "</td>");
			out.println("</tr>");
		}
%>
</table>
</CENTER>
</body>
</html>