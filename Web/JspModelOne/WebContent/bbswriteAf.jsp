<%@page import="dto.BbsDto"%>
<%@page import="dao.BbsDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% request.setCharacterEncoding("utf-8"); %>

<%
String id = request.getParameter("id");
String title = request.getParameter("title");
String content = request.getParameter("content");
%>
  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
BbsDao dao = BbsDao.getInstance();

boolean isS = dao.writeBbs(new BbsDto(id, title, content));
if(isS){
%>
	<script type="text/javascript">
	alert("글쓰기 성공!");
	location.href = "bbslist.jsp";
	</script>
<%
}else{
%>
	<script type="text/javascript">
	alert("글입력을 다시 해 주세요");
	location.href = "bbswrite.jsp";
	</script>
<%
}
%>



</body>
</html>





