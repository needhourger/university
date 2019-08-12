<%@page import="java.text.SimpleDateFormat"%>
<%@ page errorPage="ExceptionError.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.*" %>
<%@page import="manager.domian.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>welcome</title>
</head>
<body>
<%
    Date d = new Date();
    SimpleDateFormat df =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String now = df.format(d);
%>
<%  
    User u=(User)request.getSession().getAttribute("sessionUser");
   
%>
<c:if test="u">
  <%@include file="ExceptionError.jsp" %>
</c:if>
<h1 align="center">****************Welcome*****************</h1>
<p align="center"><%=u.getusername() %></p>
<p align="center"><%=now %></p>
<p align="center">****************************************</p>
</body>
</html>