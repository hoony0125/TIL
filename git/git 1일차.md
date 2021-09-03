## Git

- 분산 버전 관리 시스템이다. 

  \- 버전관리란 코드의 히스토리(버전)을 관리하는 도구이며,

  \- 개발되는 과정을 기록하고, 파악하는 것이다. 

  \- 버전관리를 통해 이전 버전과의 변경사항을 비교 분석할 수 있다. 

- 만약 로컬 컴퓨터에서 작업을 하다가 버전1 파일을 두번 수정해서 버전2 파일이 생겼을 때, git은 버전1 파일을 저장하고, 이 후에 버전2 파일을 저장할 때는 변경사항만을 저장한다. 이렇게 하면, 관리할 용량이 작다는 이점이 있다. 또한 내 로컬 컴퓨터에 저장된 데이터들이 날아가도, git 서버 저장소에 저장이 되어 있기 때문에 데이터를 복구할 수 있다. 
- **git hub**는 이러한 분산 버전 관리 시스템에서 저장소 역할을 하는 웹 서비스이다. 



## Unix / Linux 기본 명령어 

#### 1. ls - 현재 위치의 폴더, 파일 목록 보기 

#### 2. cd (change directory) - 현재 위치 이동하기

- `cd desktop `

  \- desktop이라는 하위 디렉토리로 이동 

  \- 일부를 검색하고 Tab키를 누르면 바로 뜬다. 

- `cd ..` 

  \- 상위 폴더로 이동 

  \- `cd ../..`으로 두개 상위의 디렉토리로 이동도 가능하다 

- `cd des`에서 탭키를 누르면 . desktop가 떠서 자동완성기능을 쓸 수 있다. 

  `. `하나는 현재위치를 가리킨다. 

- 절대경로와 상대경로 

  ​	\- 절대경로는 c드라이브부터의 위치를 나타내며,  `cd "C:\Users\kkh87\Desktop\TIL\markdown"` 의 형태로 작업 디렉토리를 변경할 수 있다. 

  ​	\-  참고로 `~`(틸다기호) 는 C:\Users\kkh87(사용자 폴더)까지를 의미한다 

#### 3. 생성하기 

- mkdir [폴더명] - 폴더 만들기 
- touch [파일명] - 파일 만들기 
- rm [파일명] - 파일 하나를 삭제 
- rm -r [폴더명] -  폴더를 삭제 

#### 4. 창 정리하기

- clear 

  

## Commit 

#### 1. git init 

- 명령어로 로컬 저장소를 생성 

- git init을 하면 .git 이 숨김파일로 생성된다. 

- .git 디렉토리에 버전 관리에 필요한 모든 것이 들어있다. 



#### 2. Commit 하는 방법

- 특정 버전으로 남긴다 = "커밋(commit)한다"

  

- 3가지 영역 (Working Directory | Staging Area | Repository) 

  > **Working Directory**   `git add .`  또는 `git add 파일명`  \> Staging Area 

  \- 내가 작업하고 있는 실제 디렉토리 

  \- 여기에 있는 파일들은 git으로부터 untracked 된 상태

  > **Staging Area**    `git commit -m "Commit 01(메시지)"` \> Repository

  \- 커밋으로 남기고 싶은, 특정버전으로 관리하고 싶은 파일이 있는 곳 

  \- 여기에 있는 파일들은 staged 된 상태

  > **Repository**

  \- 커밋들이 저장되는 곳 

  \- 여기에 있는 파일들은 committed 된 상태

  

- **commit 하는 순서 요약**

  1.  `git init` 

     \- 로컬 저장소 생성 

  2. `git add .` 또는  `git add Basic.py`

     \- 현재 디렉토리의 모든 파일(add.) 또는 원하는 파일(add 파일명)을 Staging Area에 올리기 

  3. `git commit -m "Commit 01" `

     \- "Commit 01"이라는 메시지를 남기고, Repository에 올리기 

  

- commit 관련 명령어 

1. `git status`

   \- 현재 git의 상태 확인, 필수적인 단계는 아니다.

2. `git log`

   \- commit 메시지 commit 고유의 아이디를 시간 순서대로 볼 수 있다. 

   <img src="git%20%EB%AA%85%EB%A0%B9%EC%96%B4%20(git%20bash%20%EB%98%90%EB%8A%94%20powershell).assets/image-20210902153852741.png" alt="image-20210902153852741" style="width:60%;" />

   

3.  `git diff 고유아이디1 고유아이디2` 

   \- 고유아이디는 git log에서 볼 수 있는 커밋마다 주어지는 아이디이다. 

   \- 긴 아이디 중에서 앞 4자리만 있으면 git diff 명령어를 사용할 수 있다. 

   \- commit과 다른 commit간의 차이를 비교해서 알려준다. 

   \- ex) git diff 373c 1543

   

   ## Repository

   #### 1. Repository 생성

   \- 아래 사진처럼 좌측 상단의 new 또는 우측 상단의 + 버튼을 눌러서 New repository를 만들어준다. 

   ![image-20210902175420202](C:/Users/kkh87/AppData/Roaming/Typora/typora-user-images/image-20210902175420202.png)



![image-20210902175727272](C:/Users/kkh87/AppData/Roaming/Typora/typora-user-images/image-20210902175727272.png)

\- 위 사진에서 레포지터리 즉, 저장소의 이름을 적어준다. 

\- Description 은 설명을 추가하고 싶으면 적으면 된다. 적지 않아도 무방하다. 

\- 내 코드를  공개하기 싫다면, Private으로 관리해도 된다. 참고로 Private은 원래 유료였으나 ms에서 github를 인수하고 무료로 풀렸다고 한다!  

