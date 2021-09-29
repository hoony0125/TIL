### Servlet 2

#### 포워드, 예시3(포워드, 서블릿 두개, Dto사용), 예시4(web.xml없이 서블릿 작업, 저장공간 Session)

### 1. 포워드 기능 

하나의 웹 애플리케이션은 여러 기능을 합쳐서 하나의 프로그램으 실행한다. 회원관리, 주문관리, 게시판 관리 등 각각의 기능을 각각의 서블릿이 수행한다. 그런데 종종 서블릿끼리 또는 JSP를 연동하여 작업을 해야하는 경우도 발생한다. 하나의 서블릿에서 다른 서블릿이나 JSP와 연동하는 것을 포워드(forward)라고 한다. 쉽게 말하면, 포워드는 다른 서블릿 또는 JSP로 요청을 전달하는 역할을 한다. 또한 이 요청은 추가데이터를 포함시켜서 전달할 수 도 있다. 이러한 포워드 방법에는 redirect방법, refresh방법, location방법, dispatch방법이 있다. 이 중에서도 redirect방법과 dispatch방법을 사용한 포워드를 예시3에서 살펴보자.



-----------------

### 예시3(포워드, 서블릿 두개, Dto사용)

- **HelloServlet.java**

  ```java
  package serv;
  
  import java.io.IOException;
  import java.io.PrintWriter;
  
  import javax.servlet.RequestDispatcher;
  import javax.servlet.ServletException;
  import javax.servlet.http.HttpServlet;
  import javax.servlet.http.HttpServletRequest;
  import javax.servlet.http.HttpServletResponse;
  
  import dto.HumanDto;
  
  public class HelloServlet extends HttpServlet{
  
  	@Override
  	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
  		
  		System.out.println("HelloServlet doGet()");
  		
  		
  		String name = req.getParameter("name");
  		System.out.println(name);					// 웹에 출력한게 아니라 콘솔에 출력한 것
  		
  		int number = Integer.parseInt(req.getParameter("number"));
  		System.out.println(number);
  		
  		
  		/*
  		 * resp.setContentType("text/html; charset = utf-8");
  		 * 
  		 * PrintWriter pw = resp.getWriter();
  		 * 
  		 * pw.println("<html>");
  		 * 
  		 * pw.println("<head>"); pw.println("<title>HelloServletHtml</title>");
  		 * pw.println("</head>");
  		 * 
  		 * pw.println("<body>"); pw.println("<h3>Hello Servlet</h3>");
  		 * 
  		 * 
  		 * pw.println("</body>");
  		 * 
  		 * pw.println("</html>");
  		 * 
  		 * pw.close();
  		 */
  		
  		
  		HumanDto human = new HumanDto(name, number);
  		req.setAttribute("human", human);	// 짐 싸서! -> req.getRequestDispatcher("world"); 여기에서 이동해 
  		
          RequestDispatcher rd = req.getRequestDispatcher("world");	
          //sendRedirect처럼 이동시켜주는 역할을 하지만 차이가 있다. 
  		rd.forward(req, resp);    // dispatch방법은 짐을 가지고 간다. --> WorldServlet으로 
  		
          
  		// resp.sendRedirect("world");			
          // servlet으로 이동 
          // location으로 helloservlet으로 이동해 와서 world를 받고 WorldServlet으로 간 것! 	
          // Redirect방법은 이동하지만 짐을 가지고 가지 않는다.
  		// resp.sendRedirect("index.html");		// web으로 이동 
  		
  
  		
  	}
  
  	@Override
  	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
  
  	}
  	
  }
  
  ```

  

- **WorldServlet.java**

  ```java
  package serv;
  
  import java.io.IOException;
  import java.io.PrintWriter;
  
  import javax.servlet.ServletException;
  import javax.servlet.http.HttpServlet;
  import javax.servlet.http.HttpServletRequest;
  import javax.servlet.http.HttpServletResponse;
  
  import dto.HumanDto;
  
  public class WorldServlet extends HttpServlet{
  
  	@Override
  	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
  		
  		// HelloServlet에서 짐싸서 이동했지? 이제 짐 풀어
  		HumanDto dto = (HumanDto)req.getAttribute("human");
  		
  		
  		resp.setContentType("text/html; charset = utf-8");
  		
  		PrintWriter pw = resp.getWriter();
  		
  		pw.println("<html>");
  		pw.println("<head>");
  		pw.println("<title>HelloServletHtml</title>");
  		pw.println("</head>");
  		
  		pw.println("<body>");
  		pw.println("<h3>World Servlet</h3>");
  		
  		if(dto != null) {
  			pw.println("<p>" + dto.toString() + "</p>");
  		}
          
  		pw.println("</body>");
  		pw.println("</html>");
  		pw.close();
  
  	}
  
  	@Override
  	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
  		
  	}
  }
  ```

  

- **HumanDto.java**

  ```java
  package dto;
  
  public class HumanDto {
  	private String name;
  	private int number;
  	
  	public HumanDto(String name, int number) {
  		super();
  		this.name = name;
  		this.number = number;
  	}
  	
  	@Override
  	public String toString() {
  		return "HumanDto [name=" + name + ", number=" + number + "]";
  	}	
  }
  // 참고로 이 예시에서 getter, setter 생성은 필요없으니 패스 
  ```

  

