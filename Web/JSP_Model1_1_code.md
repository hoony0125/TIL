## 로그인, 회원가입 기능 구현 코드 

- **Member.sql**

  ```sql
  CREATE TABLE MEMBER(
  	ID VARCHAR2(50) PRIMARY KEY,
  	PWD VARCHAR2(50) NOT NULL,
  	NAME VARCHAR2(50) NOT NULL,
  	EMAIL VARCHAR2(50) UNIQUE,
  	AUTH NUMBER(1) NOT NULL
  );
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
  
  <%
  response.sendRedirect("login.jsp");
  %>
  
  </body>
  </html>
  ```

  

- **login.jsp**

  ```jsp
  <%@ page language="java" contentType="text/html; charset=UTF-8"
      pageEncoding="UTF-8"%>
  <!DOCTYPE html>
  <html>
  <head>
  <meta charset="UTF-8">
  <title>login</title>
  
  <style type="text/css">
  .center{
  	margin: auto;
  	width: 60%;
  	border: 3px solid;
  	padding: 10px;	
  }
  </style>
  
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="http://lab.alexcican.com/set_cookies/cookie.js" type="text/javascript" ></script>
  </head>
  <body>
  
  <h2>login page</h2>
  
  <div class="center">
  
  <form action="loginAf.jsp" method="post">
  
  <table border="1">
  <tr>
  	<th>아이디</th>
  	<td>
  		<input type="text" id="id" name="id" size="20"><br>
  		<input type="checkbox" id="chk_save_id">save id
  	</td>
  </tr>
  <tr>
  	<th>패스워드</th>
  	<td>
  		<input type="password" name="pwd" size="20">
  	</td>
  </tr>
  <tr>
  	<td colspan="2">
  		<input type="submit" value="login">
  		<button type="button" onclick="account()">회원가입</button>
  	</td>
  </tr>
  
  </table>
  </form>
  </div>
  
  <script type="text/javascript">
  function account() {
  	location.href = "regi.jsp";
  }
  /*잠깐! 리마인드하기
  	session : java -> server 회원정보, 방문횟수 = Object가 저장 
  	cookie : javascript -> client쪽에서 저장, id저장 = String 저장
  	쿠키를 사용하기 위해서는 자바스크립트 관련 스크립트 태그를 헤드에 추가해줘야 한다. 
  */
  
  let user_id = $.cookie("userId");
  if(user_id != null){
  	$("#id").val( user_id );
  //	$("#chk_save_id").attr("checked", "checked");
  	$("#chk_save_id").prop("checked", true);
  // 앞은 attribute명, 뒤는 value // 풀이해보면, checked가 checked 벨류를 가지면 id에 user_id를 올려라 
  // prop를 써도 되고 attr을 써도 된다. 근데 attr은 false인 경우를 표현하기가 애매해서 false 표현할 때는 prop가 낫다
  
  }
  // 쿠키를 저장하기 위한 코드
  $("#chk_save_id").click(function() {
  //	alert('check click');	
  	if( $("#chk_save_id").is(":checked") ){		// 체크가 된 경우가 true
  		
  		if( $("#id").val().trim() == "" ){ 
  			alert('id를 입력해 주십시오');
  			$("#id").val("");
  			$("#id").focus();
  			$("#chk_save_id").prop("checked", false);
  		}
  		else{
  			$.cookie("userId", $("#id").val().trim(), { expires:7, path:'./' });	
  		}		
  	}
  	else{		// 체크가 안되어 있는 경우엔 쿠키를 제거 
  		$.removeCookie("userId", { path:'./' });
  	}
  });
  </script>
  </body>
  </html>
  ```

  

