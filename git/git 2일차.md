## `git pull`

- `git pull orgin master 또는 main`

  \- remote repository(git hub)에서 변경사항이 있으면 내 로컬 저장소로 가져오겠다는 것이다. 

  

- 로컬 저장소와 원격 저장소(github)을 연결하고 나서, 원격에서 수정한 사항을 로컬로 가져오기 

  1. 원격에서 연필을 누르고 edit file에서 수정을 했다고 가정해보자 

  2. 이제 최신 버전은 원격에서 수정한 파일이다. 

  3. git bash에서 `git pull origin main 또는 master`를 해주면 내 로컬 저장소에도 원격에서 수정한 부분이 반영된다. 

     \- 그러나 이러한 형태의 작업은 , 협업이 아닌 이상 그렇게 권장되지는 않는다. 

  

- 실습 집과 직장에서 일한다고 가정해보자 

  1. 집에서 작업을 하고, push를 해서 github에 작업물이 올라갔다. 

  2. 직장에서 github에 있는 작업물을 git pull orgin master 또는 main을 하면 된다. 

     

- 같은 부분에 작업을 해서 충돌(conflict)이 났을 때  

  1. 원격저장소에서 만약 README.md 파일의 2번째 줄에 작업을 하고 저장을 했다.

  2. 아직 로컬에서는 pull을 안 한 상태에서 동일하게 README.md 파일의 2번째 줄에 작업을 했다. 

  3. 이 후 로컬에서 push를 하면 에러가 나고, push가 제대로 이뤄지지 않는다. 

  4. 로컬에서 pull을 하면 원격과 로컬 사이의 다른 점을 비교해서 로컬에 띄워준다. 

  5. 비교된 부분을 보고 본인이 수정한 후 push를 하면 된다.  

     

- **git push와 git pull origin master? main?**

  \- `git push origin master` (여기서 master는 로컬 기준)

  ​	=> git아 내가 origin으로 내 브랜치를 push할게

  ​	\- 로컬 기준 main인지 master인지 확인 

  \- `git pull orgin master` (여기서 master는 원격 기준)

  ​	=> git 아 내가 원격 저장소의 브랜치에서 pull할게 

  ​	\- 원격 기준 main인지 master인지 확인 

  ## ## git add와 git commit 되돌리기

- `git restore`(git add를 취소하고 싶을 때)

  \-   git add를 해서 Staging Area에 올린 파일을 Working Directory로 다시 내리고 싶을 때

  \- `git restore --staged 파일명` : `git add .` 한상태에서 Working Dircectory를 되돌릴때 사용 (소스 제어에 되돌리기 전후 상태를 비교해서 되돌리기를 결정할 수 있다.)

  \- `git restore README.md ` : 가장 최신의 커밋과 비교해서  워킹디렉토리를 되돌리는 것이다. (마지막 커밋의 상태로 되돌리는 것) 

  

- `git reset` (git commit 되돌리기)

  \-`git reset --hard 커밋 고유의 아이디`

  \- 옵션에는 --soft 와 --mixed 와 --hard가 있다.

  \- HARD옵션만 내가 작업하고 있는 워킹디렉토리가 바로 바뀐다 .

|         | Working Directory | Staging Area | Repository |
| ------- | ----------------- | ------------ | ---------- |
| --soft  | X                 | X            | X          |
| --mixed | X                 | O            | O          |
| --hard  | O                 | O            | O          |

O : 과거의 커밋의 모습으로 돌아간다

X : 현재 내가 작업하고 있는 모습이 그대로 남아있는다. 

참고로 reset은 --hard만 종종 쓰이고 사실 잘 쓰이지 않으므로 이런 것도 있구나 한번 경험하는 걸로 족하다



## .gitignore

\- .git(숨김파일)이 있는 위치와 동일할 때, .gitignore파일을 사용해서 git에 의한 버전관리를 하지 않을 파일을 따로 정할 수 있다. 

\- git status를 통해 .gitignore에 올리고 저장하기 전 후의 상황을 비교해보면, 명확한 이해가 가능하다. 

\- *.png 라고 타이핑하고 저장을 하면 .png파일은 모두 제외시킬 수 있다. 

\- data/라고 하게 되면, data폴더를 포함해서 그 안에 있는 모든 파일을 제외시킬 수 있다. 

