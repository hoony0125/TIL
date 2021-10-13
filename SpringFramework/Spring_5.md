## 자료실 

**1. pom.xml에 파일업로드 dependency 추가** 

maven repository 사이트에서 Apache Commons FileUpload 1.3.3버전에서 복사! 

```xml
  	<!-- 파일업로드 -->
	  <dependency>
	    <groupId>commons-fileupload</groupId>
	    <artifactId>commons-fileupload</artifactId>
	    <version>1.3.3</version>
	  </dependency>
```



**2. PdsDto.java 작성**

생성자를 만들 때, 기본 생성자, 모든 멤버변수를 받는 생성자, 외부로부터 받는 정보만 받는 생성자 총 세개를 만든다. 

```java
package mul.com.a.dto;

import java.io.Serializable;

public class PdsDto implements Serializable{
	
	private int seq;
	private String id;
	
	private String title;
	private String content;
	
	private String filename; 		// 원본 파일명 mydata.txt 
	private String newfilename; 	// 변환 파일명 718237817.txt 
	private int readcount;			// 조회수
	private int download;			// 다운로드수
	private String regdate;
	
	public PdsDto() {	
	}

	public PdsDto(int seq, String id, String title, String content, String filename, String newfilename, int readcount,
			int download, String regdate) {
		super();
		this.seq = seq;
		this.id = id;
		this.title = title;
		this.content = content;
		this.filename = filename;
		this.newfilename = newfilename;
		this.readcount = readcount;
		this.download = download;
		this.regdate = regdate;
	}

	public PdsDto(String id, String title, String content, String filename, String newfilename) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.filename = filename;
		this.newfilename = newfilename;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getNewfilename() {
		return newfilename;
	}

	public void setNewfilename(String newfilename) {
		this.newfilename = newfilename;
	}

	public int getReadcount() {
		return readcount;
	}

	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}

	public int getDownload() {
		return download;
	}

	public void setDownload(int download) {
		this.download = download;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	@Override
	public String toString() {
		return "PdsDto [seq=" + seq + ", id=" + id + ", title=" + title + ", content=" + content + ", filename="
				+ filename + ", newfilename=" + newfilename + ", readcount=" + readcount + ", download=" + download
				+ ", regdate=" + regdate + "]";
	}
}
```



**3. 이클립스에서 쿼리문 작성 및 실행** 

```SQL
CREATE TABLE PDS(
	SEQ NUMBER(8) PRIMARY KEY, 
	ID VARCHAR2(50) NOT NULL,			-- 아이디를 외래키로 설정 
	TITLE VARCHAR2(200) NOT NULL,	
	CONTENT VARCHAR2(4000),				-- 자료실이므로 내용은 비어있어도 되게 함 
	FILENAME VARCHAR2(200) NOT NULL,	 
	NEWFILENAME VARCHAR2(200) NOT NULL,
	READCOUNT NUMBER(8) NOT NULL,		-- 조회수
	DOWNCOUNT NUMBER(8) NOT NULL,		-- 다운로드수
	REGDATE DATE NOT NULL
);

CREATE SEQUENCE SEQ_PDS
START WITH 1 
INCREMENT BY 1; 			-- 시퀀스 설정 

ALTER TABLE PDS
ADD CONSTRAINT FK_PDS_ID FOREIGN KEY(ID)
REFERENCES MEMBER(ID)	-- MEMBER(ID)와 연결해서 ID를 외래키로 지정 
```



**4. other > spring > spring bean configuration > file-context.xml 생성 후 upload <bean>작성** 

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
	
	<!-- upload -->
	<bean id="multipartResolver"
		class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
		<property name="maxUploadSize" value="104857600"/>
		<property name="maxInMemorySize" value="102400"/>
		<property name="defaultEncoding" value="utf-8"/>
		<property name="uploadTempDir" value="upload"/>
        <!-- 웹 컨테이너 위치에 upload라는 폴더를 만든 것이다. 업로드한 파일이 올라가는 폴더이다 -->
	</bean>

</beans>
```

이 후 upload폴더를 만들어준다. 



**5. web.xml에 file-context.xml 추가**

```xml
	<init-param>
		<param-name>contextConfigLocation</param-name>
		<param-value>
			/WEB-INF/spring/servlet-context.xml
			/WEB-INF/spring/file-context.xml		<!--추가한 부분-->
		</param-value>
	</init-param>
```



**6. sqls에 Pds.xml 생성 및 작성**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper
  PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
  
<mapper namespace="Pds">

<select id="pdslist" resultType="mul.com.a.dto.PdsDto">
	SELECT SEQ, ID, TITLE, CONTENT, FILENAME, NEWFILENAME,
		READCOUNT, DOWNCOUNT, REGDATE
	FROM PDS
	ORDER BY SEQ DESC
</select>

</mapper>
```



**7. pdslist와 관련하여 PdsControllor / PdsDao / PdsDaoImpl / PdsService / PdsServiceImpl 작성** 



**8. views에 pds폴더 생성 후 pdslist.jsp생성**



 **9. pdswrite.jsp생성** 



**10. PdsUtil파일 생성**

동일한 파일명이 업로드될 경우에 문제가 생기므로, 업로드된 파일에 새로운 파일명을 주기 위한 코드이다. 

