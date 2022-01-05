##  Intellij Runserver error

**첫 번째 에러내용**

```
Execution failed for task ':compileJava'.
```

맨 처음에 서버를 Run했을 때, 이런 에러가 떴다. 그래서 아래 부분 설정을 바꿔주었다. 

![image-20220105175410482](Intellij%20Runserver%20error.assets/image-20220105175410482.png)

저기 위에 Intellij IDEA로 바꿔준 것은 원래 Gradle로 되어있는데, 이 부분은 서버를 실행할 때, 그레이들로 실행하는게 아니라, IntelliJ로 실행하기 위함이다. gradle로 해도 상관은 없는데, IntelliJ로 하면 보다 빠르게 Run이 가능하다. 그리고 아래 Gradle JVM부분을 11로 바꿔주니, 세개의 에러가 떴었는데 아래의 에러 하나만 뜨게 되었다.

 

**두 번째 에러 내용**

```
java: warning: source release 11 requires target release 11
```

 **1. build.gradle에서 sourceCompatibility 확인**

![image-20220105174644377](Intellij%20Runserver%20error.assets/image-20220105174644377.png)

여기는 11로 제대로 되어있었다. 



**2. Project Structure > Project Settings > Project 확인**

![image-20220105174905634](Intellij%20Runserver%20error.assets/image-20220105174905634.png)

나의 경우, 여기 SDK가 11이 아니라 1.8로 되어있었다. 이 부분을 수정하니 Run이 정상적으로 되었다. 

**3. Project Structure > Platform Settings > SDKs**

![image-20220105175149093](Intellij%20Runserver%20error.assets/image-20220105175149093.png)

이 부분에서 안맞으면 또 에러가 날 수도 있다는데, 참고로 나는 1.8로 되어있음에도 Run이 잘 되긴 했었다. 그래도 버젼을 맞춰주자. 