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
