<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>login</title>
</head>

<body>
<form action="<c:url value='/LoginServlet'/>" method="post">
 用户名：<input type="text" name="username"/><br>
  密码： <input type="password" name="password"/><br>
<!--<span style="Color:red;font-weight:bold">${errors.username}</span><br>  --> 

 <!-- <span style="Color:red;font-weight:bold">${errors.password}</span><br>--> 
 <button>登录</button><br>
<a href="/Manager/register.jsp">还没有注册，点击注册</a>
</form>
</body>
</html>