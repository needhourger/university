<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>register</title>
</head>

<body>
<p style="Color:red;font-weight:bold">${msg }</p>
 <form action="<c:url value='/RegisterServlet'/>" method="post"> 
<!-- <form action="/Manager/RegisterServlet" method="post">   -->
 用户名：<input type="text" name="username"/>
  <span style="Color:red;font-weight:bold">${errors.username}</span><br>
 密码： <input type="password" name="password"/>
 <span style="Color:red;font-weight:bold">${errors.password}</span><br>
 <button>注册</button>
</form>
</body>
</html>