- **web.xml**

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee
                        http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
    version="4.0">
    
  <!-- servlet 설정 -->
  <servlet>
  	<servlet-name>helloServlet</servlet-name>
  	<servlet-class>serv.HelloServlet</servlet-class>
  </servlet>  
  
  <servlet-mapping>
  	<servlet-name>helloServlet</servlet-name>	
  	<url-pattern>/location</url-pattern>				
  </servlet-mapping>  
  
  <servlet>
  	<servlet-name>world</servlet-name>
  	<servlet-class>serv.WorldServlet</servlet-class>
  </servlet>
  
  <servlet-mapping>
  	<servlet-name>world</servlet-name>
  	<url-pattern>/world</url-pattern>
  </servlet-mapping>  
    
  </web-app>
  ```

  

- **index.html**

  ```html
  <!DOCTYPE html>
  <html>
  <head>
  <meta charset="UTF-8">
  <title>Insert title here</title>
  </head>
  <body>
  	
  <!-- 
  	Web ------------------ > Servlet(java코드 사용) : 언제든 DB를 호출할 수 있다. 	
  		 데이터(짐)을 갖고 이동
  	html
  	<a href=""></a>					GET방식
  	<form action="">				GET방식, POST방식
  	
  	JavaScript
  	location.href = ""				GET방식
  	
  	Java
  	sendRedirect			Servlet -> Servlet		 Servlet-> Web
  	forward  
  --> 
  
  <a href = "location?name=홍길동">Hello Servlet으로 이동</a>
  
  <br><br>
  
  name:<input type="text" id="name">
  <br>
  number:<input type="text" id="number">
  <br>
  <button type="button" onclick="btnclick()">Hello Servlet으로 이동 </button>
  
  
  <script type="text/javascript">
  //location.href = "location?name"+ "성춘향";
  
  function btnclick() {
  	let name = document.getElementById("name").value;
  	let number = document.getElementById("number").value;
  	
  	// 검사 (자바스크립트는 검사를 해서 보내준다.)
  	
  	location.href = "location?name=" + name + "&number=" + number;		// location으로 helloservlet으로 이동 
  }
  
  </script>
  
  </body>
  </html>
  ```





----------------

### 예시4(web.xml없이 서블릿 작업, 저장공간 Session)

- **index.html**

  ```html
  <!DOCTYPE html>
  <html>
  <head>
  <meta charset="UTF-8">
  <title>Insert title here</title>
  </head>
  <body>
  
  <!--  
  저장공간(Server, Client)
  
  cookie : client에 저장. JavaScript에서 사용 
  		 예를 들어, id 저장
  
  session : server에 저장. Java에서 사용 
  		  기한을 설정(세션이 만료되었습니다.)
  		  예를 들어, 사용자 정보를 저장   
  -->
  
  <a href="location">hello servlet으로 이동</a>
  
  </body>
  </html>
  ```

  

- **HelloServlet.java**

  ```java
  package serv;
  
  import java.io.IOException;
  import java.io.PrintWriter;
  
  import javax.servlet.ServletException;
  import javax.servlet.annotation.WebServlet;
  import javax.servlet.http.HttpServlet;
  import javax.servlet.http.HttpServletRequest;
  import javax.servlet.http.HttpServletResponse;
  import javax.servlet.http.HttpSession;
  import javax.servlet.http.HttpSessionEvent;
  
  import dto.HumanDto;
  
  @WebServlet("/location")								// web.xml 파일 만드는 것 대신에 이런 식으로도 가능하다! 둘 다 알아두기
  public class HelloServlet extends HttpServlet{
  
  	@Override
  	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
  		System.out.println("HelloServlet doGet()");
  		
  		
  		
  		  resp.setContentType("text/html; charset = utf-8");
  		  PrintWriter pw = resp.getWriter();
  		  pw.println("<html>");
  		  
  		  pw.println("<head>"); 
  		  pw.println("<title>HelloServletHtml</title>");
  		  pw.println("</head>");
  		  
  		  pw.println("<body>"); 
  		  
  		  pw.println("<h3>Hello Servlet</h3>");
  		  
  		  // 세션을 생성 
  		  HttpSession session = req.getSession();
  		  
  		  // 세션에 저장 
  		  session.setAttribute("name", "giant");  	// 반드시 영어로 작성하기 (앞 string 부분)
  		  
  		  // 세션에서 산출 
  		  String name = (String) session.getAttribute("name");
  		  
  		  
  		  
          
  		  // 세션에 저장
  		  HumanDto human = new HumanDto("홍길동",1001);
  		  
  		  session.setAttribute("user", human);
  		  session.setMaxInactiveInterval(10);		// 60초 동안 저장 
  		  
  		  // 세션에서 산출
  		  HumanDto h = (HumanDto)session.getAttribute("user");
  		  
  		  // 세션 오브젝트를 삭제
  		  // session.removeAttribute("name");
  		  
  		  // 세션 전부 삭제 
  		  // session.invalidate();
  		  
  		  pw.println("<p>name:" + name + "</p>");
  		  pw.println("<p>human:" + h.toString() + "</p>");
  		  
  		  pw.println("</body>");
  		  pw.println("</html>");
  		  pw.close();
  	}
  
  	@Override
  	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
  	}
  }
  ```



- **HumanDto.java**

  ```java
  package dto;
  
  public class HumanDto {
  
  	private String name;
  	private int number;
  	
  	public HumanDto() {
  	}
  	public HumanDto(String name, int number) {
  		super();
  		this.name = name;
  		this.number = number;
  	}
  	@Override
  	public String toString() {
  		return "HumanDto [name=" + name + ", number=" + number + "]";
  	}	
  }
  ```

