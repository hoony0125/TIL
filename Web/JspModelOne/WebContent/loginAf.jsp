<%@page import="dto.MemberDto"%>
<%@page import="dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
request.setCharacterEncoding("utf-8");
%>

<%

String id = request.getParameter("id");
String pwd = request.getParameter("pwd");

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
MemberDao dao = MemberDao.getInstance();

MemberDto mem = dao.login(id, pwd);		

if(mem != null){	// 로그인 성공 시 
	// session에 로그인 정보를 저장
	session.setAttribute("login", mem);
	session.setMaxInactiveInterval(60 * 60 * 2); 
%>
	<script type="text/javascript">
	alert("안녕하세요 <%=mem.getName()%>님")
	location.href = "bbslist.jsp";
	</script>
	
<%	
}else{
%>
	<script type="text/javascript">
	alert("ID나 Password를 확인하세요")
	location.href = "login.jsp";
	</script>
<%
}
%>

</body>
</html>