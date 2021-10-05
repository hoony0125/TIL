## 게시판(답글 가능 형태)

#### 게시물 수정 기능 구현, 게시글 삭제 기능 구현, 게시물 제목 요약 출력하기 

### 0. 구조

#### 자바 클래스

Dao - BbsDao

Dto - BbsDto

#### 데이터베이스

bbs.sql

#### JSP파일 (html 코드)

bbslist.jsp

bbsdetail.jsp

bbsupdate.jsp

bbsupdateAf.jsp

bbsdelete.jsp





### 1. 게시물 수정 기능 구현

- **bbsdetail.jsp에서 수정버튼 구현**

  \- 로그인한 아이디와 작성자 아이디가 같은 경우에만 수정할 수 있도록 한다. 

  \- 수정버튼을 누르면 dto.getSeq()를 받아 onclick으로 updatebbs(seq)함수가 실행되는데, 시퀀스를 가지고 bbsupdate.jsp로 이동한다. 

  

- **BbsDao.java에서 updateBbs(BbsDto dto) 함수 작성**

  \- 쿼리문은 시퀀스를 조건으로 걸고, Title, Content를 UPDATE하는 원리로 작성한다.

  \- 변경사항이 생기면 count를 하고, 0보다 크면 true를 출력해서 true일 경우 업데이트를 하는 형태로 함수를 작성하였다. 



- **bbsupdate.jsp(수정을 하는 페이지)에서 수정 페이지 프론트 구현**

  \- 기본적으로 상단의 JSP태그로 시퀀스, readcount를 받는다. 

  \- form형태로 마지막에 저장을 누르면 bbsupdateAf.jsp로 이동하게끔 작성한다. 

  \- 제목과 내용만 수정이 가능하도록 input태그로 구현하고, 나머지는 값을 받아만 오는 형태로 작성한다. 

  

- **bbsupdateAf.jsp(update의 후속처리 담당 페이지)**

  \- 기본적으로 id, title, content, seq를 상단의 JSP영역에서 변수로 받아준다. 

  \- BbsDao에서 작성한 updateBbs(BbsDto dto)함수를 호출해서 사용하는 페이지이다. 

  \- 업데이트가 정상적으로 이뤄진 경우 bbslist.jsp로 이동하고, 실패할 경우, 다시 수정할 수 있도록 bbsupdate.jsp로 시퀀스를 가지고 간다. 

  



### 2. 게시글 삭제 기능 구현

- **bbsdetail.jsp에서 삭제버튼 구현**

  \- 로그인한 아이디와 작성자 아이디가 같은 경우에만 삭제할 수 있도록 한다. 

  \- 삭제버튼을 누르면 dto.getSeq()를 받아 deletebbs(seq)함수가 실행되는데 시퀀스를 가지고 bbsdelete.jsp로 이동하게 작성한다. 



- **BbsDao.java에서 dao.deleteBbs(int seq) 함수 작성**

  \- 쿼리를 작성하는데, 게시글을 삭제하면 DEL(삭제여부)이 1로 설정되도록 코드를 짠다.

  \- count를 받아서 count가 0보다 크면, true를 출력하고, 아닌 경우에는 false를 출력한다. 



- **bbsdelete.jsp에서 dao.deleteBbs(seq) 함수 실행 및 후속처리** 

  \- 위에서 true가 출력된 경우, 게시글을 정상적으로 삭제한다. 

  \- 삭제가 되거나 안되거나 bbslist.jsp로 이동하게 설정하였다. 

  

- **bbslist.jsp에서 삭제된 게시글에 대한 프론트 구현** 

  \- 조건문을 통해 삭제되지 않은 게시글(getDel()==0)은 정상 출력하고, 아닌 경우는 삭제가 이뤄진다.  

  \- 삭제된 글은 아무런 링크가 걸리지 않고, \*\*\*이 글은 작성자에 의해서 삭제되었습니다\*\*\* 라는 문구가 뜨도록 코드를 작성했다. 





### 3. 게시물 제목 요약 출력하기

- **bbslist.jsp에 상단 JSP 영역에서 dot3(String title) 함수를 작성한다.**

  \- 이 함수는 예를 들어, 제목의 길이가 길어지면 32자까지만 출력하도록 한다고 했을 때, 조건문을 통해 문자열의 길이가 32자 이상이면 0~32자까지 포함시켜 스트링을 만들고 그 뒤에 ...을 추가해주는 함수이다. 



- **bbslist.jsp에 프론트 구현**

  \- 기존에 bbs.getTitle()로 제목 데이터를 받아오는 부분을 dot3()로 감싸주면, 함수 적용 끝! 
