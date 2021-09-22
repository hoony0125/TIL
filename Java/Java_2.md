## Java 2 

#### (자료 형 변환, 배열, 연산자)



### 1. 자료 형 변환 

<img src="Java%20%EA%B8%B0%EC%B4%882%20(%EC%9E%90%EB%A3%8C%20%ED%98%95%20%EB%B3%80%ED%99%98,%20%EB%B0%B0%EC%97%B4,%20%EC%97%B0%EC%82%B0%EC%9E%90).assets/image.png" alt="img-50px" style="zoom:64%;" />

```
 <div style="text-align: center;">
  <img style="max-height:90%; max-width:90%;"
  src="https://t1.daumcdn.net/cfile/tistory/99BF19485C514D480F">
 </div>
```



자료형의 우선순위를 정리해봤다. 큰 흐름은 바이트 크기 순으로 가고, 예외로 4바이트인 float만 8바이트인 long보다 높은 우선순위를 갖는다. 



자동 (자료)형 변환 : 우선순위가 낮은 것을 우선순위가 높은 것에 넣는 것을 말한다. 

강제 (자료)형 변환 == Casting : 우선순위가 높은 것을 우선순위가 낮은 것에 넣는 것을 말한다. 



**1.1. 자동 형 변환** 

```java
short sh = 12345;
int num; 
num = sh;      
// 쉽게 말해 작은 그릇(short)에 있는 것을 큰 그릇(int)에 붓기 때문에 넘치지 않는다.
System.out.println(num); 
출력값 12345

double = d;
d = num; 
// 이 또한 마찬가지로 num을 상대적으로 우선순위가 높은 double에 넣기 때문에 자동으로 형 변환이 일어난다. 
System.out.println(d);
출력값 12345.0 
```



**1.2. 강제 형 변환 (Casting)**

```java
int number = 200;
short sho;
sho = (short)number; 
// 상대적으로 우선순위 낮은 short으로 int를 바꾸려면 (short)를 적어서 강제 형 변환을 시켜줘야 한다.
// 이것을 캐스팅한다고 한다. 
System.out.println(sho);  
출력값 200 

float f;
f = 3/2;
System.out.println(f); 
출력값 1.0 
// 여기서는 좀 다른 목적으로 캐스팅을 하는데, 3/2는 몫을 출력하는 연산자가 쓰인 것으로 판단을 내려 
// 1이 나오고 여기에 float으로 인해 1.0이 출력된다.

f = (float)3/2;   
// 캐스팅을 해주고 연산을 하면 1.5로 출력되는 것을 확인할 수 있다. 
System.out.println(f); 

```



**1.3 보너스.. 연산 중에 주의해야 할 연산**

```java
int result = 0; 
// result = 3/0;     
// 분모가 0인 경우 두 연산 모두 에러가 나오기 때문에 분모에는 0이 올 수 없다. 
// result = 3%0;

result = 0/3;                               // 분자는 0으로 에러가 뜨지 않는다. 
```



### 2. 배열(Array)

Array(배열): 같은 자료형의 묶음. 변수들

배열의 목적: 변수들을 관리하기 위함이다. 

만약 

int num1, num2, num3 ... 이러한 형태로 변수가 100개 있다고 가정해보자. 많은 타이핑을 쳐야함은 물론, 보기에도 좋지 않다. 그렇기 때문에 보다 간편하고, 간결하게 이러한 형태의 변수들을 표현하고 관리하기 위해 배열이 존재한다. 

형식은 다음과 같다 

<center>자료형 배열변수명[ ] = new 자료형[배열의 총 수]

<center>int Array[ ] = new int[10]

<center>선언 = 할당

정리하자면 이렇다. 배열의 총 수가 10이라는 것은 총 변수의 개수가 10개라는 것이다. 



여기서 코너 속의 코너 느낌으로 **할당**의 개념에 대해 잠시 알아보려고 한다. 

위의 할당 부분에서 new가 보인다. new가 나왔다는 것은 동적할당이 이뤄진다는 것을 의미한다. 

**동적할당**이란, 내 마음대로 결정해서 할당을 할 수 있다는 것을 의미한다. 위의 예시에서는, 10의 위치에 100, 50, 7 등 어떤 숫자가 오든 내 마음대로 배열의 총 수를 결정해서 변수의 개수를 정할 수 있다. 



자 이번에는 **정적할당**에 대해 설명하려고 한다. 당연히 동적할당이 반대일 것 같은 느낌이 든다. 그렇다 마음대로 바꿀 수 없다고 생각하면 된다. 배열에 대해 좀 더 보며 정적할당을 설명해보겠다. 

