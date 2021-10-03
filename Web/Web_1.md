### 로그인, 회원가입 기능 구현

### 0. 구조

#### 자바 클래스 

Dao - MemberDao.java

Dto - MemberDto.java

DB - DBConnection.java / DBClose.java  



#### 데이터베이스 

Member.sql



#### JSP파일 (html 코드)

index.jsp

login.jsp

loginAf.jsp

regi.jsp

regiAf.jsp

getId.jsp 

(bbslist.jsp)



### 1. index.jsp 만들기(홈으로 가정)	

- 출력과 동시에 login.jsp(로그인 화면, 아이디 패스워드 입력 창)로 이동



### 2. 아이디 저장 기능 구현

- 체크박스에 체크가 되어있고, 공백이 없는 아이디인 경우에 쿠키로 저장

  

### 3. 회원가입 기능 구현 

- **login.jsp(로그인창)에서 regi.jsp(회원가입 정보 입력 창)로 이동** 

  \- regi.jsp에서 회원가입 정보를 입력할 수 있는 form을 작성한다. 

  \-  form action은 regiAf.jsp(입력한 정보가 데이터로 저장되는 페이지)로 설정한다.

- **MemberDto 작성** 

  \- 필드입력, 기본생성자, 매개변수 생성자, Getter/Setter 작성

- **MemberDao 작성** 

  \- 싱글톤으로 dao 객체 생성 및 함수(addMember) 작성

  ✨싱글톤 다시 상기하기✨

  \- 싱글톤은 객체 생성을 컨트롤하기 위한 목적으로 사용하는데, 객체의 갯수를 하나로 제한하는 것이다. 

- **MemberDao의 addMember함수** 

  \- Member.sql파일로 테이블 생성은 이미 되어있다고 생각하자

  \- INSERT INTO 로 데이터에 저장, JDBC(INSERT)에서 학습한 수순 그대로 코딩

- **regiAf.jsp에서 addMember함수 실행** 

  \- MemberDao를 getInstance()로 받고 

  \- dao.addMember(dto) 함수를 통해 회원가입 진행

  \- 회원가입 성공 시 login.jsp로 이동 



### 4. regi.jsp에서 아이디 중복 확인 기능 구현 

- **MemberDao에 getId(String id) 함수 작성** 

  \-  JDBC(SELECT 하나의 데이터만 가져오기)에서 학습한 수순을 밟으면 된다. 

  \- 입력 받은 아이디를 조건으로, 같은 아이디인 데이터를 수를 세서 카운트가 올라가면 true를 출력한다.

  ​	즉, 중복된 아이디가 있으면 true출력, 중복된 아이디가 없으면 false를 출력하는 함수를 작성한다. 

- **getId.jsp 생성 후 함수 적용**

  \- getId()함수에 매개변수를 입력받아 true가 나오면 "NO", false가 나오면 "YES"를 출력하도록 만든다. 

- **regi.jsp에서 스크립트 영역에 ajax코드를 작성**

  \- 방금 작성한 getId.jsp에서 데이터를 가져오도록 코딩을 한다.

  \- 입력받은 데이터(id)를 가져가서 "YES"를 가져오면 사용할 수 있는 ID입니다라는 문구가 뜨고, "NO"를 가져오면 사용중인 ID입니다라는 문구가 뜨게 코딩을 한다. 



### 5. 로그인 기능 구현

- **MemberDao에 login(String id, String pwd) 함수 작성**

  \- 일단 아이디와 비밀번호를 조건으로 입력된 정보와 같으면 로그인이 이뤄지고, ID, NAME, EMAIL, AUTH를 데이터로 받아오는 함수이다. JDBC(SELECT 다수의 데이터 가져오기)에서 학습한 수순을 밟으면 된다. 

  \- 로그인이 이뤄지면 mem이라는 변수에 위에서 언급한 데이터(ID, NAME, EMAIL, AUTH)가 들어가고 return값으로 mem을 출력한다.

- **loginAf.jsp에서 login()함수를 적용**

  \- mem이 null값이 아니라는 것은 곧, 아이디와 비밀번호가 일치하는 그에 맞는 데이터들이 mem에 들어갔다는 것을 의미하므로, if문으로 mem이 null이 아닌 경우에 로그인이 되어 bbslist.jsp(게시판 내용 목록을 보여주는 페이지)라는 게시판이 출력되도록 한다. 

  \- 반대로 로그인이 안될 경우에는 로그인 페이지를 그대로 유지하고 ID, PWD를 확인하라는 알림창을 띄운다. 

  



