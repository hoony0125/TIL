## AJAX 1

#### AJAX, 비동기, AJAX예시

### 1. AJAX, 비동기

AJAX는 

Asynchronous(비동기) Javascript And Xml(Json)의 줄임말로 비동기적인 웹 애플리케이션의 제작을 위해 HTML, CSS, JS, XML, JSON 등을 사용하는 웹 개발 기법이다.

먼저 예시 코드를 보면서 **비동기**가 무엇인지 알아보자 



### 비동기 예시

- **data.html**

  ```html
  <!DOCTYPE html>
  <html>
  <head>
  <meta charset="UTF-8">
  <title>Insert title here</title>
  </head>
  <body>
  <p id = "data1">사과</p>
  <h3 id = "data2">바나나</h3>
  </body>
  </html>
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
  <!--AJAX는 JQuery의 일부이기 때문에 script태그를 달아줘야 한다.  -->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  </head>
  <body>
  <%-- 
  AJAX : Asynchronous(비동기) Javascript And Xml(Json)
  
  --%>
  
  <p id = "demo"></p>
  <br>
  <button type="button">클릭</button>
  
  <script type="text/javascript">
  $(document).ready(function() {
  	$("button").click(function() {
  //$("#demo").load("data.html");
  // data.html로 가지 않고, 버튼을 누르면 데이터만 가져와서 index.jsp 페이지(현재 화면이 유지)에 띄운다.
  // load함수가 중요한게 아니라, 현재 페이지를 유지한 채 데이터를 가져온다는 것이 중요하다(비동기)
  //$("#demo").load("data.html #data1");
  // data.html로 가지 않고, 현재 페이지(index.jsp)에서 데이터를 하나만 가져온다.
  	
  		$("#demo").load("data.jsp","t1=abc&t2=123")
  		// jsp파일에서도 가져오는 것이 가능하다. 
  		
  	});
  });
  
  </script>
  
  
  </body>
  </html>
  ```

  

- **data.jsp**

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
  
  t1=<%=request.getParameter("t1") %>
  <br>
  t2=<%=request.getParameter("t2") %>
  
  </body>
  </html>
  ```

  

-----

### AJAX 기본예시

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>		<!-- JQuery에서 script태그 복붙 -->
</head>
<body>

<p id = "demo"></p>
<br>
<button type="button">클릭</button>

<script type="text/javascript">
$(document).ready(function() {
	$("button").click(function() {
// 현 상태를 유지하면서 외부에서 데이터를 가져오는 것이 ajax! a태그나 form action등 링크로 연결되는 경우는 짐을 가지고 이동하는 것이므로 ajax와 구분된다. 
		$.ajax({	
			////////////////////////////////////////////// 
			url:"data.jsp", 			// 위에 예시 코드인 data.jsp로 출장을 보낸다
			type: "get",				// get이나 post나 ajax에서는 크게 상관없다. 
			//data: "t1=가나다&t2=DEF",	// 가져가는 데이터이다. 
			data:{ t1:"라마바", t2:"GHI"},
            // data는 이런 형태로 적을 수도 있다. 
            // 가져가는 데이터가 없으면 data는 빠져도 된다. 
			
			//////////////////////////////////////////////	윗 덩어리는 send
			// 위는 출장 가기 전, 아래는 출장 가고 난 후
			/////////////////////////////////////////////
			success:function(data/* , status, xhr */){	
     // data는 가져오는 데이터이며 status, xhr은 생략이 가능하다 
				//alert("success");
				//alert(data);
				//alert(status);
				//alert(xhr);
				$("#demo").html(data);	 			// 출력값 t1=라마바 t2=GHI
			},
			error:function(/* xhr, status, error */){		
// 어떤 에러인지 궁금해서 띄어보는 것이며, 여기서 매개변수는 아예 안 적어도 작동을 한다.
				alert("error");
			},
			complete:function(xhr, status){
				//alert("통신종료");
			}
			///////////////////////////////////////////	아래 덩어리는 receive

		});
	});
});


</script>
</body>
</html>
```

