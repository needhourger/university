<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登录</title>
</head>
<body>
	<h1><center>登录</center></h1>
	<center><form action="/BMS/LoginServlet" post="GET">
		用户名:<input type="text" name="Admin_name"/><br>
		密　码:<input type="password" name="Admin_pwd"/><br>
		<input type="submit" value="登录"/>
	</form></center>
</body>
</html>	