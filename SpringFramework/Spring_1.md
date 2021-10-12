## Spring 1 

####  스프링 프레임워크 MVC , SprSample1 실습(web.xml, DispatcherServlet 설정, Controller 사용)

### 1. 스프링 프레임워크 MVC 

**스프링 프레임워크 MVC 구성요소**

- DispatcherServlet

  : Client의 요청을 전달 받고, 해당 요청에 대한 적합한 Controller를 선택해서 Client의 요청을 전달하는 역할을 한다. 또한 컨트롤러가 반환한 값을 View에 전달해서 알맞은 응답을 생성한다.  

- HandlerMapping

  : Client로부터 요청받은 URL을 처리할 Controller를 선택한다. 

- Controller

  : Client의 요청을 처리하고 그 결과를 DispatcherServlet에 전달한다. 

- ModelAndView

  : Controller가 처리한 결과 및 뷰 선택에 필요한 정보를 저장한다. 

- ViewResolver

  : Controller의 처리 결과를 전달할 view를 지정한다. 

- View

  : Controller의 처리 결과 화면을 생성한다. 

  

**스프링 프레임워크 MVC 기능 수행 과정** 

(1) 브라우저가 DispatcherServlet에 URL로 접근해서 해당 정보를 요청한다.

(2) HandlerMapping에서 해당 요청과 매핑되는 Controller가 있는지 요청한다.

(3) 매핑된 Controller에게 처리를 요청한다.

(4) Controller가 Client의 요청을 처리한 결과와 View의 이름을 ModelAndView에 저장해 DispatcherServlet으로 반환한다.

(5) DispatcherServlet에서는 Controller에서 보내온 View 이름을 ViewResolver로 보내서 해당 View를 요청한다. 

(6) ViewResolver는 요청한 View를 보낸다. 

(6) View의 처리결과를 DispatcherServlet으로 보낸다. 

(7)  DispatcherServlet은 최종적으로 요청받은 결과를 브라우저로 전달한다. 



### 2. SprSample1 

- **web.xml**

```xml
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://xmlns.jcp.org/xml/ns/javaee" xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd" version="4.0">
  <display-name>sprSample1</display-name>
  <welcome-file-list>
    <welcome-file>index.html</welcome-file>
    <welcome-file>index.htm</welcome-file>
    <welcome-file>index.jsp</welcome-file>
    <welcome-file>default.html</welcome-file>
    <welcome-file>default.htm</welcome-file>
    <welcome-file>default.jsp</welcome-file>
  </welcome-file-list>
  
<!-- Servlet을 묶어주는 기능(DispatcherServlet)  --> 
<servlet>
    <servlet-name>dispatcherServlet</servlet-name> 
    <!-- 이름이 dispatcherServlet인 서블릿을 생성한다. -->
    <servlet-class>
        org.springframework.web.servlet.DispatcherServlet
    </servlet-class>  
    
    <init-param>
        <param-name>contextConfigLocation</param-name>
        <!-- contextConfigLocation에 위치한 파일들과 함께 초기화 된다 -->
        <param-value>
            /WEB-INF/spring/dispatcherServlet.xml 
            <!-- bean정보를 담고 있는 설정 파일의 경로를 지정한다. -->
        </param-value>
    </init-param>
    
    <load-on-startup>1</load-on-startup>
    <!-- load-on-startup은 여러가지 서블릿이 로드될 때 순서를 정의하는 것으로서, 정수값이 작은 서블릿부터 먼저 로드된다. -->

    
</servlet>  

<servlet-mapping>
    <servlet-name>dispatcherServlet</servlet-name>
    <!-- <url-pattern>/</url-pattern> -->
    <url-pattern>/home</url-pattern>
<!-- /home으로 들어오는 클라이언트의 요청을 dispatcherServlet이 처리 하도록 설정한다. -->
   
</servlet-mapping> 
</web-app>
```

url-pattern(/home) 코드로 매핑값이 home인 모든 클라이언트의 요청들을 dispatcherServlet이라는 이름을 가진 서블릿이 처리하도록 넘기는 것이다. 그리고나서  dispatcherServlet에서 /WEB-INF/spring/dispatcherServlet.xml의 경로를 따라 dispatcherServlet.xml 파일을 찾는 것이다. 



- **dispatcherServlet.xml**

