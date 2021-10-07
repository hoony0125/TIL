<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String msg = request.getParameter("msg");
%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
if(msg.equals("OK")){
	%>
	<script type="text/javascript">
	alert('성공적으로 추가되었습니다');
	location.href = 'member?param=login';
	</script>
	<%
}else{
	%>
	<script type="text/javascript">
	alert('추가되지 않았습니다. 다시 작성해 주십시오');
	location.href = 'member?param=regi';
	</script>
	<%
}
%>

</body>
</html>