- **regi.jsp**

  ```jsp
  <%@ page language="java" contentType="text/html; charset=UTF-8"
      pageEncoding="UTF-8"%>
  <!DOCTYPE html>
  <html>
  <head>
  <meta charset="UTF-8">
  <title>regi</title>
  
  <style type="text/css">
  .center{
  	margin: auto;
  	width: 60%;
  	border: 3px solid;
  	padding: 10px;	
  }
  </style>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  
  </head>
  <body>
  
  <h2>회원가입</h2>
  <p>회원가입을 환영합니다</p>
  
  <div class="center">
  
  <form action="regiAf.jsp" method="post">
  
  <table border="1">
  <tr>
  	<th>아이디</th>
  	<td>
  		<input type="text" name="id" id="id" size="20"><br>
  		<p id="idcheck" style="font-size: 8px"></p>
  		<input type="button" id="btn" value="id확인">
  	</td>
  </tr>
  <tr>
  	<th>패스워드</th>
  	<td>
  		<input type="text" name="pwd" size="20">
  	</td>
  </tr>
  <tr>
  	<th>이름</th>
  	<td>
  		<input type="text" name="name" size="20">
  	</td>
  </tr>
  <tr>
  	<th>이메일</th>
  	<td>
  		<input type="text" name="email" size="20">
  	</td>
  </tr>
  <tr>
  	<td colspan="2" align="center">
  		<input type="submit" value="회원가입">
  	</td>
  </tr>
  </table>
  </form>
  </div>
  <script type="text/javascript">
  $(document).ready(function() {
  	
  	$("#btn").click(function() {
  		
  		$.ajax({
  			url:"getId.jsp",
  			type:"post",
  			data:{id:$("#id").val()},	// 이 데이터를 가지고 getId.jsp로 출장을 가라
  			success:function(resp){		// resp 위치는 아무거나 써도 된다. 충돌만 안나면 된다
  				//alert('success');
  				//alert(resp.trim());
  				
  				if(resp.trim() == "YES"){
  					$("#idcheck").css("color", "black");
  					$("#idcheck").html("이 ID는 사용할 수 있습니다.");
  				}
  				else{
  					$("#idcheck").css("color", "red");
  					$("#idcheck").html("사용중인 ID입니다.");
  					$("#id").val("");	// 친절하게 지워주기
  					$("#id").focus();	// 더 친절하게 포커스도 잡아주기 
  				}
  			},
  			error:function(){
  				alert('error');
  			}
  		});
  	});
  });
  </script>
  </body>
  </html>
  ```

  

- **regiAf.jsp**

  ```jsp
  <%@page import="dto.MemberDto"%>
  <%@page import="dao.MemberDao"%>
  <%@ page language="java" contentType="text/html; charset=UTF-8"
      pageEncoding="UTF-8"%>
  <% request.setCharacterEncoding("utf-8"); %>
  
  <%
  String id = request.getParameter("id");
  String pwd = request.getParameter("pwd");
  String name = request.getParameter("name");
  String email = request.getParameter("email");
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
  // 싱글톤으로 해놔서 어디서든 부를 수 있다. 
  
  MemberDto dto = new MemberDto(id, pwd, name, email, 0);
  boolean isS = dao.addMember(dto);
  if(isS == true){
  %>
  	<script type="text/javascript">
  	alert("성공적으로 가입되었습니다");
  	location.href = "login.jsp";
  	</script>
  <%
  }else{
  %>
  	<script type="text/javascript">
  	alert("다시 기입해 주십시오");
  	location.href = "regi.jsp";
  	</script>
  <%
  }
  %>
  </body>
  </html>
  ```

  

- **MemberDto.java**

  ```java
  package dto;
  
  import java.io.Serializable;
  
  public class MemberDto implements Serializable { // 직렬화(순서대로 정렬)
  	
  	
  	 private String id;
  	 private String pwd;
  	 private String name;
  	 private String email;
  	 private int auth;  // 사용자와 관리자를 구분할 수 있는 번호를 줌 예를 들어 사용자3, 관리자1
  	
  	 public MemberDto() {
  		 
  	 }
  	 
  	 public MemberDto(String id, String pwd, String name, String email, int auth) {
  		super();
  		this.id = id;
  		this.pwd = pwd;
  		this.name = name;
  		this.email = email;
  		this.auth = auth;			// 사용자 3 관리자 1로 내 마음대로 설정 
  	}
  
  	public String getId() {
  		return id;
  	}
  
  	public void setId(String id) {
  		this.id = id;
  	}
  
  	public String getPwd() {
  		return pwd;
  	}
  
  	public void setPwd(String pwd) {
  		this.pwd = pwd;
  	}
  
  	public String getName() {
  		return name;
  	}
  
  	public void setName(String name) {
  		this.name = name;
  	}
  
  	public String getEmail() {
  		return email;
  	}
  
  	public void setEmail(String email) {
  		this.email = email;
  	}
  
  	public int getAuth() {
  		return auth;
  	}
  
  	public void setAuth(int auth) {
  		this.auth = auth;
  	}
  
  	@Override
  	public String toString() {
  		return "MemberDto [id=" + id + ", pwd=" + pwd + ", name=" + name + ", email=" + email + ", auth=" + auth + "]";
  	}
  }
  ```

  

