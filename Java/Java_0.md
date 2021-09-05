## Java 작업환경 구축 방법

#### (Java설치, 시스템 설정,  eclipse설치 및 기본 설정까지)



### 0.

이 부분은 기존에 Java가 최신버전으로 깔려있던 나에게 한정된  단계이다. 나는 Java se 16.0.2 버전을 미리 깔아놨는데, 사실 이러한 최신 버전은 오류 수정이 안된 부분이 많다거나 하는 문제들이 있다고 한다. 더불어 현업에서는 대부분 이전 버전들을 많이 사용하기 때문에, 최신 버전이 있다면 과감히 삭제한다. 삭제는 제어판 프로그램 추가/제거에서 삭제하면 되고, 내 PC > 로컬디스크(C:) > Program files 에서 JAVA 폴더가 사라졌는지 확인하고, 안됐으면 그 부분도 삭제하면 된다. 



### 1. 본격적으로 Java 다운로드 하기 

좀 더 알아보기 쉽게 캡쳐한 것을 보며, 설명하려고 한다. 먼저 오라클을 검색해서 오라클 메인 페이지를 띄운다.

![img](https://postfiles.pstatic.net/MjAyMTA4MjVfMjEw/MDAxNjI5ODk0NzcwNzM0.Knk-qSTSfP-jsrAaT4g3wdIQMYIyHKCzJiQhKpKHe8kg.VXepbrlOguV6XUIsqDbPP3ePqKtNKNlTlhWacgEuQC8g.PNG.boribro0425/image.png?type=w773)

이 후, 제품 클릭 후 Java 클릭 



![img](https://postfiles.pstatic.net/MjAyMTA4MjVfMjgg/MDAxNjI5ODk0ODI2Nzg1.GOPxUtGcUaRBu7OnrODrF44FUTJwyKhN9tPQ6d38HZUg.VRz5kQzq9KdKl7XiLUwVpgihbcEptL26QMmFyvCOwP8g.PNG.boribro0425/image.png?type=w773)

우측 상단의 Java 다운로드 클릭 



![img](https://postfiles.pstatic.net/MjAyMTA4MjVfOTIg/MDAxNjI5ODk0OTA5NzAy.x2JXX-bXYLP1achZiK7AH5I26m_WtUfCcBbgeR2xFz4g.GyC2vkXQzidvJDHStSJd-2Jg60Pm6-83bvcwo7oOoFog.PNG.boribro0425/image.png?type=w773)

이 후 최상단부터는 Java의 최신버전들이 뜨는데, 2021년 8월 기준 Java SE 16이 최상단에 있다. 스크롤을 내리다 보면, Java SE 8이 있는데, 나는 이 버전을 다운 받을 것이기 때문에 여기서 JDK Download를 클릭한다.



![img](https://postfiles.pstatic.net/MjAyMTA4MjVfNjQg/MDAxNjI5ODk1MTQyMTc2.22Pzh3qF1p61cWrfyCHpWJxRB5Fbi1V6XQHYU56K8x8g.Z_IRP4XZQiKmLKmZnfXcIZy-jPU1iaOfqDrM1ImCwIkg.PNG.boribro0425/image.png?type=w773)

다음 페이지에서 스크롤을 내려 본인 운영체제에 맞는 파일을 다운 받으면 된다. 아 참 그런데 이 과정에서 다운받을 때 로그인이 필요할 수 있다. 그러므로 간단하게 회원가입을 마치고 로그인한 후 다운로드를 진행하면 된다. 



이 후 Java 설치 과정부터는 이미 Java가 설치되어 있기 때문에 말로 추가설명을 하자면, 

저 파일을 실행시켜서 그대로 따라가면 된다. 또한 중간에 JRE다운로드 관련된 파일도 다운로드 하도록 뜨는데, 그것도 다운로드 해주면 된다. 다운로드가 다 잘 되었는지 확인해야 하는데, 내 PC > 로컬디스크(C:) > Program files > JAVA에서 아래 두 폴더가 생겼는지 확인을 한다. 



![img](https://postfiles.pstatic.net/MjAyMTA4MjVfMjYw/MDAxNjI5ODk1NTE3MjEw.lT9opm65qDLdT3BenyLDG2B7LXDGvdRFv-JJZWP84okg.sx0AjB8ZTVeZhQ6TiZEuk9828rdm3v8hyXAlgngV0log.PNG.boribro0425/image.png?type=w773)

두 폴더가 잘 생성이 되었다면, 일단 1단계는 성공! (참고로 나는 설치과정에서 뭐가 누락이 됐는지, JDK 관련 폴더만 생성이 되어서 다시 다 지우고 새로 깔았다...)



### 2. window에서 설정하기

![img](https://postfiles.pstatic.net/MjAyMTA4MjVfMjc2/MDAxNjI5ODk1ODQ0MDAy.WVFBG78iYRdeqDBphFigvdm8YDpcqEUbKX8W9-L2zFgg.uQRqa62wyNZiVu23RPk8gR47MCM-73rhjg49GTAxq50g.PNG.boribro0425/image.png?type=w773)

시작버튼을 눌러서 우클릭 후에 시스템을 클릭하고, 그 페이지에서 우측에 보이는 고급 시스템 설정을 눌러 시스템 속성의 고급 탭을 띄우고, 환경변수를 클릭한다. 



![img](https://postfiles.pstatic.net/MjAyMTA4MjVfNzIg/MDAxNjI5ODk2MDQ1NDQ2.iKlh3bg8bRiU1qbgLoU_4iKlgsFZ5_ci8K2UVSR9ANIg.4y79igTHVy3rYvr9ulxzxR0CjvbFZdxwXK2LOAto9_og.PNG.boribro0425/image.png?type=w773)

환경변수에서 위에 있는 새로만들기 버튼을 누르고, 변수 이름을 JAVA_HOME으로, 변수 값은 jdk 1.8.0~ 폴더가 있는 폴더 위치를 복사해서 붙여넣기 해주고 확인버튼을 누른다. 



![img](https://postfiles.pstatic.net/MjAyMTA4MjVfMTc5/MDAxNjI5ODk2MzE4OTQ3.dGSaABQp4_X6kD4gb2F_9jXWdZB0UfuECkaaqkMj_Bgg.kWFJ_PTZvbJF2pwXlIOB6vfX2ueocSkwYqLl2evJhz4g.PNG.boribro0425/image.png?type=w773)

다음은 아래의 시스템 변수에서 Path를 누르고 편집을 누르면 우측처럼 환경변수 편집이 뜨는데, 여기서 새로 만들기를 클릭하고 %JAVA_HOME%\bin 를 입력해준다. 그리고 해당 내용을 위로 이동을 눌러 제일 위로 올려주면 끝! 여기까지만 하고 확인버튼을 계속 누르고 마치면 끝이다. 

다만 선택적으로 하나의 단계를 추가하자면, JRE_HOME 과 해당위치를 위 사용자 변수에서 JAVA_HOME에서 했던 것처럼 새로 만들고 Path에서 편집으로 %JRE_HOME%\bin 를 추가해주고 두번째로 위로 올려준다. JRE도 올려주는 이유는 드물긴 하지만, 각기 다른 노트북과 컴퓨터로 같은 작업물을 내는 과정에서 혹시나, 정말 혹시나 모를 에러나 오류가 뜰 수 있기 때문에 밟는 단계라고 한다.

### 3. Eclipse 설치하기 

구글에 이클립스를 검색하고 이클립스에 들어간다. 나는 구글에서 첫 페이지에서 나오는 다운로드 창으로 바로 들어왔는데, 주소는 이거다. https://www.eclipse.org/downloads/

![img](https://postfiles.pstatic.net/MjAyMTA4MjVfMTQ1/MDAxNjI5ODk3MTc5MTE5.MDfbT_wCrx-UTNG2eEv8BV_ettVoCbL_lrdBtdl5O1Yg.a2cb3bdv6dqAXFBP69OM2Mg1f7b8GF7GHUZJnMS6j8kg.PNG.boribro0425/image.png?type=w773)

들어와서 좀 내려서 Download Packages를 클릭한다



![img](https://postfiles.pstatic.net/MjAyMTA4MjVfMTk5/MDAxNjI5ODk3Mjc2MjMx.AVjAWA93idHSndgKh0_g7HqIK1Ghi8A0R3x-kCPwlBQg.M2Wo6crR81wcxf2EaYep2E450qBrrhkvngXH_PPl2g0g.PNG.boribro0425/image.png?type=w773)

여기서 스크롤을 조금만 내리다보면, 우측에 날짜별 버전을 볼 수 있는데, 얘도 Java와 비슷한 맥락으로 최신 버전은 최적화라던지, 기타 새로운 오류 등의 위험성을 가지고 있기 때문에 나는 2020년 12월 버전을 다운로드 하기로 하였다. 



![img](https://postfiles.pstatic.net/MjAyMTA4MjVfNTUg/MDAxNjI5ODk3MzQ0OTYx.33c5iHxTLUHUQRy17IbnNJIwtraebQFKwOYHjtbSovog.VlHuZ-tqP0buTYHlWG8amVJMKoR47c2mwdUK1Y6BynMg.PNG.boribro0425/image.png?type=w773)

다음 들어오면 Eclipse IDE for Enterprise Java Developers가 있다. 여기서 본인에 맞는 운영체제를 클릭한다. 

![img](https://postfiles.pstatic.net/MjAyMTA4MjVfNzIg/MDAxNjI5ODk3MzkyNzM3.n8ZNXDlaoHM8uYMAZRiyWXGKMZQwKcnkJAN1aTbPJiYg.FSCYev7Rh5SL_GoS_e76qUZAKxM7aDaNtshXyoDdP8wg.PNG.boribro0425/image.png?type=w773)

다음으로 들어오면, zip파일 형식의 다운로드 파일이 우리를 기다리고 있다. 저 파일을 다운로드 해주면 된다. 이걸 받아서 압축을 푸는데, 압축을 풀면 eclipse라는 폴더가 나온다. 이 폴더를 로컬디스크(C:)에 위치시킬 수 있으나, 가급적 다른 저장소에 위치시키길 권한다. 이유는 혹여나 해당 프로그램에서 오류가 나면 저장소를 통째로 초기화해야하는 불상사가 생길 수 있기 때문이다. 그러므로 메인 저장소가 아닌 서브에 위치시키길 권한다. 그러면 이제 Eclipse설치도 완료! 이제 eclipse exe를 눌러 실행시키면 된다. 



### 4. Eclipse 작업환경 기초적인 설정

Eclipse를 열었으면 위에 메뉴바에서 Window를 누르면, 맨 밑에 Preferences가 있다. 클릭하면 설정 창이 뜨고, 설정을 검색할 수 있다.

![img](https://postfiles.pstatic.net/MjAyMTA4MjVfMjg1/MDAxNjI5ODk3OTQyNTAx.M5CkiPK-mTxLkXE3J4QGyGa_vVjcZiHVvu1ao08EUe4g.M3G7kb1rDNZZX8bWjlXyOT80EUCKLBX8Ois5PRf5_lUg.PNG.boribro0425/image.png?type=w773)

Spelling을 검색해서 Spelling 설정으로 와서 하단의 encoding에서 other를 눌러 UTF-8로 바꿔주고 Apply를 눌러준다. 



![img](https://postfiles.pstatic.net/MjAyMTA4MjVfMTUw/MDAxNjI5ODk4MDAxMTMy.m6PD4N4avKqHXtXII6b4snVPOuMJRyRBQ1dt7v2C-zwg.eFVwyVGwe91h9ks_C-n9GIFLY1RffRVaba1a-iRvdi8g.PNG.boribro0425/image.png?type=w773)

이번에는 Workspace를 검색하고 들어와서 하단에 Text file encoding에 other를 누르고 UTF-8로 바꿔준다. 위에서나 아래에서 UTF-8로 바꿔주는 이유는 이걸 바꾸지 않으면 프로그램 실행 중에 한글이 깨지기 때문에 이렇게 바꿔주는 것이다. 



![img](https://postfiles.pstatic.net/MjAyMTA4MjVfMzQg/MDAxNjI5ODk4MTIxMzE3.eajK7fQ-ZF6GEYvgWD2fj4CCE6UJ_Po2mhDu0ajFpKYg.fFM8wN143A0E1gCvTdBOOihQa4ju6vO89zl1tLcTl-gg.PNG.boribro0425/image.png?type=w773)

이제 다시 eclipse 프로그램 기본으로 돌아와서 우측에 Open Perspective에 해당되는 버튼을 클릭한 후 Java를 누르고 Open 버튼을 눌러주면, 작업환경 구축은 끝이 난다..! 후후

이외에 추가적으로 font도 바꿀 수 있는데, 이 또한 Window의 Preferences에서 font를 찾아 바꾸면 간단하다.

이렇게 작업환경을 구축해봤는데, 요즘에는 취업해서 현업에 가보면, 예전과는 다르게 알아서 작업환경을 구축하라는 경우가 다반사라고 한다. 언젠가 나도 취뽀하고, 회사에서 설레는 마음으로 작업환경을 구축하기를 바라며,, 그럼20000

