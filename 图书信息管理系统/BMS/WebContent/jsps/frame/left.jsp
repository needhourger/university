<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.list{
	padding:25px 20%;
}
ul li{
	list-style-type:none;
	padding-top:45px;
}
a{
	text-decoration:none;
	color: black;
}
</style>
</head>
<body  style="background-color: #6BB8Fa">
<div id="divleft">
	<ul class="list">
		<li>
			<a href="/BMS/BookListServlet" target="framMain">查询图书</a>
		</li>
		<li>
			<a href="/BMS/jsps/books/bookAdd.jsp" target="framMain">添加图书</a>
		</li>
		<li>
			<a href="/BMS/RecordListServlet" target="framMain">操作记录</a>
		</li>
	</ul>
</div>
</body>
</html>