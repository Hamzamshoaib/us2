<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Details</title>
</head>
<body>
<!-- Code between the tags check whether a session exists or not -->
<% 
	String name = (String) session.getAttribute("username");
	if (name == null){
		response.sendRedirect("index.jsp");
	}
%>
<!-- This is basically testing that sessions are working properly -->
        <% 
        Integer counter = (Integer)session.getAttribute("counter");
        if (counter == null) {
            counter = new Integer(1);
        } else {
            counter = new Integer(counter.intValue() + 1);
        }

        session.setAttribute("counter", counter);
        %>
<form action="welcome.jsp">
	<INPUT type="submit" value="welcome">
</form>
<form action="logout.jsp">
	<INPUT type="submit" value="logout">
</form>
You have visited this page <%=counter %>
</body>
</html>