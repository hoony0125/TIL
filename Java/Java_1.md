## Java 1 

#### (주석문, 출력, 변수, 입력, 백슬래쉬)



### 1. 주석문

주석문의 기능은 주석이 달려있으면 컴파일이 되지 않는다. 그러므로 주석문은 코드를 설명해주는 역할을 해준다. 

두 가지 종류가 있는데 하나는 한줄 주석문이고, 다른 하나는 범위 주석문이다. 

한줄 주석문은 슬래쉬 두개를 연속으로 써주면 된다. 

```java
// 이것이 한줄 주석문
```



범위 주석문은 아래의 형태처럼 슬래쉬와 별표(애스터리스크)를 사용하여 작성한다.

```java
/*
이것이
범위
주석문
*/
```



### 2. 출력 

배움의 논리적인 순서대로 설명하기 위해 출력 - 변수 - 입력의 순서로 설명하려고 한다. 

출력은 쉽게 말해, 입력받은 값을 출력하는, 말 그대로 보여주기 위한 역할을 한다. 

출력은 다양한 예시를 통해 접하는 것이 가장 와닿는 학습방법인 것 같다. 



**2.1. print와 println의 차이**

```java
System.out.print("Hello World");
```

여기서 System은 클래스의 한 종류이고, print는 함수의 일종이다.

``` java
System.out.println("Hi. Nice to meet you");
```

둘 다 비슷해보이지만, 함수부분을 보면 다르다. 아래는 println으로 되어있는데 println은 출력할 때 커서의 위치를 보면 개행(줄바꿈)이 된다. 이와 달리 그냥 print는 개행이 되지 않고 쭉 이어서 출력이 된다 

예를 들어 System.out.print("Hello World"); 와 System.out.print("Goodbye");를 동시에 출력하면 

Hello WorldGoodbye 이러한 형태로 개행없이 이어서 출력이 된다. 



**2.2. 큰 따옴표도 문자열로 출력하기**

```java
System.out.println("Hi. Nice to meet you");
```

이 코드는 Hi.Nice to meet you로 출력이 된다. 즉, 큰 따옴표는 생략되고 출력이 된다. 여기서 큰 따옴표가 생략되지 않고 "Hi. Nice to meet you" 이렇게 출력되게 하기 위해서는 아래 코드처럼 역슬래쉬를 사용해야 한다. 

```java
System.out.println("\"Hi.Nice to meet you\"");
```



**2.3. 큰 따옴표와 작은 따옴표의 사용과 문자열 연산**

```java
System.out.println("World " + "Hello");  
// 문자열에는 기본적으로 큰 따옴표를 적으면 더하기 연산으로 붙여쓸 수 있다.

System.out.println('한'); 
// 한 글자(1음절)의 경우에는 작은 따옴표도 쓸 수 있다.

System.out.println(256); 
// 숫자를 쓰려고 할 때는 따옴표를 붙이지 않는다. 

System.out.println("234"); 
// 숫자를 문자열로 쓸 수도 있다. 

System.out.println(123 + "번"); 
// 숫자와 문자열을 더하면 123번으로 출력이 된다. 

System.out.println(23 + 45 + "숫자"); 
// 숫자 연산이 먼저 이뤄지고 문자열을 합쳐 68숫자가 출력된다.

System.out.println("숫자" + 123 + 45); 
// 문자열 연산이 먼저되기 때문에 다 문자열 연산이 이뤄짐으로 숫자12345가 출력된다.

System.out.println("숫자" + (123 + 45));
// 이렇게 소괄호로 연산의 순서를 정해주면, 숫자168이 출력된다.
```



**2.4. printf 함수 ** 

printf는 뒤에 적혀있는 애를 출력하는 함수이다. 

```java
System.out.printf("%c\n", 'A');   
/* 
printf 함수에서는 \n을 사용해 개행(줄바꿈)을 할 수 있다.
A라는 문자를 출력하기 위해 앞에 %c가 나왔다. 뒤에 무엇이 나오냐에 따라 앞 알파벳도 달라진다. 
*/

System.out.printf("%c %d\n", '한', 123);
//한 123이 출력되고, 숫자는 앞에 %d가  오며, \n으로 개행이 이뤄진다.
System.out.printf("%s", "안녕하세요");
// 안녕하세요가 출려된다. 문자열의 경우 %s가 앞에 온다. 
```



### 3. 변수 

변수는 변하는 값이며, 그 값을 저장하기 위한 공간이다. 데이터에는 다양한 종류가 있는데, 변수에서는 이 종류를(자료형) 과 변수명을 적음으로써 변수를 선언할 수 있다. 

예를 들어, 

```java
int numSam;
numSam = 123;
System.out.println(numsam);  // 123이 출력 
numSam = 456;
System.out.println(numsam);  // 456이 출력 
```

이처럼 numSam이라는 그릇은 변하지 않고, 안의 데이터만 바뀐다.  



