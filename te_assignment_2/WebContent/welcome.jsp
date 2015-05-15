<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import="java.util.ArrayList" %>
  <%@ page import="java.lang.String" %>
 <%@ page import="java.util.Iterator" %>
 <%@ page import="edu.unsw.comp9321.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
body {background-color:#F8F8F8}
h1   {color:#000099}
p	 {color:#000099}
.right {
    position: absolute;
    right: 0px;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome!</title>
</head>
<body>
${message}
<!-- Code between the tags check whether a session exists or not -->
<% 
	String name = (String) session.getAttribute("username");
	if (name == null){
		response.sendRedirect("index.jsp");
	}
	else if (name.equals("Admin")) {
		response.sendRedirect("adminhome.jsp");
	}
%>

<p style="text-align:left;">
	<a href="http://localhost:8080/te_assignment_2/additem.jsp">Add Item</a>
	<span style="float:right;"><a href="edit">Welcome ${username} </a></span>
</p>
<form action="logout.jsp" class=right>
	<INPUT type="submit" value="logout">
</form> 
<form action='wishlist' method='GET'> 
<input type='submit' name='action' value ='Wishlist'>
</form>



<!--  Session ID: <%=session.getId()%>-->


<CENTER>
<H1>Daily Auction</H1>
Product Search:
<FORM ACTION='itemresult' METHOD='GET'>
<br>
<INPUT type="text" name="searchItem">
<br>
<INPUT TYPE='submit' VALUE='search'>
</FORM><br>
<a href="advance.jsp">Advanced Search</a><br>

<%
BiddingController bc = new BiddingController();
Notifications notify = new Notifications();
String ownerAction = request.getParameter("ownerAction");
if (ownerAction != null)
	if (ownerAction.equals("accept"))
	{
		bc.acceptOffer(Integer.parseInt(request.getParameter("id")));
		notify.ok("Offer has been accepted");
	}
	else if (ownerAction.equals("reject"))
	{
		bc.rejectOffer(Integer.parseInt(request.getParameter("id")));
		notify.ok("Offer has been rejected");
	}
%>
</CENTER>
</body>
</html>
