# Object Detection (객체 감지)

<img src="Object%20Detection%20(%EA%B0%9D%EC%B2%B4%20%EA%B0%90%EC%A7%80).assets/image-20211103172106953.png" alt="image-20211103172106953" style="zoom:80%;" />



\- 사진을 업로드해서 그 사진에 어떤 객체가 있는지 감지하고 알려주는 기능이다.



### Backend

- **NaverAiController.java**

```java
	// Object Detection 객체인식 
	@RequestMapping(value = "/obj_detect", method = RequestMethod.POST)
	public String obj_detect(@RequestParam("uploadFile")
								MultipartFile uploadFile, 
								HttpServletRequest req) {
		
		System.out.println("PdsController obj_detect");			
		
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
		
		String text = NaverCloud.objectDetection(filepath);		
		return text;
	}
```



### Frontend

- **objectDectection.html**

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>

<h2>Object Detection upload</h2>

<form id="upload_file_frm">	
	<input type="file" id="uploadFile" name="uploadFile" accept="*">	
	<button type="button" id="uploadFileBtn">파일 업로드</button>
	<p id="uploadResult"></p>
</form>

<hr>

<img alt="이미지없음" src="" id="img">

<h2>판별된 객체</h2>
<div id="obj">
</div>

<script type="text/javascript">
$("#img").hide();

$("#uploadFileBtn").click(function() {
	
	// 파일명을 취득하기 위한 코드
	let filePath = $("#uploadFile").val();	
	let lastDot = filePath.lastIndexOf("\\");
	let filename = filePath.substring(lastDot + 1);
//	alert(filename);	
	
	$.ajax({
		url:"http://localhost:3000/obj_detect",
		type:"post",
		data:new FormData( $("#upload_file_frm")[0] ),
		enctype: 'multipart/form-data',
		processData:false,
		contentType:false,
		cash:false,
		success:function(msg){
			alert('success');
			alert(msg);
		
			let json = JSON.parse(msg);
			alert(json.predictions[0].detection_names);
			
			$("#obj").text("");
			$.each(json.predictions[0].detection_names, function (i, item) {
				$("#obj").append(item + "<br>");
			});
			
			$("#img").show();
			$("#img").attr("src", "http://localhost:3000/upload/" + filename);
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

