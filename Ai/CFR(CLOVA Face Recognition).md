# CFR(CLOVA Face Recognition)

![image-20211103171921372](CFR(CLOVA%20Face%20Recognition).assets/image-20211103171921372.png)

![image-20211103171929575](CFR(CLOVA%20Face%20Recognition).assets/image-20211103171929575.png)

![image-20211103171935315](CFR(CLOVA%20Face%20Recognition).assets/image-20211103171935315.png)



\- CFR은 얼굴인식인데, 유명인의 얼굴을 넣어서 그 사람의 이름을 알려줄 수 있고, 그냥 사람의 얼굴을 넣어서 표정이나, 예상 나이, 성별 등을 알려줄 수도 있다. 



### Backend

- **NaverAiController.java**

```java
	@RequestMapping(value = "/cfr_fileUpload", method = RequestMethod.POST)
	public String cfr_fileUpload(   @RequestParam("uploadFile")
								MultipartFile uploadFile, 
								HttpServletRequest req,
								String title) {
		System.out.println("PdsController cfr_fileUpload");		
		System.out.println("title:" + title);
		
		// 경로
		// server : 3000
		String uploadPath = req.getServletContext().getRealPath("/upload");
		// 폴더
		//String uploadPath = "d:\\temp";
		
		// 파일명 취득
		String filename = uploadFile.getOriginalFilename();
		String filepath = uploadPath + File.separator + filename;		
									//   '/'
		System.out.println(filepath);		

		try {
			BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(new File(filepath)));
			os.write(uploadFile.getBytes());
			os.close();
			
		} catch (Exception e) {			
			e.printStackTrace();			
			return "fail";
		}
		
		// Naver AI
		String resp = NaverCloud.processCFR(filepath, title);	
		
		return resp;
	}
```





### Frontend

- **CFR.html**

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>

<h2>CFR file upload</h2>

<form id="upload_file_frm">	
	<input type="file" id="uploadFile" name="uploadFile" accept="*">
		
	<select name="title">
		<option value="celebrity">얼굴 인식</option>
		<option value="face">표정 감지</option>
	</select>
	
	<button type="button" id="uploadFileBtn">파일 업로드</button>
	<p id="uploadResult"></p>
</form>

<div id="msg"></div>

<script type="text/javascript">
$("#uploadFileBtn").click(function() {
	
	$.ajax({
		url:"http://localhost:3000/cfr_fileUpload",
		type:"post",
		data:new FormData( $("#upload_file_frm")[0] ),
		enctype: 'multipart/form-data',
		processData:false,
		contentType:false,
		cash:false,
		success:function(msg){
			alert('success');
			alert(msg);
		
		//	let json = JSON.parse(msg);
		//	$("#msg").text(json.text);		
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

