<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
input{
	margin:5px 0;
}
</style>
</head>
<body>
<div style="BACKGROUND-COLOR: #afd1f3; WIDTH: 100%; FONT-SIZE: 12px; PADDING: 5px 0;
	MARGIN-BOTTOM: 10px"><center>图书编辑</center></div>
<form action="/BMS/BookEditServlet" method="POST" style="FONT-SIZE: 12px;
	HEIGHT: 100px; MARGIN-BOTTOM: 10px">
	<c:forEach items="${book }" var="book">
		<div style="float: left; PADDING-LEFT:15%">
			图书编号：<input type="text" name="Book_id" value="${book.book_id }"><br>
			图书名称：<input type="text" name="Book_name" value="${book.book_name }"><br>
			图书作者：<input type="text" name="Book_author" value="${book.book_author }"><br>
			图书价格：<input type="text" name="Book_price" value="${book.book_price }"><br>
			图书种类：<input type="text" name="Book_type" value="${book.book_type }"><br>
		</div>
		<div style="float: left; PADDING-LEFT: 20%">
			图书isbn：<input type="text" name="Book_isbn" value="${book.book_isbn }"><br>
			出版社：<input type="text" name="Book_publisher" value="${book.book_publisher }"><br>
			总量：<input type="text" name="Book_allnum" value="${book.book_allnum }"><br>
			剩余量：<input type="text" name="Book_surplus" value="${book.book_surplus }"><br>
		</div>
		<div style="FLOAT:right; PADDING-RIGHT:20PX">
			<input type="submit" value="修改">
		</div>
	</c:forEach>
 </form>
</body>
</html>