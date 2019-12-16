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
select{
	margin:5px 0;
}
a{
	text-decoration:none;
	color: black;
}
</style>
</head>
<body>
	<div style="BACKGROUND-COLOR: #afd1f3; WIDTH: 100%; FONT-SIZE: 12px; PADDING: 5px 0;
	MARGIN-BOTTOM: 10px"><center>查询条件</center></div>
	<form action="/BMS/BookFindByManyCondition" method="POST" style="FONT-SIZE: 12px;
	HEIGHT: 100px; MARGIN-BOTTOM: 10px">
		<div style="float: left; PADDING-LEFT:15%">
			图书编号：<input type="text" name="Book_id"><br>
			图书名称：<input type="text" name="Book_name"><br>
			图书作者：<input type="text" name="Book_author"><br>
		</div>
		<div style="float: left; PADDING-LEFT: 20%">
			图书种类：<select name="Book_type" style="padding: 2px 5px">
				<option value="">--请选择图书种类--</option>
				<option value="神话">神话</option>
				<option value="历史">历史</option>
				<option value="科幻">科幻</option>
			</select><br>
			图书isbn：<input type="text" name="Book_isbn"><br>
			价格区间：<input type="text" name="Book_minPrice">&nbsp;—
			<input type="text" name="Book_maxPrie"><br>
		</div>
		<div style="FLOAT:right; PADDING-RIGHT:20PX">
			<input type="submit" value="查询">
			<input type="reset" value="重置">
		</div>
 	</form>
	<div style="BACKGROUND-COLOR: #afd1f3; WIDTH: 100%; FONT-SIZE: 12px; PADDING: 5px 0;
	MARGIN-BOTTOM: 10px; clear: both"><center>图书列表</center></div>
	<table cellspacing="0" cellpadding="1" rules="all" bordercolor="grey" border="1" id="BookGrid"
	style="BORDER-RIGHT:grey 1px solid; WIDTH: 100%; WORD-BREAK: break-all;
	BORDER-BOTTOM: gery 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe;
	WORD-WRAP: break-word">
		<tr style="FONT-WIGHT: bold; FONT-SIZE: 12px; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">
			<td align="center" width="9%">图书编号</td>
			<td align="center" width="12%">图书名称</td>
			<td align="center" width="9%">图书作者</td>
			<td align="center" width="9%">图书价格</td>
			<td align="center" width="9%">图书类别</td>
			<td align="center" width="12%">ISBN号</td>
			<td align="center" width="12%">出版社</td>
			<td align="center" width="9%">总量</td>
			<td align="center" width="9%">剩余量</td>
			<td align="center" width="5%">编辑</td>
			<td align="center" width="5%">删除</td>
		</tr>
		<c:forEach items="${book }" var="book">
			<tr style="FONT-SIZE: 12px"
				onmousemove="this.style.backgroundColor = 'white'"
				onmouseout="this.style.backgroundColor = '#f5fafe';">
				<td style="cursor: hand; height: 22px" align="center" width="9%">${book.book_id }</td>
				<td style="cursor: hand; height: 22px" align="center" width="12%">${book.book_name }</td>
				<td style="cursor: hand; height: 22px" align="center" width="9%">${book.book_author }</td>
				<td style="cursor: hand; height: 22px" align="center" width="9%">${book.book_price }</td>
				<td style="cursor: hand; height: 22px" align="center" width="9%">${book.book_type }</td>
				<td style="cursor: hand; height: 22px" align="center" width="12%">${book.book_isbn }</td>
				<td style="cursor: hand; height: 22px" align="center" width="12%">${book.book_publisher }</td>
				<td style="cursor: hand; height: 22px" align="center" width="9%">${book.book_allnum }</td>
				<td style="cursor: hand; height: 22px" align="center" width="9%">${book.book_surplus }</td>
				<td style="cursor: hand; height: 22px" align="center" width="5%">
					<a href="/BMS/BookFindByIdServlet?Book_id=${book.book_id }">编辑</a></td>
				<td style="cursor: hand; height: 22px" align="center" width="5%">
					<a href="/BMS/BookDeleteServlet?Book_id=${book.book_id }">删除</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>