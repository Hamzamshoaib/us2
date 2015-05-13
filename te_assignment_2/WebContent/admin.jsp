<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Thou Admin</title>
</head>
<body>
<% 
	String name = (String) session.getAttribute("username");
	if (name == null){
		response.sendRedirect("index.jsp");
	}
	
	ArrayList<ArrayList<String>> table = new ArrayList<ArrayList<String>>();
	table = (ArrayList<ArrayList<String>>) request.getAttribute("table");
	
	for (int i = 0; i < table.size(); i++)
	{
		out.println("<table><tr>");
		int j = 0;
		while (j < table.get(i).size())
		{
			out.println("<td>" +"<a href=\"itemDetails.jsp\" id=\"" + i + "\">" + table.get(i).get(j++) + "</a>" + "</td>");
			out.println("<td>" + "<form action=\'wishlist\' method=\'POST\'><input type=\'submit\' name=\'action\' value = \'Delete\'> <input type=\'hidden\' name=\'id\' value = \'" +  table.get(i).get(j++) + "\'> </FORM></td>");
		}
		out.println("</tr><br></table>");
	}
%>
</body>
</html>