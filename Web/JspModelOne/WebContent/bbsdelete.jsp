<%@page import="dao.BbsDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
int seq = Integer.parseInt(request.getParameter("seq"));
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

boolean isS = dao.deleteBbs(seq);

if(isS){
%>
	<script type="text/javascript">
	alert("정상적으로 삭제되었습니다.");
	location.href = "bbslist.jsp";
	</script>


<%
}else{
%>
	<script type="text/javascript">
	alert("삭제되지 않았습니다.");
	location.href = "bbslist.jsp";
	</script>
<%
}
%>

</body>
</html>