- **MemberDao.java**

  ```java
  package dao;
  
  import java.sql.Connection;
  import java.sql.PreparedStatement;
  import java.sql.ResultSet;
  import java.sql.SQLException;
  
  import db.DBClose;
  import db.DBConnection;
  import dto.MemberDto;
  
  public class MemberDao {
  // 이 클래스에 있는 메소드는 getInstance를 통해 어디서든지 부를 수 있다
  // 싱글톤형태
  
  	private static MemberDao dao = null;
  	
  	private MemberDao() {
  		DBConnection.initConnection();
  	}
  	
  	public static MemberDao getInstance() {
  		if(dao == null) {
  			dao = new MemberDao();
  		}
  		return dao;
  	}
  	
  	public boolean addMember(MemberDto dto) {
  		
  		String sql = " INSERT INTO MEMBER(ID, PWD, NAME, EMAIL, AUTH) "
  				+ "    VALUES(?, ?, ?, ?, 3) ";
  		
  		Connection conn = null;
  		PreparedStatement psmt = null;
  		
  		int count = 0;
  		
  		try {
  			conn = DBConnection.getConnection();
  			System.out.println("1/3 addMember success");	
  			
  			psmt = conn.prepareStatement(sql);
  			psmt.setString(1, dto.getId());
  			psmt.setString(2, dto.getPwd());
  			psmt.setString(3, dto.getName());
  			psmt.setString(4, dto.getEmail());			
  			System.out.println("2/3 addMember success");
  			
  			count = psmt.executeUpdate();
  			System.out.println("3/3 addMember success");
  			
  		} catch (SQLException e) {			
  			e.printStackTrace();
  			System.out.println("addMember fail");
  		} finally {
  			DBClose.close(conn, psmt, null);
  		}
  		
  		return count>0?true:false;
  	}
  	
  	// 아이디 중복확인 쿼리 작성 후 dao작성
  	
  	public boolean getId(String id) {
  		String sql = " SELECT COUNT(*) "
  				   + " FROM MEMBER "
  				   + " WHERE ID=? ";
  	
  		Connection conn = null;
  		PreparedStatement psmt = null;
  		ResultSet rs = null;
  		
  		int findId = 0;
  		
  		
  		try {
  			conn = DBConnection.getConnection();
  			System.out.println("1/3 getId success");
  			
  			psmt = conn.prepareStatement(sql);
  			psmt.setString(1, id.trim());
  			System.out.println("2/3 getId success");
  			
  			rs=psmt.executeQuery();	
  			System.out.println("3/3 getId success");
  			
  			if(rs.next()) {
  				findId=rs.getInt(1); 
  			}
  			
  			
  		} catch (SQLException e) {
  			
  			e.printStackTrace();
  			System.out.println("getId fail");
  		}finally {
  			DBClose.close(conn, psmt, rs);
  		}
  		return findId>0?true:false;
  	}
  	
  	
  	public MemberDto login(String id,String pwd) {		
          // loginAf에서 id,pwd를 받는다(?)
  		
  		String sql = " SELECT ID, NAME, EMAIL, AUTH "
  				   + " FROM MEMBER "
  				   + " WHERE ID=? AND PWD=? ";
  		
  		Connection conn = null;
  		PreparedStatement psmt = null;
  		ResultSet rs = null;
  		
  		MemberDto mem = null;
  		
  		
  		try {
  			conn = DBConnection.getConnection();
  			System.out.println("1/3 login success");
  			
  			psmt = conn.prepareStatement(sql);
  			psmt.setString(1,id);
  			psmt.setString(2,pwd);
  			System.out.println("2/3 login success");
  			
  			rs = psmt.executeQuery();
  			System.out.println("3/3 login success");
  			
  			if(rs.next() == true) {
  				String _id = rs.getString("id");		// "id","name","email","auth" 대신 1,2,3,4로 적어도된다. 
  				String _name = rs.getString("name");
  				String _email = rs.getString("email");
  				Integer _auth = rs.getInt("auth");
  				
  				mem = new MemberDto(_id, null, _name, _email, _auth);
  			}
  			
  		} catch (SQLException e) {
  			
  			e.printStackTrace();
  			System.out.println("login fail");
  		}finally {
  			DBClose.close(conn, psmt, rs);
  		}
  		
  		return mem;
  	}
  }
  ```

  

- **getId.jsp** 

  ```jsp
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
  ```

  

- **loginAf.jsp**

  ```jsp
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
  
  if(mem != null){
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
  ```

  
