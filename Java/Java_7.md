## Java 7

#### (2차원 배열, 정렬, 함수) 

### 1. 2차원 배열 (2 dimensional array)

**1.1. 배열** 

Array (배열)은 같은 자료형 변수들의 묶음이며, index number(번지)로 관리(접근)한다.

```java
int array[] = new int[10];	// 방법1
array[0] = 10 ;
	 	
int array[] = {1, 2, 3};	// 방법2
```



**1.2. 2차원 배열** 

```java
int array[][] = new int[3][4];
int array[][] = {
	{ 1, 2, 3, 4 },
	{ 5, 6, 7, 8 },
	{ 9, 10, 11, 12 },
};
	 	
array[1][2] => 7     
// 행렬과 개념은 같으나 0번지부터 시작한다는 점이 다르다.
```



**1.3. 2차원 배열의 선언과 동적할당**

```java
int array2[][] = new int[3][4];     // 선언과 동적할당 
array2[0][0] = 1;
array2[0][1] = 2;
array2[0][2] = 3;
array2[0][3] = 4;

array2[1][0] = 5;
array2[1][1] = 6;
array2[1][2] = 7;
array2[1][3] = 8;
	
array2[2][0] = 9;
array2[2][1] = 10;
array2[2][2] = 11;
array2[2][3] = 12;
```



**1.4. 2차원 배열의 길이** 

```java
for(int i = 0; i < array2.length; i++) {       
// array2.length는 큰 묶음인 3에 맞춰서나온다 

	for(int j =0; j <array2[i].length;j++) {      
	// array2[i].length는 큰묶음 0번지 1번지 2번지의 길이를 의미함
		System.out.print(array2[i][j] + " ");
	}
	System.out.println();
}
/* 출력값 
	1 2 3 4 
	5 6 7 8 
	9 10 11 12
*/
```



**1.5. 2차원 배열의 초기화** 

```java
char cArray[][]= {               // = new int[2][5];
		{ 'H', 'e', 'l', 'l', 'o' },
		{ 'W', 'o', 'r', 'l', 'd' },
};
	
System.out.println(cArray[1][2]);    
// 출력값 r 
```



```java
String strArr[][] = {
		{ "홍길동", "90" },
		{ "성춘향", "100" },
		{ "정수동", "85" },
};
	
int sum = 0;
		
for(int i = 0; i <strArr.length;i++) {
	sum = sum + Integer.parseInt(strArr[i][1]);   // 객체화
}
System.out.println("점수 합계: "+ sum);
// 출력값 점수 합계: 275
```



### 2. 선택정렬(Selection Sort)

정렬에는 선택, 삽입, 버블, 합병, 퀵 등이 있다. 이번에는 선택 정렬만 알아보고자 한다. 

선택정렬은 숫자가 규칙없이 배열로 존재할 때, 오름차순 또는 내림차순으로 정렬하는 것이다. 

만약 3번지까지 있는 배열을 오름차순으로 정렬한다고 할 때,  0번지와 1번지, 0번지와 2번지, 0번지와 3번지를 비교해서 0번지에 제일 작은 수를 놓는다. 그 다음은 1번지와 2번지, 1번지와 3번지를 비교해서 1번지에 그 다음으로 작은 수를 놓는다. 이 과정을 반복해서 정렬하는 것이 바로 선택정렬이다. 

**2.1. 교환 (swap)**

선택정렬을 하기 위해서는 먼저 swap이라는 개념을 알아야 한다. Java에서는 교환을 하기 위해 새로운 빈 변수를 도입해서 거기에 기존 값1을 넣고, 값1에 값2를 넣고, 새로운 변수에 있는 값을 다시 값1에 넣는 방식으로 교환을 한다. 백문이불여일견이다. 코드로 이해해 보자.

```java
int num1, num2; 
num1 = 1;
num2 = 2;
		 	
int temp;
temp = num1; 
num1 = num2; 
num2 = temp; 
```



**2.2. 2차원 배열 예시**

```java
int numbers[] = {5, 1, 10, 2, 9, 3, 8, 7, 4, 6};
int temp;
/* 맨 앞자리부터 뒤로 쭉 하나하나씩 비교해서 첫 자리에 1을 넣고
	  두번째 자리에 있는 수랑 뒤로 쭉 하나씩 비교해서 두번째 자리에 2를 넣고, 이 과정을 반복 
*/

for(int i = 0; i < numbers.length-1; i++) {    
//마지막은 끝에서 두번째랑 마지막이랑 비교를 하기 때문에 -1 
		
	for(int j = i + 1; j < numbers.length ; j++) {
			
		if(numbers[i] > numbers[j]) {     
		// 앞에 있는 수가 더 크면 바꿔라(swap), 여기서 부등호만 바꾸면 내림차순이 된다. 
			// 교환(swap)
			temp = numbers[i];
			numbers[i] = numbers[j];
			numbers[j] = temp; 
		}
	}
}
```