위에서 총 수를 10개로 정한 배열에 1부터 10까지 들어가 있다고 하고, 이를 표현해보겠다. 

<center>Array[0] = 1;

<center>Array[1] = 2;

<center>Array[2] = 3;

 <center>          :

<center>Array[8] = 9;

<center>Array[9] = 10;

여기서 보면 대괄호 안에 있는 숫자들을 배연 변수에 접근하기 위한 Index Number(번지)라고 부른다. 번지는 0에서 시작해서 Array.length -1로 끝이 난다.(배열의 총 수 - 1)

여기서 돌아오면, 선언과 할당의 단계에서는 배열의 총 수를 마음대로 결정한다는 점에서 동적할당이었으나, 이미 결정된 배열의 총 수는 이 배열을 사용할 때는 변경할 수 없고, 10개로 유지해야 한다는 점에 정적이다. 

그러므로 정리하자면, 배열은 동적으로 셋팅하고, 정적으로 사용한다고 볼 수 있다. 그리고 결론적으로 Array는 정적이다. 정적이라고 해서, 변화를 줄 수 없다고 해서 배열에 한계가 있을 것 같지만, 실제로는 그렇지 않으며 코딩에 있어서, 특히 통계쪽이나, 빅데이터를 다루고 전처리 하는 과정에서 많이 쓰인다고 한다. 



**2.1. 배열의 표현 방법** 

```java
int Array[ ] = new int[5];

int [ ]Array = new int[5];

int[ ] Array = new int[5];
// 세 방식으로 표현이 가능하다


// 아래 방식도 가능하다

int Array[ ];

Array = new int[10];

// 선언만 먼저하고, 동적할당은 따로 하는 형태이다.
```



**2.2.  배열의 길이**

``` java
Array[0] = 11;
Array[1] = 22;
Array[2] = 33;
Array[3] = 44;
Array[4] = 55;
System.out.println(Array.length);     // 배열의 길이를 의미하며, 출력값은 5이다.
```



**2.3. 초기화 (변수의 초기화, 배열의 초기화)** == Initialize

먼저 변수의 초기화도 끼워넣기로 같이 보자

```java
// 변수의 초기화 
int num1;
num1 = 5;                                                    // 초기화 방법1 

int num2 = 7;                                                // 초기화 방법2 


// 배열의 초기화 
int Array1[] = null; 
```

