## Java 3

#### (String, Wrapper Class, Calendar) d



### 1. String 

**1.1. String 형식** 

String은 Wrapper Class의 일종으로, 문자열을 받는 역할을 한다. 

형식은 

<center>String str = new String("안녕"); 

String: 클래스이름

str: class에서 변수 역할을 하는 인스턴스 또는 객체(object)

참고로 위의 형식도 new를 사용한 동적할당이라고 할 수 있다. 아래의 방식으로 표현할 수도 있다.



<center> String str;

<center>str = "안녕";



**1.2. 함수 간단 소개** 

String 클래스를 설명할 때는 해당 클래스의 각 함수의 기능을 소개하는게 핵심이다. 그러기 위해 

함수에 대해 간단히 짚고 넘어가려고 한다. 



함수는 기본적으로 들어가는 값과 리턴값(나오는 값)이 존재한다. 

나오는 값( 리턴 값 ) = 함수명( 들어가는 값 );



들어가는 값에는 여러개가 들어갈 수 있으나, 나오는 값은 반드시 하나이거나 없다.

나오는 값( 리턴 값 ) = 함수명( 들어가는 값1, 들어가는 값2 );

나오는 값( 리턴 값 ) = 함수명( 들어가는 값1, 들어가는 값2, 들어가는 값3 ,,, ); 



**1.3. 비교함수**

equals() : 문자열과 문자열을 정확하게 비교하기 위한 함수로서 결과값은 True 또는 False의 형태로 나온다. 비교를 할 때는 == 를 이용해서도 가능하지만, 정확한 비교가 이뤄지지 않기도 하기 때문에 equals함수를 이용하는 것이 바람직하다.

먼저, ==를 이용한 비교가 왜 부정확한지를 보자.

```java
String str3 = "world";
String str4 = "worl";
		
str4 = str4 + "d";
System.out.println(str4);                   
// world가 출력됨에도 다르다고 인식한다. 
System.out.println(str3 == str4);           
// 같으면 true 다르면 false인데 출력값은 false이다. 
```

equals함수의 사용을 보면,

```java
String str3 = "world";
String str4 = "worl";	
str4 = str4 + "d";
		
boolean b = str3.equals(str4);                         
// true인지 false인지가 b에 들어간다.  
System.out.println(b);
System.out.println(str3.equals(str4)); 
```



**1.4. 문자의 위치를 탐색하는 함수**

indexOf() : 문자열의 앞에서부터 문자를 찾아나가는데, 문자를 찾아서 번지를 출력한다.

lastIndexOf() : 뒤에서부터 찾아나가고, 문자를 찾아서 번지를 출력한다.

```java
String str5 = "abcdeabcde";                            // 0 ~ 9번지 
int Index = str5.indexOf("c")                         
System.out.println(Index);                             // 출력값 2 (번지)
int lastIndex = str5.lastIndexOf("c");
System.out.println(lastIndex);                         // 출력값 7 (번지)

```



**1.5. 문자의 길이** 

length(): 해당 문자열의 길이를 알려주는 함수이다.

참고로 배열(Array)에서의 길이표현은 `array.length`로 한다. 

```java
String str5 = "abcdeabcde";
System.out.println(str5.length());                              // 출력값 10

// 아래처럼 활용할 수도 있다. 
int len = str5.length();
System.out.println(len);                                        // 출력값 10
```



**1.6. 문자열을 수정하는 함수** 

replace() 

```java
String str6 = "A*B*C*D"; 
String reStr = str6.replace("*", "-");    
// 기존 문자를 먼저 쓰고 뒤에 수정할 내용을 쓴다. 
System.out.println(reStr);                               // 출력값 A-B-C-D

String dateStr = "2021-08-26";
dateStr = dateStr.replace("-", "");
System.out.println(dateStr);                            // 출력값 20210826
```



**1.7.1.문자열을 자르는 함수1** 

split() : 토큰을 기준으로 자른다. 

예를 들어 "홍길동-24-2000/02/17-서울시" 라는 문자열은 "-"를 기준으로 데이터를 구분하는데 여기서는 "-"가 토큰(문자와 문자 사이에 있는 구분자)의 역할을 한다. 

