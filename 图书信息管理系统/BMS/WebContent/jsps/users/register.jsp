<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注册</title>
</head>
<body>
	<h1><center>注册</center></h1>
	<center>
		<form action="/BMS/RegisterServlet" method="post">
			用户名:<input type="text" name="Admin_name" /><br>
			密　码:<input type="password" name="Admin_pwd" /><br>
			邀请码:<input type="text" name="Admin_old_code" /><br>
			<input type="submit" value="注册" />
			<input type="reset" value="重置"/>
		</form>
	</center>
</body>
</html>