\- Add a README file은 체크해도 안해도 상관없다. 안하고 만들면 따로 VSCode에서 만들 수 있기 때문에 알아서 선택하면 된다. 

\-Add a README file을 체크하면 main을 디폴트 브랜치로 만든다는 문구가 뜨는데, 이게 무슨 뜻이냐면, README파일을 생성할건데, 이때 그 파일을 넣을 브랜치가 필요한데 main이라는 브랜치에 만들어서 거기에 파일을 넣겠단 뜻이다.

\- Add.gitignore 경우도 마찬가지이다. 나중에 추가할 수 있으므로 편한대로 선택하면 된다. 



#### 2. Remote repository와 연결

\- 저장소를 만들었으면, 해당 Repository로 가면 https://github.com/hoony0125/[레포지터리명] 이 있다. 이 주소를 복사해서 VSCode로 와서 명령어를 입력해주면 remote repository와 local repository를 연결할 수 있다. 



- `git remote add origin https://github.com/hoony0125/[레포지터리명]`

  \- 여기서 레포지터리 주소를 orgin이라는 이름으로 추가한다는 뜻이다. orgin은 다른 이름으로 수정은 가능하지만, 수정하지 않고 쓰는 것이 관례이다. 이제 orgin이라는 이름으로 이 주소의 레포지터리의 코드를 업로드하거나 다운로드가 가능하다. 

- `git push -u origin master`

  \- git push는 레포지터리에 작업한 내용을 올리는 명령어이다.

  \- 이름이 orgin인 원격 레포지터리(저장소)에 로컬에서 작업한 master 브랜치를 push하는 것이다. 

- `git push origin master`

  \- 로컬 레포지터리의 최신 커밋을 원격 레포지터리로 push한다.

- `git push  -u orgin master `

  \- \-u == set upstream 이라는 옵션

  \- 한번 git push -u origin master로 push를 하고나면 그 다음부터는 git push만 써도 된다. 



### 💥 여기서 잠깐!  `git clone`💥

나의 경우에는 remote하는 과정에서 레포지터리 관련 에러가 나서 로컬 레포지터리와 원격 레포지터리를 연결이 되지 않았다. 이러한 경우에 다르게 연결할 수 있는 방법이 있는데, 그것이 바로 `git clone`이다. 

- `git clone https://github.com/hoony0125/[레포지터리명].git `

  \- 원격 저장소에 있는 레포지터리를 로컬 저장소로 그대로 복사해오는 명령어이다. 

  \- `git clone 레포지터리주소.git .` 

  이렇게 띄어쓰기를 하고 .을 하게 되면 현재위치에(현재의 디렉토리에) 폴더를 추가로 만들지 않고, 로컬 저장소 바로 복사해올 수 있다.

- 그 과정을 순서대로 정리해보았다. 

  1. 프로젝트를 진행할 디렉토리를(test폴더) 바탕화면에 만들었다고 가정해보자. 

  2. github에 작업할 레포지터리를 생성한다. 

  3. 바탕화면에서 우클릭으로 git bash창을 띄우고, `cd test`로 작업할 디렉토리를 설정한다.

  4.  `git clone https://github.com/hoony0125/[레포지터리명].git `을 한다

     (비어있는 레포지터리를 복사해왔기 때문에 warning이 뜨지만 정상이다.)

  5.  바탕화면에 있는 test폴더로 이동해서 레포지터리명으로 생성된 폴더가 있는지 확인한다. 

- 이 후 작업에 대해 설명을 하자면, 

\- clone은 원격저장소를 로컬로 복사하는 것이기 때문에 init을 할 필요가 없다. 

\- 바탕화면에 생성된 폴더를 열어보면, 원격저장소에 있는 레포지터리가 폴더로 들어와있다 (그 안에.git도 생성되어 있다) 

\- 이렇게 하고 작업을 할 때, 유의해야할 점이 있는데, **test폴더에서 작업을 하는 것이 아니라, test에서 `cd [레포지터리명]`을 하고 작업을 시작해야 한다.**

\-  이제부터는 git add부터 이어나가면 된다. 다만, 이미 원격에서 복사를 받았기 때문에

`git remote add origin https://github.com/hoony0125/[레포지터리명]`는 해주지 않아도 된다. 

\- git add > git commit > push 순서로 해주면 된다.

\- 참고로 clone을 한 경우에는 `git push`만 타이핑 해줘도 push가 이뤄진다. 

## TIL(Today I Learned) 프로젝트

매일매일 내가 배운 것을 마크다운으로 정리해서 문서화하고, 이를 github에 올리는 것이다. 

잔디를 빼곡하게 채우는 작업을 통해 내가 얼마나 꾸준히 학습할 수 있는지를 보여줄 수 있는 지표이기도 하다. 따라서 하루 이틀하고 마는 것이 아니라, 정말 장기적으로, 꾸준하게 할 수 있는 것이 중요하다. 

오늘은 처음이라 좀 정갈하고 빡세게 TIL을 작성했지만, 이렇게 하면 금방 지쳐서 장기전으로 가지는 못할 것 같다. 내가 배우는 것도 정말 중요하지만, 내가 어떤 내용을 배웠고, 얼마나 꾸준히 해왔는지를 어필하기 위해서도 TIL은 매일 작성해야 한다. 그러므로 앞으로는 형식을 잘 갖춰서 하기보단, 실용적이고, 가독성은 좋으면서, 그렇다고 또 시간을 많이 요하지는 않는 방향으로 TIL을 작성해보도록 노력해야겠다.  