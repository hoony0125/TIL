<%@page import="dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
String id = request.getParameter("id");
System.out.println("id:" + id);			// 데이터를 잘 가져오는지 확인


MemberDao dao = MemberDao.getInstance();
boolean b = dao.getId(id);

if( b == true){
	out.println("NO");		// 사용할 수 없는 경우에 NO
}else{
	out.println("YES");		// 사용할 수 있는 경우에 YES
}

%>