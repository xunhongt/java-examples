<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SESSION TRACKING - LOGIN PAGE</title>
</head>
<body>

LOGIN PAGE
<br>
<form action="LoginServlet" method="GET">
	User ID : <input type="text" name="id" size="20"><br>
	Password : <input type="password" name="password" size="20">
<br><br>
<input type="submit" value="Submit">
</form>

</body>
</html>