null은 주소를 0000 0000으로 셋팅해주는 것이다. 여기서 말하는 주소에 대해 살펴보면, 배열은 선언과 할당만 하더라도 메모리의 stack부분에 주소가 생성된다. 주소를 보기 위해 System.out.println(Array); 입력하면, 출력값으로 [I@36aa7bc2 가 나온다. 

여기서 3으로 시작해 2로 끝나는 부분이 8비트로 구성된 1바이트로서 8자리의 주소이다. 배열의 초기화는 이 여덟 자리를 0000 0000으로 셋팅해주는 것이다. 

```java
// 배열에서 값의 초기화(Value Initialize)
int Array3[] = {10, 20, 30, 40, 50, 60};
//               0	 1	 2 	 3	 4	 5  (번지 == index number)
		
int Array4[] = {10, 20, 30, 40, 50, 60, 70, 80, 90};
```



**2.4. char 배열 "dog"**

```java
char charArray[] = new char[3]
charArray[0] = 'd';
charArray[1] = 'o';
charArray[2] = 'g';
System.out.println(charArray[0]);             // 출력값 d
System.out.println(charArray)                 // 출력값 dog  
```

int배열과 다르게 char배열은 변수를 바로 출력해도 주소가 아니라 값이 출력된다.

```java
char charArray1 = {'d', 'o', 'g'}; 
System.out.println(charArray1);               // 출력값 dog

System.out.println(charArray1[0] + charArray1[1] + charArray[2]);         // 출력값 314 
System.out.println("" + charArray1[0] + charArray1[1] + charArray[2]);    // 출력값 dog
```

위의 형태로 char 배열 구성하고, 출력할 수 있다. 

다만 char배열의 경우, 각 번지를 적어서 더하기를 연산을 하게 되면 위의 예시에서 볼 수 있듯이 314가 나오는데, 이는  ASCII코드에서 각 문자에 해당하는 10진수를 더해서 나온 값이 출력된 것이다. 이 값이 아닌 번지를 통해 문자 dog를 출력하기 위해서는 큰따옴표("")를 사용해 문자 더하기 연산을 넣어주면 dog가 출력된다.



**2.5. 배열 복사** 

```java
int arrayNum[] = {10, 20, 30};
int arrayCopy[] = arrayNum;
System.out.println(arrayCopy[0]);              // 출력값 10
System.out.println(arrayCopy[1]);              // 출력값 20
System.out.println(arrayCopy[2]);              // 출력값 30

// 일단 출력값만 보면, arrayNum 배열이 arrayCopy에도 들어갔다. 
// 여기서 arrayCopy를 수정하면 어떻게 될까?
arrayCopy[0] = 50; 
System.out.println(arrayNum[0]);                               // 출력값 50
// arrayCopy에 변화를 주었는데, 원본인 arrayNum의 데이터까지 변화된다. 
// 즉, 단지 배열의 값을 가져온 것이 아니라, 데이터의 원본인 arrayNum이 복사된 것이다.  
System.out.println(arrayNum);
System.out.println(arrayCopy);
// 두 배열의 주소를 출력해봐도 동일한 주소가 출력된다. 
```



아래처럼 코딩을 하면 배열의 복사가 아니라, 값만 가져올 수 있다. 

```java
int arr1[] = {1,2,3};
int arr2[] = new int[3];
arr1[0] = arr2[0];
arr1[1] = arr2[1];
arr1[2] = arr2[2];
System.out.println(arr1);
System.out.println(arr2);
// arr1과 arr2의 출력된 주소를 보면 다르다.

int Arr1[] = new int[5];
System.out.println(Arr1); 
// 주소가 출려된다. 즉, 선언 및 동적할당만 해도 주소는 생성된다. 
```

포인트는 선언과 할당이 이뤄질 때, 주소가 나오기 때문에 선언과 할당이 이뤄진 후에 값을 가져와야 한다. 그래야 복사가 되지않고 값만 가져올 수 있다. 



### 3. 연산자(Operator)

**3.1. 기본 사칙연산**

연산자에는 +(덧셈), -(뺄셈),  *(곱셈),  /(나눗셈 - 몫 계산), %(나눗셈 - 나머지 계산)이 있다. 

```java
int num1, num2; 
int result; 
result = num1 + num2; 

result = num1 + num2; 	
System.out.println(result);
System.out.println(num1 + "+" + num2 + "=" + result);        
// 출력값 25+5=30
		
result = num1 - num2;
System.out.println(num1 + "-" + num2 + "=" + result);       
// 출력값 25-5=20
		
result = num1 * num2;
System.out.println(num1 + "*" + num2 + "=" + result);        
// 출력값 25*5=125
		
result = num1 / num2;
System.out.println(num1 + "/" + num2 + "=" + result);        
// 출력값 25/5=5
		
result = num1 % num2;
System.out.println(num1 + "%" + num2 + "=" + result);        
// 출력값 25%5=0
```



**3.2. 자기(변수) 자신의 값을 변경하는 연산 표현**

```java
int num3 = 0;
num3 = num3 +3; 
System.out.println("num3 = " + num3);                  // 출력값 num3 = 3
		
num3 += 4;   
System.out.println("num3 = " + num3);                  // 출력값 num3 = 7

// 이와 동일한 형식으로 아래처럼 표현할 수 있다. 
num3 -= 4;                                             // num3= num3 - 4
num3 *= 4;                                             // num3= num3 * 4
num3 /= 4;                                             // num3= num3 / 4
num3 %= 4;                                             // num3= num3 % 4
```

 

**3.3. 증감 나타내기(증가, 감소)** 

증감은 ++ 또는 -- 기호를 사용해 +1을 시키거나 -1을 시키는 것이다. 

```java
int num4 = 0; 
// == (num4 = num4 +1, num4 += 1)                                    
num4++;                                     
System.out.println("num4 = " + num4);                   // 출력값 num4 = 1
		
++num4;                                     
System.out.println("num4 = " + num4);                   // 출력값 num4 = 2


// == (num4 = num4 -1, num4 -= 1)		
num4--;                                    
System.out.println("num4 = " + num4);                   // 출력값 num4 = 1

--num4;                                    
System.out.println("num4 = " + num4);                   // 출력값 num4 = 0
```



변수를 두고 계산을 하면, ++, --의 위치(전위 연산 또는 후위 연산)에 따라, 변수의 값이 달라진다. 아래 코드를 보자 

```java
int num5, num6, num7, num8;
num5 = 1; 
num7 = 1; 
num6 = num5++;                                      // num6 = 1, num5 = 2 
num6 = (num5++);                                    // num6 = 1, num5 = 2 
                                                    // 괄호를 해도 달라지지 않는다. 
num8 = ++num7;                                      // num8 = 2, num7 = 2 
// 전위 연산을 하면 좌변과 우변이 모두 연산 이후의 값으로 같지만
// 후위 연산을 하면 좌변은 연산이 일어나기 전의 값이 들어간다. 
```

