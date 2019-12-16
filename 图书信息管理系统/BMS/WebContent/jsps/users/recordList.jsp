<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div style="BACKGROUND-COLOR: #afd1f3; WIDTH: 100%; FONT-SIZE: 12px; PADDING: 5px 0;
	MARGIN-BOTTOM: 10px; clear: both"><center>记录列表</center></div>
	<table cellspacing="0" cellpadding="1" rules="all" bordercolor="grey" border="1" id="BookGrid"
	style="BORDER-RIGHT:grey 1px solid; WIDTH: 100%; WORD-BREAK: break-all;
	BORDER-BOTTOM: gery 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe;
	WORD-WRAP: break-word">
		<tr style="FONT-WIGHT: bold; FONT-SIZE: 12px; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">
			<td align="center" width="25%">记录编号</td>
			<td align="center" width="25%">管理员编号</td>
			<td align="center" width="25%">图书编号</td>
			<td align="center" width="25%">操作记录</td>
		</tr>
		<c:forEach items="${record }" var="record">
			<tr style="FONT-SIZE: 12px"
				onmousemove="this.style.backgroundColor = 'white'"
				onmouseout="this.style.backgroundColor = '#f5fafe';">
				<td style="cursor: hand; height: 22px" align="center" width="9%">${record.record_id }</td>
				<td style="cursor: hand; height: 22px" align="center" width="12%">${record.admin_id }</td>
				<td style="cursor: hand; height: 22px" align="center" width="9%">${record.book_id }</td>
				<td style="cursor: hand; height: 22px" align="center" width="9%">${record.record_msg }</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>