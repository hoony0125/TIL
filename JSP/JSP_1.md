## JSP 1 

#### JSP, 주석문, 용례, 스크립트 요소

### 1. JSP

서블릿은 자바코드를 기반으로 문자열을 사용해 HTML과 자바스크립트로 화면을 구현했다. 이와 다르게 JSP는 HTML, CSS, JS를 기반으로 JSP 요소들을 사용하여 화면을 구현하는 기술이다. JSP와 관련된 각각의 구성요소에 대해 간단히 알아보고 넘어가자. 

- **JSP의 구성요소**

  HTML 태그, CSS 태그, 자바스크립트 코드

  JSP 기본 태그

  JSP 액션 태그

  개발자가 직접 만들거나 프레임워크에서 제공하는 커스텀 태그 



- **JSP 페이지에서 사용되는 구성요소** 

  디렉티브 태그: 페이지 티렉티브 태그, 인클루드 디렉티브 태그, 태그라이브 디렉티브 태그

  스크립트 요소: 주석문, 스크립트릿, 표현식, 선언식

  표현 언어

  내장 객체

  액션 태그

  커스텀 태그 



-------

### 예시1(주석문, 용례)

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%


%>    
    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<!-- html 주석문 -->	<!-- 크롬 개발자도구에서 보여진다 -->

    
<%-- jsp 주석문 --%> <%-- 크롬 개발자도구에서 안보인다 --%>

    
<%--  

JSP : Java Server Page
	  Server(web)를 통해서 Client form을 생성하는 코드 

    	  request
client	----------> 	WAS(server + container)
		<----------
		  respond
    
클라이언트는 웹 브라우저를 통해 url을 입력해서 요청을 보낸다.
				http://localhost:8090/jspSample1/index.jsp 
				http://10.0.0.200:8090(ip주소)/jspSample1/index.jsp
				http://www.myappwork.co.kr/	-> domain name
				
		
Servlet -> Java 코드 안에 html코드가 들어가 있다.
JSP -> html 코드 안에서 Java를 같이 쓸 수 있다. => 호환이 좋다. 그러나 가독성 면에서 좋지는 않다(두 코드가 같이 쓰이므로)

script + applet = scriptlet(잘 안쓰는 말) == java 영역

    
내장(암시) 객체 : 생성하지 않고 바로 사용 가능한 객체
예) String name = request.getParameter("name") 여기서 request는 내장객체에 해당

--%>

    
<h1>Hello JSP</h1>

<h3>h3 tag</h3>

<p>p tag</p>

<button type="button">버튼</button>


<% 
// java area == 자바 영역(scriptlet)

System.out.println("Hello JSP");	// console창에서 출력 

%>

<%
String str = "Hello JSP!";
int number = 1024;
%>

<p><%=str %></p>	<!-- 여기서 <%=변수명 %> 이 부분이 JSP에서 변수의 값을 출력할 때 사용하는 표현식이다.  -->

<input type="text" size="20" value="<%=number %>">
<br>

<%
// out : web에 출력을 할 수 있는 내장객체, 사용하긴 하지만 많이 하지는 않는다. 

out.println("<h2>" + str + number + "</h2>");	
// 위처럼 되기는 되지만 자바 코드안에 태그가 들어와있는 서블릿의 형태로서 잘 안쓴다. 

%>

<h2><%=str %><%=number %></h2>	<%-- 위의 out을 쓰는 것보다 이런 형태가 편하지 --%>

<%
for(int i = 0; i < 10; i++){
%>	
	<p>hello p tag <%=(i+1) %></p>		<!-- 중간에 html 태그를 사용하기 위해 자바영역을 닫고여는 형태 -->
<%
}
%>
</body>
</html>
```





-----

### 예시2(JSP스크립트 요소)

```jsp
<%@page import="jspSample1.Member"%>
<%@page import="func.UtilClass"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%! 
// 선언부 : 전역변수, class, function
int gl_number=0;	

%>


<%
// 코드부
gl_number++;

int number = 0;
number++;

%>


<!-- value -->
전역변수:<%=gl_number %>
지역변수:<%=number %>


// 선언부에 아래처럼 클래스를 작성해서 할 수도 있긴 하지만 자동완성기능도 지원이 안되서 매우 번거롭다 
//  이것보단 Java Resource에서 클래스를 생성해서 하는 편이 훨씬 편하다. 
<%!
class Human{
	private String name;
	
	public Human(String name){
		this.name = name;
	}
	public String toString(){
		return this.name;
	}
}
%>

<%
Human h = new Human("홍길동");
System.out.println(h.toString());

Member m = new Member("성춘향");	// Member클래스를 외부에서 작성하고 JSP에서 사용 
System.out.println(m.toString());

%>


<%!
// 선언부의 function	-> 선언부에 작성해도 되고, 외부에 class로 빼서 function을 사용해도 된다. 
// 자율적으로 사용하면 된다. 다만, 공개하고 싶지 않은 부분만큼은 가급적 외부로 빼는게 좋겠지?
public int func(int n1, int n2){
	return n1 * n2;
}
%>

<%
System.out.println(func(12,10));

System.out.println( UtilClass.func(13,10));		// UtilClass라는 클래스에서 func라는 함수를 만들어서 JSP에서 사용 
%>

<%=func(12,10) %>
<%=UtilClass.func(13,10) %> 	

</body>
</html>
```

