<%@page import="dto.MemberDto"%>
<%@page import="dto.BbsDto"%>
<%@page import="dao.BbsDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
String sseq = request.getParameter("seq");
int seq = Integer.parseInt(sseq);

BbsDao dao = BbsDao.getInstance();
// readcount를 증가
dao.readcount(seq);

// seq로 BbsDto를 취득
BbsDto dto = dao.getBbs(seq);
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

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


<h2>상세 글 보기</h2>

<div align="center">

<table border="1">
<col width="200px"><col width="400px">

<tr>
	<th>작성자</th>
	<td><%=dto.getId() %></td>
</tr>
<tr>
	<th>제목</th>
	<td><%=dto.getTitle() %></td>	
</tr>

<tr>
	<th>작성일</th>
	<td><%=dto.getWdate() %></td>
</tr>

<tr>
	<th>조회수</th>
	<td><%=dto.getReadcount() %></td>
</tr>

<tr>
	<th>정보</th>
	<td><%=dto.getRef() %>-<%=dto.getStep() %>-<%=dto.getDepth() %></td>
</tr>

<tr>
	<th>내용</th>
	<td>
		<textarea rows="15" cols="90" readonly="readonly"><%=dto.getContent() %></textarea>
	</td>
</tr>

</table>
</div>

<br>

<button type="button" onclick="location.href='bbslist.jsp'">글목록</button>
<button type="button" onclick="answerbbs(<%=dto.getSeq() %>)">답글</button>

<%
if(dto.getId().equals( mem.getId() )){
	%>
	<button type="button" onclick="updatebbs(<%=dto.getSeq() %>)">수정</button>	
	<button type="button" onclick="deletebbs(<%=dto.getSeq() %>)">삭제</button>
	<%
}
%>

<script type="text/javascript">
function answerbbs(seq) {
	location.href = "bbsanswer.jsp?seq=" + seq;
}
function updatebbs(seq) {
	location.href = "bbsupdate.jsp?seq=" + seq;
}
function deletebbs(seq) {
	location.href = "bbsdelete.jsp?seq=" + seq;
}
</script>


</body>
</html>