```java
String str7 = "홍길동-24-2000/02/17-서울시";
String spl[] = str7.split("-");                      
// 좌변에는 대괄호가 있는데, 잘린 문자열들이 각 번지에 하나씩 들어간다고 생각하면 된다. 
System.out.println(spl.length());                                // 출력값 4 
System.out.println(spilt[0]);                                // 출력값 홍길동 
System.out.println(spilt[1]);                                   // 출력값 24
System.out.println(spilt[2]);                           // 출력값 2000/02/17
System.out.println(spilt[3]);                                // 출력값 서울시
```



**1.7.2. 문자열을 자르는 함수2**

substring() : 범위(시작위치와 끝위치)를 지정해서 문자열을 자른다. 

substring(a, b); :  a번지부터 b-1번지까지 출력한다.

substring(c); : c번지를 시작위치로 해서 c번지부터 끝까지 출력한다.

```java
String str8 = "홍길동-24-2000/02/17-서울시";
String name = str8.substring(0,3);               
// (시작위치 0, 3번지 전까지) == (0~2번지 출력)
System.out.println(name);                                   // 출력값 홍길동

String human = str8.substring(4);                // 4번지부터 끝까지 출력한다
System.out.println(human);                     // 출력값 24-2000/02/17-서울시
```



**1.8. 문자열을 대문자 또는 소문자로 바꾸는 함수** 

```java
str9 = "abcDEF";
```



**1.8.1. toUppercase() : 문자열을 대문자로 바꾸는 함수** 

```java
String upStr = str9.toUppercase();
System.out.println(upStr);                                  // 출력값 ABCDEF 
```



**1.8.2. toLowerCase() : 문자열을 소문자로 바꾸는 함수** 

```java
String lowStr = str9.toLowerCase();
System.out.println(lowStr);                                 // 출력값 abcdef 
```



**1.9. 문자열 앞뒤의 공백을 제거하는 함수** 

trim : 중간중간의 공백은 무시하고, 맨 앞과 맨 뒤의 공백만 제거한다. 

```java
String str10 = "  java java   java      "; 
String trimStr = str10.trim(); 
System.out.println(trimStr);                   // 출력값 java java   java 
```



**1.10. Sting을 배열처럼 사용할 수 있도록 하는 함수** == index number로 관리 (0번지 ~ n-1번지)

charAt()

```java
String str11 = "가나다라마";
char c = str11.charAt(0);
System.out.println(c);                                          // 출력값 가 
```



**1.11. 문자열을 탐색하는 함수** 

contains : 내가 찾고 있는 문자열을 포함하고 있으면 True, 그렇지 않으면 false 

```java
String str12 = "서울시 강남구";
boolean b1 = str12.contains("서울");                          // 출력값 true
boolean b2 = str12.contains("부산");                          // 출력값 false
```



### 3. Wrapper Class

클래스의 메소드(함수)들 중에는 괄호 안에 오는 매개변수로 기본 자료형의 변수가 아닌 객체형태만 받는 클래스들이 있다. 이러한 경우에 필요한 것이 기본 자료형을 객체화해주는 Wrapper Class이다. 

일반자료형에 대응되는 Wrapper Class들이 있는데 아래 표를 통해 한눈에 확인이 가능하다. 이 중에서 특히 실무에서 많이 쓰이는 것은 Integer, Double, String이 있는데, 많이 쓰이는 것 위주로 살펴보려고 한다. 

<img src="Java%203%20(String,%20Wrapper%20Class,%20Calendar).assets/image-20210905123804691.png" alt="image-" style="zoom:60%;" />

**2.1. Integer, String** 

``` java
// Integar
int i = 123;                                              // 일반 자료형 int
Integar iobj = 123;                                // Wrapper Class Integar
System.out.println(i);                                        // 출력값 123
System.out.println(iobj);                                     // 출력값 123
Integer obj = new Integer(123);         // 취소선이 그어져있는건 구버젼이라는 뜻 


// String
String str = new String("hello");
String str = "hello";                         
// new 쓸 필요없이 간단하게 쓸 수 있다.
```



