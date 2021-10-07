<%@page import="dto.MemberDto"%>
<%@page import="dto.BbsDto"%>
<%@page import="java.util.List"%>
<%@page import="dao.BbsDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%--
String choice = request.getParameter("choice");
String search = request.getParameter("search");

if(choice == null || choice == null|| search.equals("")){ //search가 null이 들어오거나, 빈문자열이 들어올때
	choice = "";
	search = "";
}


BbsDao dao = BbsDao.getInstance();

////////////////////////////////////////////////////////////////////page부분
// 글의 총수 
int len = dao.getAllBbs(choice, search);
System.out.println("글의 총수: " + len);

// 페이지 수
int bbsPage = len/10; 		// 29/10 => 몫이 2 
if((len % 10) > 0){			// len = 29를 10으로 나눈 나머지가 0보다 크면 
	bbsPage = bbsPage +1;	// 몫에서 한 페이지를 더 추가 
}

// 현재 페이지 -> 페이지들 중에서 현재 위치한 페이지 
String spageNumber = request.getParameter("pageNumber"); // int로 받아야하기 때문에 일단은 spageNumber로 잡는다
int pageNumber = 0;
if(spageNumber != null){
	pageNumber = Integer.parseInt(spageNumber);	//
}
//처음 로그인해서 페이지로 들어오면 pageNumber는 null이야. 그런데 페이지 번호를 누르면 spageNumber에는 값이 들어오게 되서 더 이상은 null이 아니야 그래서 페이지를 눌러서 pageNumber가 null이 아니게 되면 integer로 바꿔줌!
//////////////////////////////////////////////////////////////////////

//List<BbsDto> list = dao.getBbsList(); 
//List<BbsDto> list = dao.getBbsSearchList(choice, search);	// 페이지 넘버까지 받도록 함수를 추가작성해야 한다. 
List<BbsDto> list = dao.getBbsPagingList(choice, search,pageNumber);


--%>   

<% 
String search = (String)request.getAttribute("search");
String choice = (String)request.getAttribute("choice");

int bbsPage = (Integer)request.getAttribute("bbsPage");
int pageNumber = (Integer)request.getAttribute("pageNumber");

List<BbsDto> list = (List<BbsDto>)request.getAttribute("list");

%>



<%!
// 답글 깊이와 image를 추가하는 함수
public String arrow(int depth){
	String rs = "<img src='./image/arrow.png' width = '20px' height='20px'>";
	String nbsp = "&nbsp;&nbsp;&nbsp;&nbsp;";
	
	String ts = "";	// ts는 여백
	for(int i = 0; i < depth; i++){
		ts += nbsp;
	}
	return depth ==0?"":ts + rs;
}

// 제목의 문자열의 길이가 25자를 넘을 때, ...으로 표현 
public String dot3(String title){
	String str = "";
	if(title.length() >= 32){	// 문자열의 길이가 32자 이상이면, 0부터 32까지 포함시켜서 스트링을 만들고
		str = title.substring(0,33);	// 그 뒤에 ...을 추가한다. 
		str += "...";
	}else{						// 문자열의 길이가 32자 미만이면 그냥 title로 가져온다.
		str = title;
	}
	return str;
}
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	// 검색어가 있는 경우
	let search = "<%=search %>";
	if(search == "") return;	
	
	// select 처리 -> 만약 내용으로 설정하고 검색하여도 계속 내용이라고 select가 유지된다. 
	let obj = document.getElementById("choice");
	obj.value = "<%=choice %>";
	obj.setAttribute("selected","selected");
});

</script>


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
<div align="center">
<h2>게시판</h2>



<table border="1">
<col width="70px"><col width="500px"><col width="100px"><col width="100px">
<tr>
	<th>번호</th><th>제목</th><th>정보</th><th>작성자</th>
</tr>

<%
if(list == null || list.size() == 0){
%>
	<tr>
		<td colspan="3">작성된 글이 없습니다</td>
	</tr>
<%
}else{
	
	for(int i = 0;i < list.size(); i++){
		BbsDto bbs = list.get(i);
%>
		<tr>
			<th><%=(i + 1) %></th>
			<td>
				<% 
				if(bbs.getDel()==0){	// 안지워졌을 때
					%>
					<%=arrow( bbs.getDepth() ) %>
					<a href="bbsdetail.jsp?seq=<%=bbs.getSeq() %>"><%=dot3(bbs.getTitle()) %></a>
					<% 
				}else{
					%>
					<font color="red">***이 글은 작성자에 의해서 삭제되었습니다***</font>
					<%
				}
				%>
				
<%--  getTitle을 눌렀을 때, bbsdetail.jsp로 이동하면서 시퀀스넘버를 가져간다--%> 
			</td>
			<td>
			<%=bbs.getRef() %>-<%=bbs.getStep() %>-<%=bbs.getDepth()%>
			</td>
			<td>
				<%=bbs.getId()%>
			</td>
		</tr>
<%
	}
}
%>

</table>

<br>
<%-- 페이지부분 --%>
<%-- 1 [2][3] --%>
<% 
for(int i = 0;i < bbsPage; i++){
	if(pageNumber == i){	// 현재 페이지		1 [2] [3]
		%>
		<span style="font-size: 15pt; color: #0000ff; font-weight: bold;">
			<%=i + 1 %>
		</span>&nbsp;
		<%
	}
	else{					// 그 외의 페이지
		%>    <!--  for문이 돌기 때문에 보여지는 페이지로 2페이지(i+1)를 누르면, 실제로 넘어가는건 1페이지(i)  -->
		<a href="#none" title="<%=i + 1 %>페이지" onclick="goPage(<%=i %>)"
			style="font-size: 15pt;color: #000; font-weight: bold; text-decoration: none;">
			[<%=i + 1 %>]
		</a>&nbsp;
		<%
	}
}
%>
<br><br>
<div align="center">

<select id="choice">
	<option value="title">제목</option>
	<option value="content">내용</option>
	<option value="id">작성자</option>
</select>

<input type="text" id="search" value="<%=search%>">	 <!-- 이렇게 해야 내용으로 검색 후에도 select가 제목으로 돌아가지 않고 내용으로 유지된다. -->

<button type="button" onclick="searchBbs()">검색</button>

</div>

<br>	
	<a href="bbswrite.jsp">글쓰기</a>

</div>

<script type="text/javascript">
function searchBbs() {
	let choice = document.getElementById("choice").value;
	let search = document.getElementById("search").value;
	
//	alert(choice);
//	alert(search);
	location.href = "bbs?param=bbslist&choice=" + choice + "&search=" + search;
}
	

function goPage( pageNum ) {	// 페이징은 검색기능과 같이 가야되기 때문에 코드를 많이 받아온다. 	
	let choice = document.getElementById("choice").value;
	let search = document.getElementById("search").value;
	
	location.href = "bbs?param=bbslist&choice=" + choice + "&search=" + search + "&pageNumber=" + pageNum;
}
		// 페이지 번호를 받아서 bbslist.jsp로 간다. 가는데 문자열로 가. 그래서 위에서 string으로 받고 integer로 바꿔줘야 되는데, 
		// 처음 로그인해서 페이지로 들어오면 pageNumber는 null이야. 그런데 페이지 번호를 누르면 spageNumber에는 값이 들어오게 되서 더 이상은 null이 아니야 그래서 null인 경우에 integer로 바꿔줌!

</script>



</body>
</html>


