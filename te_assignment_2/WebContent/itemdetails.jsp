<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="edu.unsw.ass1.*"%>
<%@ page import="edu.unsw.comp9321.*"%>
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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Item Details</title>
</head>
<body>
<p style="text-align:left;">
	<a href=http://localhost:8080/te_assignment_2/index.jsp>Home</a>
	<span style="float:right;"><a href="edit">Welcome ${username} </a></span>
</p>
<form action="logout.jsp" class=right>
	<INPUT type="submit" value="logout">
</form> 
<form action='wishlist' method='POST'> 
<input type='submit' name='action' value ='Wishlist'>
</form>

<center>
<h1>Daily Auction</h1>
<% 
	String name = (String) session.getAttribute("username");
	if (name == null){
		response.sendRedirect("index.jsp");
	}
	
	ArrayList<ArrayList<String>> table = new ArrayList<ArrayList<String>>();
	table = (ArrayList<ArrayList<String>>) request.getAttribute("table");
	
	/*
	Order of Item details
	Name
	Picture
	Description
	Category
	Owner
	EndTime
	ReservePrice
	Item_ID
	StartingPrice
	*/
	
	out.println("<h2>" + table.get(0).get(0) + "</h2>");
	out.println("<table>");
	out.println("<tr><td>" + "<img src=\"" + table.get(0).get(1) + "\" width = \"150\" height = \"150px\">" + "</td></tr>");
	out.println("<tr><td> Description: " + table.get(0).get(2) + "</td></tr>");
	out.println("<tr><td> Category: " + table.get(0).get(3) + "</td></tr>");
	out.println("<tr><td> Owner: " + table.get(0).get(4) + "</td></tr>");
	out.println("<tr><td> Ending Time: " + table.get(0).get(5) + "</td></tr>");
	if (name.equals(table.get(0).get(4)))
	{
		out.println("<td> Reserve Price: " + table.get(0).get(6) + "</td><br>");
	}
	out.println("</table>");
	
	int Item_ID = Integer.parseInt(table.get(0).get(7));
	//System.out.println(Item_ID);
	BiddingController bc = new BiddingController();
	int increment = bc.getIncrement();
	String newBid = request.getParameter("bid");
	if (!InputCheck.isNum(newBid) && newBid != null) {
		Notifications notify = new Notifications();
		notify.ok("Please Enter Numbers for Bidding");
	}
	else {
		if (newBid != null)
		{
			Notifications notify = new Notifications();
			int result = bc.placeBid(name, Item_ID, Integer.parseInt(newBid));
			// need to add checks if placeBid don't work
			if (result == 1)
			{
				notify.ok("Bid has been accepted");
			}
			else if (result == 0 || result == -1)
			{
				notify.ok("Bid has not been accepted. Bid is lower than increment.");
			}
			else if (result == -3)
			{
				notify.ok("Sorry, auction has finished.");	
			}
			else if (result == -4)
			{
				notify.ok("Auction has been halted. Contact Administrator!");
			}
		}
	}
	int highBid = bc.currentWinningBid(Item_ID);
	if (highBid == -1)
	{
		out.println("<br><br><br><br><b>No Bids yet</b><br>");
		out.println("Starting price = " + table.get(0).get(8) + "<br>");
	}
	else
	{
		out.println("<br><br><br><br><b>Current Winning Bid: " + bc.currentWinningBid(Item_ID)+ "</b><br>");
	}
	out.println("<br>Bidding Increment: " + increment);
	
	String Owner = table.get(0).get(4);
	if (!(bc.isAuctionDone(Item_ID) || Owner.equals(name)))
	{
		out.println("<form action=\'itemdetails\' method=\'POST\'><input type=\"text\" name=\"bid\"><input type=\'hidden\' name=\'id\' value = \'" +  table.get(0).get(7) + "\'><input type=\'submit\' name=\'action\' value ='Place Bid'></FORM>");
	}
	else if ((bc.isAuctionDone(Item_ID) && Owner.equals(name) && bc.bidLessThanReserved(Item_ID)))
	{
		out.println("<form action=\'welcome.jsp\' method=\'POST\'><input type=\'hidden\' name=\'ownerAction\' value = \'accept\'><input type=\'hidden\' name=\'id\' value = \'" +  table.get(0).get(7) + "\'><input type=\'submit\' name=\'action\' value ='Accept'></FORM>");
		out.println("<br>");
		out.println("<form action=\'welcome.jsp\' method=\'POST\'><input type=\'hidden\' name=\'ownerAction\' value = \'reject\'><input type=\'hidden\' name=\'id\' value = \'" +  table.get(0).get(7) + "\'><input type=\'submit\' name=\'action\' value ='Reject Bid'></FORM>");
	}
%>
</center>
</body>
</html>
