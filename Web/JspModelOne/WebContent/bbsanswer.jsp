<%@page import="dto.MemberDto"%>
<%@page import="dto.BbsDto"%>
<%@page import="dao.BbsDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
int seq = Integer.parseInt(request.getParameter("seq"));

BbsDao dao = BbsDao.getInstance();
BbsDto bbs = dao.getBbs(seq);	// 부모글의 정보를 얻어옴 
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h2>부모글</h2>

<div align="center">
<table border="2">
<col width="200"><col width="500">
<tr>
	<th>작성자</th>
	<td><%=bbs.getId() %></td>
</tr>
<tr>
	<th>제목</th>
	<td><%=bbs.getTitle() %></td>
</tr>
<tr>
	<th>작성일</th>
	<td><%=bbs.getWdate() %></td>
</tr>
<tr>
	<th>조회수</th>
	<td><%=bbs.getReadcount() %></td>
</tr>
<tr>
	<th>내용</th>
	<td>
		<textarea rows="10" cols="65" readonly="readonly"><%=bbs.getContent() %></textarea>
	</td>
</tr>

</table>	
</div>

<%
MemberDto mem = (MemberDto)session.getAttribute("login");
if(mem == null){
%>
	<script type="text/javascript">
	alert("로그인 해 주십시오");
	location.href = "login.jsp";
	</script>
<%
}
%>

<h2>답글</h2>
<div align="center">
<form action="bbsanswerAf.jsp" method="post">
<input type="hidden" name = "seq" value="<%=bbs.getSeq()%>"> 
<table border="1">
<col width="200"><col width="500">
<tr>
	<th></th>
	<td>
		<input type="text" name="id" size="50" readonly="readonly" value="<%=mem.getId()%>">
	</td>
</tr>

<tr>
	<th>제목</th>
	<td>
		<input type="text" name="title" size="50px">
	</td>
</tr>

<tr>
	<th>내용</th>
	<td>
		<textarea rows="20" cols="50px" name="content"></textarea>
	</td>
</tr>

<tr>
	<td colspan="2" align="center">
		<input type="submit" value="답글작성완료">
	</td>	
</tr>
</table>


</form>
</div>










</body>
</html>