\- gitignore를 검색해보면 이러한 제외시킬 파일들을 정리해주는 사이트가 있다. 이런 사이트에서 window나 mac os, 프로그래밍 언어를 검색해서 한번에 쉽게 제외시킬 수 있다. 



## Repository 공유 

- 레포지터리에서 settings > manage access > invite a collaborator(초대를 하는 사람이 팀장) > 유저네임 입력 > Add하기 > 팀원(collaborator)이 초대에 응하고 나서 > 팀원은 팀장의  레포지터리에 가서 clone을 받는다 > 여기서 팀원이 push까지 하게 되면 팀장의 레포지터리에서 팀원의 push내용이 뜨게 된다. > 그러나 아직 팀장의 로컬 저장소에는 해당 내용이 없으므로 팀장은 본인의 레포지터리를 clone하고 pull 

- 팀장(Inviter)   

  \- GitHub Repository 생성

  \- Local에서 Repository 생성 

  \- 1. `git init `

  \- 2. `git remote add origin URL.git`

  (위 1.과 2.는 clone으로 대체가능)

  \- README.md 생성 

  \- `git add .` , `git commit -m "first"`

  \- `git push origin master `

  \- 초대하기 

  \- 팀원이 작업하고 push를 하면, `git pull origin master`

- 팀원(Collaborator)

  \- GitHub에 가입한 이메일로 오는 초대 메일 수락 

  \- `git clone URL.git`

  \- (작업)

  \- `git add .` , `git commit -m "first"`

  \- `git push origin master`



## Branch

- 브랜치란는 특정 커밋을 가리키는 '포인터' 라는 개념이 중요하다. 

- `git branch` 

  \- 브랜치 목록을 보여주고, 현재 브랜치의 위치도 보여준다. 

- `git branch 브랜치명`

  \- 브랜치를 생성한다. 

- `git checkout 브랜치명`

  \- 해당 브랜치로 이동한다. 

  \- `git checkout -b 브랜치명`이렇게 옵션을 주면, 브랜치를 생성과 동시에 해당 브랜치로 이동한다. 

- `git merge signup(브랜치명)` 

  \- 어떤 브랜치를 어디 브랜치에 합칠지에 유의해야 한다. 

  \- 예를 들어, A브랜치에 B브랜치를 합치려면, `git checkout Abranch`를 해서 현재 내가 있는 브랜치를 Abranch로 만들고 나서, `git merge Bbranch`를 해주어야 한다. 

  \- 그리고 merge는 로컬 저장소에서만 반영된 것이므로, 이 후 push를 해주어야 한다. 

- `git branch -d 브랜치명`

  \- 해당 브랜치를 삭제한다. 

- `git log --graph`

  \- 커밋이 된 목록을 보여주는데, 브랜치의 흐름도 그래프로 같이 보여준다. 

- `git log -- graph --oneline`

  \- 커밋이 된 목록을 한 줄로 보여주고, 브랜치의 흐름도 그래프로 같이 보여준다. 

- 만약 브랜치를 하나만 두고 작업을 하다가 하나의 브랜치에 문제가 생기면, 모든 서비스가 중단된다. 그러나 만약 브랜치가 여러 개라면, 하나의 브랜치가 멈춰도 다른 브랜치들은 정상작동하게 된다. 그러므로 git 브랜치는 가능하면 많이 만들어서 여러 갈래로 작업을 하는 것이 좋다. 

  하지만, 브랜치를 나누기만 하고 merge를 하지 않으면, 화수분처럼 퍼져나가는 브랜치 형태가 되는데, 사실 이런 것은 바람직하지 않다. 중간중간 기능구현이 이뤄지면 merge를 통해 브랜치를 합쳐주는 형태로 개발이 이뤄지는 것이 바람직하다. 

- merge의 충돌상황 

  \- master브랜치에서 작업을 하다가 Cbranch를 만들었다. 이 후 작업을 하는데 C브랜치에서 gram.txt파일 2번째 줄에 코드를 입력했다. 작업을 마치고 C브랜치를 master브랜치에 merge하려고 한다. 그런데 master브랜치를 보니, gram.txt파일 2번째 줄에도 다른 코드가 입력되어 있다. 즉, 두 브랜치가 동일한 위치에서 다른 작업을 한 상황이다. 여기서 merge를 하면, 겹치지 않는 부분들은 merge가 되지만, 겹치는 부분은 충돌이 났다는 알림이 콘솔창에 뜬다. 그러므로 해당 파일에 가서 비교부분을 확인해서 수정을 하고 merge를 추가로 하고 add commit push를 해주어야 한다. 참고로 충돌이 난 부분은 아예 add가 되지 않는다. 

