<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	MARGIN-BOTTOM: 10px"><center>添加图书</center></div>
	<form action="/BMS/BookAddServlet" method="POST" style="FONT-SIZE: 12px;
	HEIGHT: 100px; MARGIN-BOTTOM: 10px">
		<div style="float: left; PADDING-LEFT:15%">
			图书名称：<input type="text" name="Book_name"><br>
			图书作者：<input type="text" name="Book_author"><br>
			图书价格：<input type="text" name="Book_price"><br>
			图书种类：<input type="text" name="Book_type"><br>
		</div>
		<div style="float: left; PADDING-LEFT: 20%">
			图书isbn：<input type="text" name="Book_isbn"><br>
			出版社：<input type="text" name="Book_publisher"><br>
			总量：<input type="text" name="Book_allnum"><br>
			剩余量：<input type="text" name="Book_surplus"><br>
		</div>
		<div style="FLOAT:right; PADDING-RIGHT:20PX">
			<input type="submit" value="添加">
			<input type="reset" value="重置">
		</div>
 	</form>
</body>
</html>