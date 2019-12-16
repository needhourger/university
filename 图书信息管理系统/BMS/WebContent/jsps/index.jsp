<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<frameset rows="10%, 80%, 10%" frameborder="no" border="0" framespacing="0">
	<frame src="/BMS/jsps/frame/top.jsp">
	<frameset cols="10%, 90%">
		<frame src="/BMS/jsps/frame/left.jsp">
		<frame src="/BMS/jsps/frame/welcome.jsp" name="framMain">
	</frameset>
	<frameset>
		<frame src="/BMS/jsps/frame/bottom.jsp">
	</frameset>
</frameset>
</html>