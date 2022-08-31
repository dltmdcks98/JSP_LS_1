<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View 역할</title>
</head>
<body>
<%
//세션에서 값 받아오기 
	String msg = (String)request.getAttribute("data");
%>
당신이 선택한 혈액형에 대한 결과 판단
<%=msg%>
</body>
</html>