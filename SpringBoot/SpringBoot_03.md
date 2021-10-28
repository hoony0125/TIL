## SpringBoot 로그인, 회원가입 구현 기본 

### 1. Backend

- **MEMBER 테이블 생성**

- **Member.xml**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="mul.com.a.dao.MemberDao">

           <!-- 파라미터로 들어오는 것은 String 하나 / java.lang.String을 String으로만 적어도 된다. -->
<select id="idcheck" parameterType="java.lang.String" resultType="java.lang.Integer">	<!-- select는 리턴값이 존재(resultType) -->
	SELECT COUNT(*)	
	FROM MEMBER
	WHERE ID=#{id}
</select>

<insert id="addMember" parameterType="mul.com.a.dto.MemberDto">
	INSERT INTO MEMBER(ID, PWD, NAME, EMAIL, AUTH)
	VALUES(#{id}, #{pwd}, #{name}, #{email}, 3)
</insert>

<select id="login" parameterType="mul.com.a.dto.MemberDto" resultType="mul.com.a.dto.MemberDto">
	SELECT ID, NAME, EMAIL, AUTH
	FROM MEMBER
	WHERE ID=#{id} AND PWD=#{pwd}
</select>

</mapper>

```



- **MemberDto**

```java
package mul.com.a.dto;

import java.io.Serializable;

public class MemberDto implements Serializable {
	
	private String id;
	private String pwd;
	private String name;
	private String email;
	private int auth;
	
	public MemberDto() {
		// TODO Auto-generated constructor stub
	}
	
	public MemberDto(String id, String pwd, String name, String email, int auth) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.email = email;
		this.auth = auth;
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



- **MemberDao**

```java
package mul.com.a.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper; 
import org.springframework.stereotype.Repository;

import mul.com.a.dto.MemberDto;

@Mapper
@Repository
public interface MemberDao {
	
	int idcheck(String id);
	int addMember(MemberDto dto);
	MemberDto login(MemberDto dto);
}

```



- **MemberService**

```java
package mul.com.a.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mul.com.a.dao.MemberDao;
import mul.com.a.dto.MemberDto;

@Service
@Transactional
public class MemberService {

	@Autowired
	private MemberDao dao; // private은 안붙여도 됨 
	
	public boolean idcheck(String id) {
		int n = dao.idcheck(id);
		return n>0?true:false; // n이 0보다 크면(중복된다는 뜻) true, 아니면 false 출력	
	}
	
	public boolean addMember(MemberDto dto) {
		int n = dao.addMember(dto);
		return n>0?true:false;
	}
	
	public MemberDto login(MemberDto dto) {
		return dao.login(dto);
	}
}

```



- **MemberController**

```java
package mul.com.a.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mul.com.a.dto.MemberDto;
import mul.com.a.service.MemberService;

@RestController
public class MemberController {

	@Autowired
	MemberService service;
	
	@RequestMapping(value="/idcheck", method = RequestMethod.POST)	// 테스트할 때는 잠시 GET으로 체크
	public String idcheck(String id) {
		System.out.println("MemberController idcheck");
		System.out.println("id:"+id); // url에서 /idcheck?id=aaa를 입력했을 때 콘솔에 id:aaa가 출력 
		
		boolean b = service.idcheck(id);
		System.out.println(b);
		if(b) { // 중복이 있다
			return "YES"; 
		}else { // 중복이 없다
			return "NO"; 
		}
	}
	
	@RequestMapping(value="/addmember", method = RequestMethod.POST)
	public String addmember(MemberDto dto) {
		System.out.println("MemberController addmember");
		System.out.println(dto.toString());
		
		boolean b = service.addMember(dto);
		if(b ==true){
			return "OK";	// YES든 뭐든 무방하다
		}else
			return "NG";
	}
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public MemberDto login(String id, String pwd) {				
    // public MemberDto login(MemberDto mem) 으로 하는 방식도 있다.
		System.out.println("MemberController login");
		
		MemberDto dto = service.login(new MemberDto(id, pwd, "", "", 0));
		return dto;
	}
}
```





### 1.2 Frontend

- **index.html**

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<script type="text/javascript">
location.href = "login.html";
</script>

</body>
</html>
```



- **login.html**

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<style type="text/css">
#app{
	margin: auto;
	margin-top: 60px;
	width: 60%;
	border: 3px solid #8ac007;
	padding: 10px;
}
</style>

</head>
<body>

<h1>login</h1>


<div id="app">	
	<table border="1">
	<tr>
		<td>아이디</td>
		<td>
			<input type="text" id="id" placeholder="아이디입력" size="20">
		</td>
	</tr>
	<tr>
		<td>패스워드</td>
		<td>
			<input type="text" id="pwd" placeholder="패스워드입력" size="20">
		</td>
	</tr>
	
	<tr>
		<td colspan="2">
			<button type="button" id="login">login</button>		
			<!-- 회원가입으로 이동 -->
			<button type="button" onclick="account()">회원가입</button>
		</td>
	</tr>
	</table>	
</div>

<script type="text/javascript">
function account() {
	location.href = "regi.html";
}

$("#login").click(function(){
	$.ajax({
		url:"http://localhost:3000/login",
		type:"post",
		data:{ id:$("#id").val(), pwd:$("#pwd").val() },
		success:function(resp){
		//	alert('success');
		//	alert(JSON.stringify(resp));	 // 단순히 alert로 정보를 받아서 보려는 것임
		
			if(resp == ""){
				alert("id나 password를 확인해 주십시오");				
			}
			else{ // login 성공!
				alert(resp.name + "님 환영합니다");
			
				// session에 저장 setAttribute( , ) getAttribute()
				sessionStorage.setItem("login", JSON.stringify(resp)); // 셋팅을 할 때 문자열로 넣어줘야 하기 때문에 JSON.stringify로 적음 / <-서버에 저장하는 것이 아닌 클라이언트에 저장
				
				location.href = "bbslist.html";			
			}
		
		},
		error:function(){
			alert('error');
		}
	});
});
</script>
</body>
</html>
```



- **regi.html**

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<style type="text/css">
#app{
	margin: auto;
	margin-top: 60px;
	width: 60%;
	border: 3px solid #8ac007;
	padding: 10px;
}
</style>

</head>
<body>

<h1>회원가입</h1>

<div id="app">	
	<form id="frm">

	<table border="1">
	<tr>
		<td>아이디</td>
		<td>
			<input type="text" id="id" name="id" placeholder="아이디입력" size="20">
			<p id="idcheck"></p>
			<button type="button" id="idcheckbtn">중복확인</button>
		</td>
	</tr>
	<tr>
		<td>패스워드</td>
		<td>
			<input type="text" id="pwd" name="pwd" placeholder="패스워드입력" size="20">
		</td>
	</tr>	
	<tr>
		<td>이름</td>
		<td>
			<input type="text" id="name" name="name" size="20">
		</td>
	</tr>
	<tr>
		<td>이메일</td>
		<td>
			<input type="text" id="email" name="email" size="20">
		</td>
	</tr>	
	
	<tr>
		<td colspan="2">
			<button type="button" id="account">회원가입</button>
		</td>
	</tr>	
	
	</table>
	</form>	
</div>

<script type="text/javascript">
$(document).ready(function () {
	
	$("#idcheckbtn").click(function () {
	//	alert('idcheckbtn');
	
		// id체크
	
		$.ajax({
			url:"http://localhost:3000/idcheck",   // 컨트롤러의 idcheck로 간다
			type:"post",
			data:{ id:$("#id").val() },
			success:function( resp ){
			//	alert('success');
			
				if(resp.trim() == "YES"){
					$("#idcheck").html("사용중인 아이디입니다");
					$("#id").val("");
				}else{
					$("#idcheck").html("사용할 수 있는 아이디입니다");
				}			
			},
			error:function(){
				alert('error');
			}
		});
	});
	
	$("#account").on("click",function () {	// 이렇게 적어도 똑같음
		
		let params = $("#frm").serialize();
		
		$.ajax({
			url:"http://localhost:3000/addmember",
			type:"post",
			data:params,
			success:function( resp ){
				//alert('success');
				if(resp=="OK"){
					alert("가입성공")
					location.href="login.html";
				}else{
					alert("가입이 되지 않았습니다.")
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