**2.3. sorting 프로그램 실제 사용 흐름(while문 활용)**

	// 변수들
	int numbers[] = null;
	int count;
	String updown;      // 오름이냐 내림이냐
	String again;
		
	while(true) {
		count = 0;					// 초기화
		updown = "오름";				// 초기화
		
		// 1.사용자로부터 입력
		// 정렬할 숫자들의 갯수			: 5
		System.out.print("정렬할 수의 총수 = ");
		count = sc.nextInt();
	
		// 정렬할 수만큼 배열을 할당		: 배열을 5개
		numbers = new int[count];
	
		// 숫자들을 입력				: 5개의 숫자를 차례대로 입력
		for(int i = 0;i < numbers.length; i++) {
			System.out.print( (i + 1) + "번째 수 = " );
			numbers[i] = sc.nextInt();
		}
	
		// 오름/내림					: 오름/내림
		System.out.print("오름/내림 = ");
		updown = sc.next();
		
		// 2.정렬 처리 
		int temp;		//swap을 위한 변수
		for(int i = 0;i < numbers.length - 1; i++) {
			for(int j = i + 1;j < numbers.length; j++) {
				if(updown.equals("오름")) {
					if(numbers[i] > numbers[j]) {
						temp = numbers[i];
						numbers[i] = numbers[j];
						numbers[j] = temp;
					}
				}
				else if(updown.equals("내림")) {
					if(numbers[i] < numbers[j]) {
						temp = numbers[i];
						numbers[i] = numbers[j];
						numbers[j] = temp;
					}
				}
			}
		}
		// 3.출력 처리
		for(int i = 0;i < numbers.length; i++) {
			System.out.print(numbers[i] + " ");
		}
		System.out.println();
		
		System.out.print("다시 시작하시겠습니까?(y/n) = ");
		again = sc.next();
		
		if(again.equals("n")) {
			System.out.println("안녕히 가십시오");
			break;
		}		
		
		System.out.println("다시 시작합니다 >>");
	}



### 3. 함수(method)

function 함수 (독립적인 함수)

method 함수 (Class에 소속되어 있는 함수)



형식:

return값의 자료형  함수명  (인수 == 인자 == 매개변수 == parameter){

​				처리

​				return 값;

}



**3.1. 첫 번째, 매개변수와 리턴값이 둘 다 없는 함수** 

```java
static void functionName() {	
	System.out.println("functionName() 호출");
}


// 호출 
functionName();
```



**3.2. 두 번째, 매개변수가 하나 있고, 리턴값은 없는 함수** 

```java
static void funcName(int n) {        
// int n을 parameter라고 한다. 
	System.out.println("funcName(int n) 호출 n = " + n);
}


// 호출 
funcName(123);   // 여기서 123을 argument라고 한다. 
```



**3.3. 세 번째, 매개변수는 없고, 리턴값만 있는 함수** 

```java
static double func() {
	System.out.println("func() 호출");
	
	/* 
	 double d = 123.456;
	 return d; 
	*/
	return 234.321;
}


// 호출
double d = func();
System.out.println("d = " + d);
```



**3.4. 네 번째, 매개변수가 2개 있고, 리턴값도 있는 함수** 

```java
static int func1(char c, String str) { 		
    // 매개변수 2개(char형, string형), 나오는 값은 int 
	System.out.println("func1(char c, String str) 호출");
	
	System.out.println("c = " + c);
	System.out.println("str = " + str);
	
	return 1; 
}


// 호출
int n = func1('A', "Hello");
System.out.println("n = " + n);
```



**3.5. 다섯 번째, 매개변수로 배열을 받고,  리턴값이 있는 함수**

```java
static int numberSet(int arr[]) {
	System.out.println("numberSet(int arr[]) 호출");
	
	for(int i = 0; i < arr.length; i++) {
		System.out.println(arr[i]);
	}
	return arr[arr.length-1];
}


// 호출
int array[] = {10, 11, 12};
int r = numberSet(array);
System.out.println("r = " + r);
```



**3.6. 여섯 번째, 매개변수는 없으나 리턴값이 배열인 함수** 

```java
static int[] getArray() {
	System.out.println("getArray() 호출");
	int array[] = new int[3];
	array[0] = 111;
	array[1] = 222;
	array[2] = 333;

	return array;
}


// 호출 
int rArr[] =getArray();
for(int num : rArr) {
	System.out.println(num); 
}
```