**2.2. 숫자(Integer) -> 문자열(String)** 

**2.2.1. 큰따옴표 사용** 

```java
int number = 123; 
String s = number + "";                 
// 문자를 포함시켜서 덧셈 연산을 하면  문자열로 바뀐다.
```



**2.2.2. toString사용**

```java
Integer inumber = 123; 
String s1 = inumber.toString(); 
// 그러나 일반 자료형 int로 쉽게 String으로 바꿀 수 있으므로 사용빈도가 높지는 않다.
```



**2.3. 문자열(String) -> 숫자(Integer / Double)** 

**2.3.1. String -> Integer**

```java
String strNum = "1024";                       // 숫자처럼 보이지만, 문자열이다.
int num = Integer.parseInt(strNum);                   
// parseInt함수로 1024를 숫자로 바꾼다. 
System.out.println(num*2);                                   // 출력값 2048 
```



**2.3.1. String -> Double**

```java
String strNumber = "123.4567";
double dnum = Double.parseDouble(strNumber); 
// parsedouble 함수로 123.4567을 소수 숫자로 바꾼다. 
System.out.println(dnum); 

// 참고 int dnum = Integer.parseInt(strNumber); (x) 에러발생 
```



### 3. Calendar 

Java에서 날짜와 요일, 오전 오후 등의 시간 관련하여 Calendar클래스를 제공한다. 과거에는 Date 클래스가 있어서 대부분 Date클래스로 시간과 관련된 코딩을 했는데, 점차 Date의 기능이 축소되어 Calendar가 더 많이 쓰인다.  



**3.1. Calendar 클래스 형식**

먼저 Calendar 클래스를 import 해주어야 한다. 

import java.util.Calendar; 

Calendar cal = Calendar.getInstance();



**3.2. 오늘 날짜 불러오기** 

```java
int year = cal.get(Calendar.YEAR);
int month = cal.get(Calendar.MONTH)+1;	 
// month를 만든 사람이 0~11월까지로 만들었다고 한다. 그러므로 +1을 해주어야 한다. 
int day = cal.get(Calendar.DATE);
System.out.println(year + "년 " + month + "월 " + day + "일"); 
//  출력값 2021년 8월 29일
```



**3.3. 날짜 직접 설정하기**

```java
cal.set(Calendar.YEAR, 2022);
cal.set(Calendar.MONTH, 2-1);           // 여기서 2가 월(month)로 찍히게 된다.
cal.set(Calendar.DATE, 14);
// 날짜를 셋팅한 후에 다시 값을 가져오기 위해 아래 코드를 작성한다.
year = cal.get(Calendar.YEAR);		
month = cal.get(Calendar.MONTH)+1;
day = cal.get(Calendar.DATE);
// 3.2.에서는 int를 붙여서 선언을 해준 것이고, 여기선 선언이 아니라, 변수에 다른 값을 넣어준 것이다. 
System.out.println(year + "년 " + month + "월 " + day + "일");  
// 출력값    2022년 2월 14일
```

 

**3.4. 오전(AM) / 오후(PM)** 

```java
int ampm = cal.get(Calendar.AM_PM);
System.out.println(ampm);	                             // 0: 오전. 1: 오후
```



**3.5. 요일 구하기** 

```java
int weekday = cal.get(Calendar.DAY_OF_WEEK);         // 1: 일요일 ~ 7: 토요일
System.out.println(weekday);
```



**3.6. 각 달의 마지막날(31 30 29 28)구하기**

참고로 3.3.처럼 날짜를  설정할 때, 일자를 31일 로 해놓으면 달 적용이 안되는 경우도 생기기 때문에 원하는 출력값이 안나온다. 

```java
int lastday = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
System.out.println(lastday);           // 위에서 2월로 설정했기 때문에 출력값 28
```



하루하루 점차 배우는게 많아져서 복습하고 암기해야 될 것들도 많아지지만 그래도 하기 싫은걸 하는게 아니라 그런지 재미도 있고, 앞으로가 점점 더 기대가 된다. 이제 일주일 지났는데, 쭉 지치지말고 가자 파이팅!