- fast forward merge 
- merge commit case 



## Pull Request (협업 시)

- Pull Request 방법 

  1. master브랜치에에서 팀원은 브랜치를 분기하여 작업하고, 나중에 기능구현이 완료되어서 master브랜치에 합치려고 한다.

  2. git add, commit 을 한다.

  3. `git push origin 내가 작업한 브랜치명` 이 방식으로 push를 한다. 

  4. 그렇게 하면 compare & pull request라는 초록색 버튼이 생성된다. 이후 버튼을 누르고 팀원은 팀장에게 Create Pull Request를 보낸다. (자신의 작업물이 master 브랜치에 합쳐지도록 요청을 보내는 것이다.)

     참고로, 팀원도 pull request를 merge할 수 있는 버튼이 활성화가 되긴 한다. 

  

팀장은 선택적으로 작업물에 대한 리뷰를 남길 수 있는데, start review , 리뷰 작성, finish review를 누르면 리뷰창이 만들어진다. 그러면 팀원도 해당 리뷰를 볼 수 있고, 남길 수도 있다. (커뮤니케이션 가능) 그리고 팀장이 merge를 하면 끝이다. 

꼭 master브랜치에 merge하는게 아니더라도 전체적인 흐름에서 merge를 하게 될 때는 pull request하기가 권장된다. (반드시는 아니다.)

## Git Flow(협업Tip)