**3.1. 변수의 선언**

자료형 변수명; => int number; 



**3.2. 변수 작명의 규칙 **

**3.2.1. 소문자/대문자를 구분해야 한다.**

```java
int a;
int A;
```



**3.2.2. 쓸 수 없는 변수명**

```java
int int;(x)
// eclipse에서 소스코드로 타이핑을 하다보면, 색깔이 변하거나, 볼드체도 바뀌는 경우가 있는데, 이러한 예약어는 변수명으로 쓸 수 없다. 


int 2a;(x) 
// 숫자가 맨 앞에 오면 안된다.


int 245;(x) 
// 상수는 사용 불가


int _abc; 
// 언더바로 시작하는 것은 허용은 되지만, 문제의 소지가 있기 때문에 권장되지 않는다.


int +abc; int -abc; int *abc 등 (x)
// 변수명에 연산자는 사용불가

```



**3.2.3 추천하는 변수 작명**

```java
int number_position_char; // 그렇게..썩..

int numberPositionChar; // 쏘쏘

int numPosChr; 
// 변수명은 직관적으로 알 수 있고, 단순하고, 너무 길지 않게 짓는 것을 추천
```



**3.3. 자료형의 종류 **

수치 - 정수, 실수

문자  

문자열

논리(True/False)



**3.3.1. 수치 - 정수**

```java
byte by;                           // 바이트는 말 그대로 1바이트의 크기를 갖는다. 
by = 128;
System.out.print(by);
// 이 경우에는 에러가 난다. byte의 출력범위를 벗어났기 때문이다. 
```

1 byte 숫자를 표현할 수 있는 것은 256개이다. 

(8개의 비트)0000 0000 맨 앞의 비트는 부호를 따지는 비트로서 0일 때 +, 1일 때 -이다. 그러므로 총 출력가능한 숫자의 범위는 -128 ~ 127이다 

```java
short sh; // 2바이트 
sh = 12345;

int i; // 4바이트, 제일 사용 빈도가 높다.
i = 45678990;

long l; // 8바이트
l = 89719879812389132; 
/* 
이 값은 long값이라는 것을 알려줘야 한다. 
따라서 숫자가 너무 커지면, 숫자 끝에 소문자l 또는 대문자L을 붙여야 한다. 
*/
```



**3.3.2. 수치 - 실수 **

```java
float f;		// 4바이트 
f = 123.423f;	// 소수점 뒤에 float값인 걸 알 수 있도록 f를 적어줘야 한다. 

double d;		
// 8바이트, float는 종종 오차가 발생하기 때문에 double이 더 자주 사용된다. 
d = 234.3456789098765;
```



**3.3.3. 문자 - 한 글자(1음절)**

```java
char c;   // 2바이트
c = 'A';
c = 'b';
c = '대';
c = 'ab'; (x)     // 작은 따옴표 안에 문자가 두개이기 때문에 에러가 발생한다. 
```



**3.3.4. 논리(True/False)**

```java
boolean b; // 1바이트
b = true;
b = false; // 데이터로 true 또는 false만 받는다. 그렇지 않으면 에러가 발생한다. 
```



### 4. 입력 (Scanner, BufferedReader)

**4.1.  Scanner **

Scanner는 콘솔환경에서만 사용하고, 나중에 웹에서는 사용하지 않는다. 따라서 기초에서만 배우는 입력 라이브러리라고 보면 된다. 

몇 가지 설명을 더 하자면, console창에서 입력을 받으며, 입력을 받으면 그 값을 출력하기 전에 임시로 그 값을 보관할 공간이 필요한데 그것이 바로 변수이다. 



**4.1.1. Scanner 사용하기 **

```java
import java.util.Scanner;   // Scanner는 import(누군가가 만들어놓은 기능을 가져다가 쓰는 것) 해줘야 한다

Scanner scan = new Scanner(System.in);   
// 여기서 scan은 클래스 변수로서 객체라고 부른다. 객체도 변수이기 때문에 scan을 다르게 바꿔도 무방하다. 
// 다만, 아래 변수를 입력할 때나, Scanner를 닫을 때는 객체에 맞춰서 작성해야 한다. 
              :
              :
scan.close();
```



**4.1.2. Scanner 변수(저장공간) 설정**

```java
// boolean(논리형 변수) => true 또는 false만 입력 받을 수 있다.
boolean b;
System.out.print("boolean 입력(true/false) = ");
b = scan.nextBoolean(); // 우항을 입력 받아서 변수 b에 저장 
System.out.println(b);
		
		
// int	
int num; 
System.out.print("숫자 입력= ");
num = scan.nextInt();
System.out.println(num);

		
// double 
double d;
System.out.print("소수 입력= ");
d = scan.nextDouble();
System.out.println(d);
	

// String 
// 문자열을 입력 받는다. 사실상 입력 받는 것은 문자열이 제일 많다. (뭘 입력해도 에러가 없다는 이점))
// 그러나 안녕 친구들을 입력하면, 안녕만 출력이 되는데, 띄어쓰기 이후의 데이터는 출력하지 못하기 때문이다. 
String str = "";   // 다른 변수들과 조금 다른 점 
System.out.print("문자열 입력: ");
str = scan.next();
System.out.println(str);
```



