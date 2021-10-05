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











