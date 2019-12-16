<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ModifyPerson.jsp</title>
</head>
<body>
<h1 align="center" style="color: red;">管理人员档案</h1>
<sql:setDataSource var="snapshot" driver="com.mysql.cj.jdbc.Driver"
     url="jdbc:mysql://localhost:3306/Manager?useUnicode=true&characterEncoding=UTF-8&serverTimezone=CTT"
     user="root"  password="123456"/>
<sql:query dataSource="${snapshot }" var="result">
select * from Employee;
</sql:query>
<table border="1"  cellspacing="0" cellpadding="10">
<tr>
   <th>EmployeeID</th>
   <th>UserName</th>
   <th>Sex</th>
   <th>Branch</th>
   <th>NativePlace</th>
   <th>Marriage</th>
   <th>Politics</th>
   <th>Education</th>
   <th>Operation</th>
</tr>
<c:forEach var="row" items="${result.rows}">
<tr>
   <td><c:out value="${row.EmployeeID}"/></td>
   <td><c:out value="${row.UserName}"/></td>
   <td><c:out value="${row.Sex}"/></td>
   <td><c:out value="${row.Branch}"/></td>
   <td><c:out value="${row.NativePlace}"/></td>
   <td><c:out value="${row.Marriage}"/></td>
   <td><c:out value="${row.Politics}"/></td>
   <td><c:out value="${row.Education}"/></td>
   <td>
    
    <a href="/Manager/ModifyEmployeeMessage?EmployeeID=${row.EmployeeID}">修改</a>&nbsp;
      <a href="/Manager/ModifyEmployeeMessage?EmployeeID=${row.EmployeeID}">删除</a>
   </td>
</tr>
</c:forEach>
</table>
</body>
</html>