**4.2. BufferedReader**

BufferedReader는 buffer라는 저장공간을 가지고, 읽어드리는 라이브러리이다. 

이걸 활용하면 띄어쓰기가 되어도 출력을 할 수 있다. 

```java
BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
/* 버퍼 스트림을 생성하는데, 위 코드는 Enter키를 입력하기 전까지 콘솔에서 입력한 모든 문자열을 한번에 얻을 수 있다.
InputStreamReader는 바이트 입력 스트림에 연결되어 문자 입력 스트림인 Reader로 변환시키는 보조 스트림이다.
*/ 

System.out.print("str= ");

try {      
	str = br.readLine();  // 실제 입력하는 값 예를 들어, 안녕 친구들을 입력했다. 
}catch(Exception e) {
}
/*  
여기서는 예외 처리 코드인 try블록을 사용한 것이다. try블록에 대해 간단히 설명하면, 
try블록에는 예외 발생 가능한 코드가 위치하고, try블록 코드에서 예외가 발생하면 즉시 실행을 멈추고 
catch블록으로 이동해 예외 처리 코드를 실행하는 것이다. 
*/
System.out.println(str); // 띄어쓰기를 반영해 안녕 친구들이 출력된다. 
```



### 5. 백슬래쉬(＼) 관련 표시

＼는 escape sequence(이스케이프 시퀀스) 중 하나이다.  참고로 블로그에서는 원화 표시(\)만 뜨는 관계로 특수문자를 사용해 설명을 하게 되었다. 

```java
＼n               // 개행(줄바꿈)

＼"               // 큰따옴표 표시, "＼"아이폰＼"" 이렇게 단어 양 옆에 와야한다. 

＼'               // 작은따옴표 표시

＼t               // 띄어쓰기

"＼＼"            // 역슬래쉬 한개 표시 

```



### 6. 실습 해보기 

![img](Java%20%EA%B8%B0%EC%B4%881(%EC%A3%BC%EC%84%9D%EB%AC%B8,%20%EC%B6%9C%EB%A0%A5,%20%EB%B3%80%EC%88%98,%20%EC%9E%85%EB%A0%A5,%20%EB%B0%B1%EC%8A%AC%EB%9E%98%EC%89%AC).assets/image.png)

위 이미지처럼 출력하기 위한 코드를 작성해보자 

```java
String name1, name2, name3;
name1 = "\"홍길동\"";
name2 = "\"일지매\"";
name3 = "\"장옥정\"";
		
		
int age1, age2, age3; 
age1 = 20;
age2 = 18;
age3 = 14;
		
boolean lady1,lady2, lady3;
lady1 = true;
lady2 = true;
lady3 = false;
	
String phone1, phone2, phone3; 
phone1 = "010-111-2222";
phone2 = "02-123-4567";
phone3 = "02-345-7890";
		
double height1, height2, height3;
height1 = 175.12;
height2 = 180.01;
height3 = 155.78;
		
String address1, address2, address3;
address1 = "\"경기도\"";
address2 = "\"서울\"";
address3 = "\"부산\"";
		
System.out.println("=================================================================");
System.out.println("\\ "+"name	        age	lady	phone		height	address"+"\t"+"\\");
System.out.println("=================================================================");
System.out.println("\\ "+name1 +"\t"+ age1 +"\t"+ lady1 +"\t"+ phone1 +"\t"+ height1 +"\t"+ address1 +"\t"+"\\");
System.out.println("\\ "+name2 +"\t"+ age2 +"\t"+ lady2 +"\t"+ phone2 +"\t"+ height2 +"\t"+ address2 +"\t"+"\\");
System.out.println("\\ "+name3 +"\t"+ age3 +"\t"+ lady3 +"\t"+ phone3 +"\t"+ height3 +"\t"+ address3 +"\t"+"\\");
System.out.println("=================================================================");
```

이게 내가 코딩한 부분이고, 위의 사진처럼 그대로 출력이 된다. 그런데 선생님이 작성하신 코드를 보니 훨씬 간단하게도 가능하다. 각 자료형 변수 타입을 선언할 때, 나처럼 변수 타입별로 1, 2, 3 번호를 붙이지 않고, 하나씩만 선언을 하고, 변수를 한번씩 바꾸고 출력 명령를 내리면 보다 간결하게 코딩이 가능했다. 제어문도 배우긴 했지만, 나중에 보다 확실히 내 것으로 만들고 나면, for문을 통해서 훨씬 간단하게도 가능할 것 같다. 