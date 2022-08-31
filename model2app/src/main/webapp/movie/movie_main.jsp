<%@page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
function getResult(){
	var form = document.querySelector("form");
	form.action="/movie.do";
	form.method="post";
	form.submit();
}
</script>
</head>
<body>
<form>
	<select name="movie">
		<option>최근에 본 영화를 선택하세요</option>
		<option value = "우영우">우영우</option>
		<option value = "오징어게임">오징어게임</option>
		<option value = "탑건">탑건</option>
		<option value = "헐크">헐크</option>
	</select>
	</form>
	
	<button onClick="getResult()">결과보기</button>
</body>
</html>