```java
package mul.com.a.util;

import java.util.Date;

public class PdsUtil {

	// abc.txt -> 434354353.txt		// static을 붙임으로써 어디서든 클래스명을 통해서 호출이 가능하다
	public static String setNewFileName(String filename) {
		String newfilename = ""; // 리턴해줄 새로운 파일명
		String filepost = "";	// 확장자명
		
		if(filename.indexOf(".") >= 0) { // 확장자명이 있는 경우
			filepost = filename.substring( filename.indexOf(".") );	// abc.txt
			newfilename = new Date().getTime() + filepost;			
		}else {							// 확장자명이 없는 경우
			newfilename = new Date().getTime() + ".back";	
		}
		
		return newfilename;
	}
}
```



**11. Pds.xml에서 upload를 위한 부분 작성 ("id = uploadPds")**

```xml
<insert id="uploadPds" parameterType="mul.com.a.dto.PdsDto">
	INSERT INTO PDS(SEQ, ID, TITLE, CONTENT, FILENAME, NEWFILENAME, READCOUNT, DOWNCOUNT, REGDATE)
	VALUES(SEQ_PDS.NEXTVAL, #{id}, #{title}, #{content}, #{filename}, #{newfilename}, 0, 0, SYSDATE)
</insert>
```



**12. pdsupload와 관련하여 PdsDao, PdsDaoImpl, PdsService, PdsServiceImpl, PdsController 작성** 



**13. Pds.xml에서 getPds 작성 (디테일페이지를 위한)**

```xml
<select id="getPds" parameterType="java.lang.Integer" resultType="mul.com.a.dto.PdsDto">
	SELECT SEQ, ID, TITLE, CONTENT, FILENAME, NEWFILENAME, READCOUNT, DOWNCOUNT, REGDATE
	FROM PDS
	WHERE SEQ=#{seq}
</select>
```



**14. getPds와 관련하여 PdsDao, PdsDaoImpl, PdsService, PdsServiceImpl  작성**



**15. pdslist.jsp에서 제목에 앵커대그 확인 후 PdsController(value = "pdsdetail.do") 작성** 

=> a태그는 무조건 get이라고 한다. 



**16. pdsdetail.jsp 생성** 

상단에 짐부터 가져오고 난 후 <body>부분을 작성한다.



**17. mul.com.a.util에 DownloadView.java를 생성만 한다.** 

실제 다운로드가 이뤄지는 클래스를 작성할 예정이다. 

생성 이유: 다운로드는 다운로드 시에 기존 디테일 페이지는 화면에 유지한 채 창이 하나 뜨면서 다운로드가 이뤄진다. 그리고 다운로드가 다 되어도 디테일 창은 유지된다. 그래서 다른 페이지들과는 차이가 있기 때문에 별도의 작업이 필요하다. 



**18. file-context.xml에서  download <bean>작성** 

```xml
	<!-- download -->
	<bean id="downloadView" class="mul.com.a.util.DownloadView"></bean>
	
	<bean id="downloadViewResolver" class="org.springframework.web.servlet.view.BeanNameViewResolver">
		<property name="order">
			<value>0</value>
		</property>
	</bean>
```



**19. pdsdetail.jsp에서  하단의 자바스크립트영역 작성** 

```jsp
<script type="text/javascript">
function filedownload(newfilename, filename, seq) {
	location.href = "fileDownload.do?newfilename=" + newfilename + "&filename=" + filename + "&seq=" + seq;	
}
</script>
```



**20. PdsController에서 fileDownload.do 부분 작성** 



**21. mul.com.a.util에서 DownloadView.java 작성**

```java
/* 이유: 다운로드는 다운로드 시에 기존 디테일 페이지는 화면에 유지한 채 창이 하나 뜨면서 다운로드가 이뤄진다. 
그리고 다운로드가 다 되어도 디테일 창은 유지된다. 고로 이런 점에서 다른 페이지들과는 차이가 있다. */
package mul.com.a.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

// 실제 다운로드가 되는 클래스
public class DownloadView extends AbstractView{

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("DownloadView renderMergedOutputModel");
		
		File file = (File)model.get("downloadFile");	// == getAttribute
		String filename = (String)model.get("filename");
		int seq = (Integer)model.get("seq");
		
		response.setContentType(this.getContentType());
		response.setContentLength((int)file.length());
		
		// 한글명 파일일 경우에 지금 설정을 안해 주면 정상으로 나오지 않는다
		filename = URLEncoder.encode(filename, "utf-8");
		
		// 다운로드 창
		response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\";");
		// filename은 원본파일명 
		response.setHeader("Content-Transfer-Encoding", "binary;");
		// 인코딩해서 바이너리형식으로 다운로드
		response.setHeader("Content-Length", "" + file.length());
		// 파일의 길이값을 알려줌
		response.setHeader("Pragma", "no-cache;"); 
		// 파일을 저장할 것인지
		response.setHeader("Expires", "-1;");
		// 기한을 의미하는데 -1인 경우 기한없음 
		
		OutputStream out = response.getOutputStream();
		FileInputStream fi = new FileInputStream(file);
		
		// 다운로드가 실제로 완성되는 부분
		FileCopyUtils.copy(fi, out);
		
		// 다운로드 회수를 증가
		
		if(fi != null) {
			fi.close();
		}
	}
}
```

