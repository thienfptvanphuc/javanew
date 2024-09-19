<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@page isErrorPage="true" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Error JSP</title>
</head>
<body>
	<h1>Error PAGE!</h1>
	<h3>Some exception occurred.</h3>
	Exception: <%=exception%>
</body>
</html>