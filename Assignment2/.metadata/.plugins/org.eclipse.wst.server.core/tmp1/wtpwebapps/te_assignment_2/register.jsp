<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
		<title>Daily Auction - Registration</title>
	</head>
<body>
	<FORM NAME="registrationform" ACTION="register" METHOD="POST">
		<br>
		<p>UserName: </p>
		<INPUT type="text" name="username">
		<br>
		<p>Password: </p>
		<INPUT type="password" name="password">
		<br>
		<p>First Name: </p>
		<INPUT type="text" name="firstname">
		<br>
		<p>Last Name: </p>
		<INPUT type="text" name="lastname">
		<br>
		<p>Preferred Name: </p>
		<INPUT type="text" name="nickname">
		<br>
		<p>Email: </p>
		<INPUT type="text" name="email">
		<br>
		<p>Birth Year: </p>
		<INPUT type="text" name="birthyear">
		<br>
		<p>Address: </p>
		<INPUT type="text" name="address">
		<br>
		<p>Credit Card Number: </p>
		<INPUT type="text" name="creditcard" value="1234 5678 0912 3456">
		<br>
		<INPUT TYPE="submit" name="" value="Register">
	</FORM>
</body>
</html>