```xml
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:context="http://www.springframework.org/schema/context"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-4.3.xsd">
    
<!-- spring MVC annotation(주석문, 지시어)을 사용하기 위한 설정 -->
    <context:annotation-config/>
    
<!-- 사용자의 view의 위치와 확장명을 설정 -->
    <bean id="viewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
<!-- InternalResourceViewResolver는 Controller에서 return된 ModelAndView 객체에서 선언된 view page를 지정해주는 class이다. -->
<!-- 실질적인 jsp를 호출하는데 필요한 친구이다. -->
        <property name="prefix" value="/WEB-INF/views/"></property> 
<!-- views(jsp, html)의 경로 -->
        <property name="suffix" value=".jsp"></property>  <!-- 확장자명은 jsp -->
    </bean>
<!-- prefix는 접두사, suffix는 접미사
         prefix는 컨트롤러에서 jsp파일을 찾을 때 기준이 되는 폴더이고,
         suffix는 컨트롤러에서 설정한 뷰페이지명 끝에 붙여주는 확장자명을 가르킨다. -->
    <!-- InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
         viewResolver.prefix = "/WEB-INF/views";
     -->
     
     <context:component-scan base-package="mul.com.a"/>
<!-- 패키지명(mul.com.a)에 해당되는 경로에서 컨트롤러를 스캔하라는 것이다 -->

</beans>
```



- **HomeController.java(컨트롤러)**

```java
package mul.com.a;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller     // <- annotation
public class HomeController {
	
	private static Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping("home") // 매핑값이 home으로 들어왔을 때 다음의 메소드를 수행한다.
	public String helloMethod(Model model) { 
        // Model <- 짐을 포장하기 위한 interface이다.
	//	System.out.println("HomeController helloMethod()");
		logger.info("HomeController helloMethod() " + new Date());
		
         String name = "홍길동";
		
		model.addAttribute("name", name); // 짐싸! == setAttribute
		
		return "home";  
 // retrun값이 "home"이면 뷰페이지명이 home인 home.jsp로 가라!
 // home.jsp로 가는 이유 ->  dispatcherServlet.xml의 bean설정에서 그렇게 정하였다. 
	}
	
	@RequestMapping(value = "home.do", method = RequestMethod.GET)
// hello.jsp 뷰페이지에서 값을 주고받는다.
	public String home(Model model, Human human) {
		logger.info("HomeController home() " + new Date());
		
// hello.jsp에서 입력한 human의 정보를 받아서 다음과 같이 출력한다.
		System.out.println("name:" + human.getName());
		System.out.println("age:" + human.getAge());
		
// hello.jsp에서 입력해서 받아온 human의 정보를 다시 짐을 싸서 hello.jsp로 이동한다.
		model.addAttribute("human", human);
		return "hello";
	}
}
```



- **index.jsp**

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<a href="home">home으로 이동</a>

<a href="hello.do">hello.do로 이동</a>

</body>
</html>
```



- **home.jsp**

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h2>Home JSP</h2>

<%
// HomeController.java에서 보낸 name값을 받는다.    
String name = (String)request.getAttribute("name"); 
%>

<!-- 받아온 name을 출력 -->    
이름:<%=name %><br><br>

</body>
</html>
```



- **hello.jsp**

```jsp
<%@page import="mul.com.a.Human"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h2>Hello JSP</h2>

<%
// HomeController.java에서 보내준 name값을 받아온다.    
String name = (String)request.getAttribute("name");
%>

<!-- 받아온 이름값인 name을 출력 --> 
이름:<%=name %><br><br>

<!-- 받아온 이름값인 name을 출력 --> 
이름:${name}     <!-- EL tag(jsp에서 사용가능), Core tag -->

<br><br>

<!-- 입력한 이름값과 나이값을 가지고 home.do(HomeController.java)로 이동한다 -->
<form action="home.do" method="get">

이름:<input type="text" name="name">
<br>
나이:<input type="text" name="age">
<br>
<input type="submit" value="전송">
</form>
    
<%
// 전송했던 이름값과 나이값을 home.do로부터 Human의 객체로 다시 받아온다.    
Human hu = (Human)request.getAttribute("human"); 
if(hu == null){ 
// 처음 hello.jsp로 왔을 때 입력한 값을 전송한 적이 없기 때문에 hu는 null이다.
	hu = new Human("김영희", 22); // 초기값.
}
%>

이름:<input type="text" value="<%=hu.getName() %>">
<!-- 처음에는 "김영희"가 value값으로 나타나며, 데이터를 입력받아 전송을 누르면, 이후에는  입력한 이름의 값이 value값으로 나타난다.-->    
<br>
나이:<input type="text" value="<%=hu.getAge() %>">
<!--위의 이름과 동일 -->    
<br><br>

이름:<input type="text" value="${human.name }">
<!-- el tag 사용 시에는 컨트롤러에서 받아오는 dto의 데이터값을 ${}안에 바로 적는다.  -->    
<br>
나이:<input type="text" value="${human.age }">

</body>
</html>
```



- **Human.java**

```java
package mul.com.a;

import java.io.Serializable;

public class Human implements Serializable{
	
	private String name;
	private int age;
	
	public Human() {
		
	}

	public Human(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Human [name=" + name + ", age=" + age + "]";
	}
}
```

