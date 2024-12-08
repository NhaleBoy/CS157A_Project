<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="login.login"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String name = request.getAttribute("name").toString();
	 	out.println(name);
	 	out.println("<br>");
	 	out.println("complete");
	%>
</body>
</html>