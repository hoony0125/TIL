## Java 9 

#### (overload, exception)

### 1. overload 

함수(method)명은 같으나, 매개변수의 자료형이나 갯수가 다른 것을 의미한다. (just like 동명이인) 

함수를 구분할 때, 매개변수로 구분하는 것이다. 



형식:

print()

print(int i)

print(char c)

print(int i, char c)

print(char c, int t)

=> 이 네가지는 함수명은 같으나, 매개변수가 각기 다르기 때문에 엄연히 다른 함수이다. 



이러한 overload를 쓰는 이유는 여러 이유가 있지만, 그 중 하나는 기존 함수에서 추가로 정보나 기능을 추가할 필요가 종종 있기 때문이다.



**1.1. 예시** 

```java
// 1
static void method() {
	System.out.println("method() 호출");
}

// 1호출 
method();


// 2
static void method(char c) {
	System.out.println("method(char c) 호출");
}

// 2호출
method('A');


// 3
static void method(int i) {
	System.out.println("method(int i) 호출");
}

// 3호출 
method(123);


// 4
static void method(char c, int i) {
	System.out.println("method(char c, int i) 호출");
}

// 4호출
method('A', 123);


// 5
static void method(int i, char c) {
	System.out.println("method(int i, char c) 호출");
}

// 5호출
method(321, 'z');
```



**1.2. overload가 아닌 경우**

인수의 자료형은 같고, 가인수만 다른 경우에는 다른 함수가 아니다

```java
static void method(int x, char y) {
}

static void method(int i, char c) {
    
}
// 위 두 함수는 가인수만 다르다. 이는 같은 함수로 본다. 
```



리턴되는 자료형만 다르면 의미가 없다. 

```java
static int method() {			
}

static char method() {			
}
// 위 두함수는 리턴되는 값의 자료형만 다르다. 이는 같은 함수로 본다. 
```



### 2. 예외 (Exception) !=에러 

예외에는 종류가 굉장히 많은데, 자주 나오는 것 위주로 학습한다. 예외는 에러는 아니지만, 발생하면 안 좋은 것은 맞다. 

예외라는 기능은 사용자를 위한 기능이라기 보다는 개발자를 위한 기능이다. 

**2.1. 예외 유형** 

- number 형식 위반
  정수값이 들어와야 하는데 -> 'A' == 65(ASCII)  => 에러가 아닌 예외발생
  			 

- array 범위 초과
  int arr[] = new int[3];    arr[3] = 12;
  		 

- 없는 class
  사용하려는 class가 없는 class인 경우 
  		 	
- file 파일 없음



**2.2. 형식** 

```java
try{
		 		
	// 예외가 발생할 수 있는 코드 
		 			
	}catch(예외 클래스1 e){	
		예외1 메시지 출력 
    // 예외가 발생하면 그것을 catch해서 자동으로 예외1 메시지 출력
	}catch(예외 클래스2 e){
		예외2 메시지 출력
	}catch(예외 클래스2 e){
		예외3 메시지 출력
	}finally{
		rollback(복구) - undo
	}
    // finally는 예외 발생과 관계없이 무조건 실행!    
    // 예외가 발생해도 문제 만들지말고 finally로 와라 라는 의미
}
```



```java
// 이렇게 쓰면 더 보기 편하고 간단하쥐 
static void method() throws 예외 클래스 {
		 			
	// 함수도 예외가 발생될 소지가 있음
		 		
}
		 		
// 그렇지만 이렇게도 쓸 수 있다네 		 		
static void method() {
	try{
		예외가 발생될 소지가 있음
	}catch(예외클래스1 e){
		예외1 메시지를 출력
	}	
}
```



**2.3. 예외에서 return과 finally**

```java
int array[] = {1, 2, 3};   // 0~2번지 
System.out.println("start");
		
try {
	for(int i = 0; i < 4; i++) {    // 3번지까지? 노노
	System.out.println(array[i]);
	}	
}catch(ArrayIndexOutOfBoundsException e) {
	// System.out.println("배열범위 초과");
	// e.printStackTrace();  // 예외 결과를 화면에 출력하는 함수
	System.out.println(e.getMessage());
	
	return;   
    // 이렇게 있으면 거기까지만 진행하고 아래는 실행을 안 한다. (밑에 end 실행 안함)
			
}catch(Exception e) {
	e.printStackTrace();
}finally {
	System.out.println("finally 부분");    
    // 중간에 return으로 아래를 실행을 안해도 finally 부분은 실행한다 
}
System.out.println("end");
```



```java
static void method() throws IndexOutOfBoundsException {
	 // 이렇게 함수 옆에 적는 방식으로도 동일한 표현이 가능하다 
	int array[] = {1, 2, 3};
		
	for(int i = 0; i < 4; i++) {
		System.out.println(array[i]);
```



**2.4. 예외별 예시**

먼저 얘기하면, 각각의 예외는 `catch(Exception e)`로 다 돌릴 수 있다. 

**2.4.1. NullPointerException**

```java
String str = null;
try {
	System.out.println(str.length());   
	// str에 값이 없으므로 길이가 없음 
}catch(NullPointerException e) {
	System.out.println("str이 null입니다");
	e.printStackTrace();             
}
```



**2.4.2. ArrayIndexOutOfBoundsException**

```java
int arr[] = {1, 2, 3};
		
try {
	arr[3] = 'A';  	// 4일 때는 배열범위 초과 
}
/*catch (ArrayIndexOutOfBoundsException e) {
	System.out.println("배열범위 초과");
}*/
catch (Exception e) {   
// Exception은 모든 예외를 다 잡아준다 
// 이렇게 작성하면 콘솔창에서 예외 내용을 볼 수 있다. 콘솔창에 뜬 예외를 검색해서 보는 방향으로 확인하면서 개발하는게 좋다. (모든 예외를 알 수는 없기에)
	System.out.println("예외발생");    
}
```



**2.4.3. FileNotFindException**

```java
// 파일을 찾을 수 없을 때 발생하는 예외 
File file = new File("d:\\xxx");
FileInputStream is; 
		
try {
is = new FileInputStream(file);
}catch(FileNotFoundException e) {
	e.printStackTrace();
}
```



**2.4.4. StringIndexOutOfBoundsException**

```java
String str1 = "java";  	// 3번지까지 존재한다 
		
try {
	str1.charAt(4);	
    // 3번지를 벗어난 값 출력을 요구해서 발생하는 예외 
}catch(StringIndexOutOfBoundsException e) {
	e.printStackTrace();
}
```



**2.4.5. NumberFormatException**

```java
try {
int i = Integer.parseInt("12a3"); 
// 여기서 소괄호 안에는 숫자로만 이뤄진 문자열이 들어가야 한다
}catch(NumberFormatException e) {
	e.printStackTrace();
}
```