- Git을 활용하여 현업을 하는 흐름으로 branch를 활용하는 전략을 의미한다. 그 중 가장 대표적인 전략은 아래 사진과 같다. ![img](git%202%EC%9D%BC%EC%B0%A8.assets/git-model@2x.png)

  ​                                                                    [사진 출처 링크](https://nvie.com/posts/a-successful-git-branching-model/)

  

| branch                       | 주요 특징                                                    | 예시                                                         |
| ---------------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| Master(main)                 | 바로 배포가능한 상태의 코드이다.                             | LOL 클라이언트 라이브 버전(게임이 바로 가능한 버전)          |
| Develop(main)                | feature branch로 나뉘거나, 발생된 버그 수정 등 개발 진행을 한다. 개발 이후에는 release branch로 나뉜다. | 다음 패치(10.2)를 위한 개발                                  |
| feature branches(supporting) | 기능별 개발 브랜치(topic branch), 기능이 반영되거나 드랍되는 경우에 삭제하는 브랜치이다. | 기능별 개발, 예를 들어, 신챔프 출시, 아이템 업데이트 등      |
| release branches(supporting) | 개발 완료 이후 테스트 등을 통해 얻어진 다음 배포 전 사소한 버그 수정 등이 반영된다. | 10.2a, 10.2b ...                                             |
| Hot fixes(supporting)        | 긴급하게 반영해야 하는 버그 수정이 이뤄지고, release branch가 다음 버전을 위한 것이라면, Hot fix는 현재 버전을 위한 branch이다. | 긴급한 패치를 위한 작업 예를 들어 버그로 게임 진행이 순탄치 않을 때 |



\- Git Flow에 정해진 답이 있는 것은 아니다. Github, Gitlab 등 각 서비스에 알맞게 제안된 Flow들이 있으며, 각 프로젝트 및 각 회사들에서 각자의 상황에 맞춰 활용되고 있다. 그러니 유동적으로 상황에 맞는 협업 방식을 따르는 것이 필요하다. 



## GitHub Flow 

- 깃허브 플로우에는 GitHub에서 제안하는 브랜치 전략으로 몇가지 기본 원칙이 있다. 
  1.  master 브랜치는 반드시 배포가 가능한 상태여야 한다. 
  2.  feature 브랜치는 각 기능의 의도를 알 수 있도록 작성한다. (각주 등 활용)
  3.  Commit message는 매우 중요하며, 명확하게 작성해야 한다. 
  4.  Pull Request를 통해 협업을 진행한다. 
  5. 변경사항을 반영하고 싶다면, master branch에 병합한다.  

- GitHub에서 제시하는 방법 2가지 

  1. Shared pull request model 

     \- 앞서 살펴본 collaborator에게 초대장을 보내서 결과적으로 pull request하는 방식

  2. Fork & pull model 

     \- 아래에서 설명 

     

## Fork & Pull

- 예를 들어, 대형의 오픈소스에 개발자로 참여하고 싶을 때, collaborator로 껴달라고 해서(초대를 하고 수락을 해서) 같은 레포지터리를 공유해서 작업을 시작할 수 있다. 그러나, 그렇게 같이 일 할 수 없을 때, 나는 상대의 GitHub의 레포지터리를 내 원격 레포지터리(github)에 복사할 수 있다.( == Fork를 뜬다) 이후 내가 작업을 해서 정상작동이 되는 것을 확인하고 PR을 보낸다. 그래서 만약 PR이 받아들여지면, 자동으로 collaborator로 등록이 되고, 내 작업물이 해당 레포지터리에 올라간다.(GitHub에 Contributor로 올라간다.) 참고로 이러한 경험은 주니어 개발자로 취업하는데 굉장히 큰 메리트를 가지고 있다. 
- Fork & Pull 하는 방법 
  1. 내가 원하는 레포지터리에 가서 우측 상단의 Fork를 누른다. 
  2. 내 깃허브 레포지터리로 넘어오면, git clone을 한다. 
  3. 새롭게 브랜치를 파고, 해당 브랜치로 이동한다. **(master브랜치에서 작업하면 절대 안된다.)**
  4. 작업을 하고 add, commit, push (`git push origin 내가 작업한 브랜치명`)를 해준다. 
  5. GitHub에 가서 compare & pull request를 누르고 create pull request를 해준다. 
  6.  이제 merge가 되기를 기다린다. 여기서부터는 해당 github 레포지터리의 오너 마음이다. 즉, 내 손을 떠났으니 기도하자. 
  7. 만약 merge가 되면, 내 아이디는 해당 레포지터리의 contributor로 올라간다!



## GitHub Page

* GitHub에서 제공하는 무료 웹 호스팅 서비스이다.

* 만드는 법 

  1. 본인의 깃허브로 가서 레포지터리를 만든다. 이 때 레포지터리 이름은 아래의 형식을 따른다. 

     내 아이디가 hoony0125인데 이 경우에 **hoony0125/github.io**의 형식이다. ( 만들 때 아무것도 추가로 체크하지 않는다.)

  2.  바탕화면 폴더를 하나 만든다. 이름은 상관없지만 나는 GitHub_Pages로 만들었다.

  3.  git bash를 열어서 폴더 위치에서 `git clone https://github.com/hoony0125/hoony0125.github.io.git .`를 해준다. 

  4. 이 후 VSCode를 열고, index.html 파일을 만든다. index.html을 꾸민다.

  5. add, commit, push를 해준다.

  6. github 사이트로 가서 해당 레포지터리로 이동한다. 

  7. settings > 주소링크로 이동 > 끝! 

     만약 이 페이지에서 source가 branch : master가 아니라 branch : none으로 되어있다면, master로 수정한다. 

  8. 템플릿을 활용하면 보다 그럴듯한 웹을 보여줄 수 있다. (startbootstrap 사이트에서 무료 템플릿 사용이 가능하다.)

추가로 **hoony0125/github.io**가 아닌 다른 레포지터리가 있고, 거기에 index.html파일이 있다면, 해당 레포지터리도 내 웹에서 보여줄 수 있다. 뒤에 /레포지터리명으로 웹을 보여줄 수 있다. 



와우.. 어제 오늘 git 배운게 너무 뿌듯하고, 또 뭔가 재밌는 느낌을 받으며 배울 수 있었다. 되게 좋은 걸 배운 느낌!!

오늘도 형식에 잘 맞춰서 TIL을 성심성의껏 작성 하다보니, 많은 에너지를 소모한 것 같다. git은 여기까지 하는 걸로 하고, 다음부터는 정말 에너지와 시간을 아끼면서 가독성은 괜찮고 내가 누굴 가르쳐주기 위한 목적을 가지고 쓰기보단, 내가 배웠던 내용을 복기하기 위한 용도로 TIL을 작성하도록 해야겠다!  주말도 해야될 것들이 넘쳐나지만, 일단 오늘 TIL은 여기서 끄읕😁