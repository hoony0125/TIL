## SpringBoot 파일 업로드, 다운로드 

### 1. 기본설정 

- **pom.xml**

- **webconfig**

- **application.properties**

  

### 2. 업로드 

#### Backend

- **MediaTypeUtiles.java**

```java
package mul.com.a;

import javax.servlet.ServletContext;

import org.springframework.http.MediaType;

public class MediaTypeUtiles {

	public static MediaType getMediaTypeForFileName(ServletContext servletContext, String filename) {
		String minType = servletContext.getMimeType(filename);
		
		try {
		MediaType mediaType = MediaType.parseMediaType(minType);
		return mediaType;
		}catch (Exception e) {
			return MediaType.APPLICATION_OCTET_STREAM;
		}
	}
}
```



- **PdsController**

\- src > main 에 webapp 안의 <u>upload 폴더</u>를 만들고 경로를 정해준다. 

```java
package mul.com.a.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import mul.com.a.MediaTypeUtiles;

@RestController
public class PdsController {
	
	@Autowired
	ServletContext servletContext;
	
	@RequestMapping(value="/fileUpload", method = RequestMethod.POST)
	public String fileUpload( @RequestParam("uploadFile")	// 프론트에서 name=uploadFile를 받는다
							  MultipartFile uploadFile,
							  HttpServletRequest req, 
							  String message) {
		
		System.out.println("PdsController fileUpload");
		System.out.println("message:" + message );
		// 경로 - tomcat server : 3000에 올리는 것!	
			String uploadPath = req.getServletContext().getRealPath("/upload");	// upload 폴더를 만들어야 돼
        
		// 폴더에도 아래처럼 올릴 수는 있다
		// String uploadPath = "p:\\temp";
					
		// 파일명 취득 
		String filename = uploadFile.getOriginalFilename(); 
        // 이렇게 하면 원본 파일명이 나온다. 
		String filepath = uploadPath + File.separator + filename;
										//  '/'와 동일하게 생각하면 된다. 
		System.out.println(filepath);
		
		try {
			BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(new File(filepath)));
			os.write(uploadFile.getBytes());
			os.close();
			
			// DB input
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return "file upload fail";
		
		}
		return "file upload  success";
	}
}
```



#### Frontend

- **이클립스 Dynamic Web Project 잡아주고 index.html 작성** 

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>

<h2>file upload</h2>

<form id="upload_file_frm">	<!-- 여기서는 method get,post 그리고 멀티파트 설정 안해주고 Ajax의 경우에는 enctype에서 설정해준다 -->
	<input type="file" id="uploadFile" name="uploadFile" accept="*">
	<input type="text" name="message">
	<button type="button" id="uploadFileBtn">파일 업로드 </button>
	<p id="uploadResult"></p>
</form>

<script type="text/javascript">

$('#uploadFileBtn').click(function() {
	
	$.ajax({
		url:"http://localhost:3000/fileUpload",
		type:"post",
		data:new FormData( $("#upload_file_frm")[0] ),
		enctype: 'multipart/form-data',
		processData:false,
		contentType:false,
		cash:false,
		success:function(msg){
			alert('success');
			alert(msg);
		},
		error:function(){
			alert('error')
		}
	});
	
});
</script>
</body>
</html>
```



cf)

중간에 메시지를 컨트롤러 업로드 부분에 파라미터로 추가하였다. 

-> STS 콘솔창에서 메시지를 받아서 출력할 수 있게 된다. 



### 3. 다운로드 부분 순서 

#### Backend

- **PdsController**

```java
	@RequestMapping(value = "/fileDownload")
	public ResponseEntity<InputStreamResource> download(String fileName, HttpServletRequest req)throws Exception {
		System.out.println("PdsController download");
	
	
	// 경로 - tomcat server : 3000에 올리는 것! - 업로드와 경로는 동일하다
		String uploadPath = req.getServletContext().getRealPath("/upload");	
    
    // 폴더에도 아래처럼 올릴 수는 있다
	// String uploadPath = "p:\\temp";
		
		MediaType mediaType = MediaTypeUtiles.getMediaTypeForFileName(this.servletContext, fileName);
		System.out.println("fileName:" + fileName);
		System.out.println("mediaType:" + mediaType);
		
		File file = new File(uploadPath + File.separator + fileName);
		
		InputStreamResource is = new InputStreamResource(new FileInputStream(file));
		
		return ResponseEntity.ok().
				header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
				.contentType(mediaType)
				.contentLength(file.length())
				.body(is);
	}
```



#### Frontend

- **index.html  작성**

```html
<h2>file download</h2>
<button type="button" id="downloadBtn">파일 다운로드</button>

<script type="text/javascript">

$("#downloadBtn").click(function() {
	
	location.href = "http://localhost:3000/fileDownload?fileName=" + "record.html";
	
	/*
	$.ajax({
		url:"http://localhost:3000/fileDownload",
		type:"get",
		data:{ fileName:"record.html" },
		success:function(){
			alert("success");
		},
		error:function(){
			alert("error");
		}
	});
	*/
});

</script>

```

