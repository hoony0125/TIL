<%@page import="dto.BbsDto"%>
<%@page import="dao.BbsDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("utf-8"); %>

<%
String id = request.getParameter("id");
String title = request.getParameter("title");
String content = request.getParameter("content");

int seq = Integer.parseInt(request.getParameter("seq"));
System.out.println("seq:" + seq);

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

BbsDto dto = new BbsDto(id, title, content);
dto.setSeq(seq);
boolean isS = dao.updateBbs(dto);
if(isS){
%>
	<script type="text/javascript">
	alert("업데이트 저장!");
	location.href = "bbslist.jsp";
	</script>
<%
}else{
%>
	<script type="text/javascript">
	alert("다시 수정해주세요");
	location.href = "bbsupdate.jsp?seq=<%=seq %>";
	</script>
<%
}
%>



</body>
</html>