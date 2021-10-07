<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%									//param은 다르게 붙여도 상관없음
response.sendRedirect("member?param=login");	// 아무런 작동없이(버튼을 누른다거나) 무조건 이동시켜주는 역할 location.href처럼
%>
</body>
</html>