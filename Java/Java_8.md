## Java 8 

#### 실습(2차원 배열, 메소드실습(배열2배, shuffle, 숫자/문자 판별, 계산기/선택정렬))

### 1. 2차원 배열 실습 

```java
			 // name	kor	  eng   math
	String students[][] = {
			{ "홍길동", "90", "85", "100" },
			{ "성춘향", "100", "95", "85" },
			{ "홍두깨", "85", "95", "90" },
			{ "정수동", "70", "85", "100" },
			{ "임꺽정", "90", "75", "80" },
	};
```



**1.1. 모든 학생의 세 과목 총점과 세 과목 총점의 평균을 구하세요.** 

```java
// 모든 학생의 세 과목 총점
int sum1 = 0; 
for(int i = 0; i < students.length;i++) {
	for(int j = 1; j < students[i].length; j++) {
		sum1 = sum1 + Integer.parseInt(students[i][j]);
	}
}
System.out.println("모든 학생의 세 과목 총점 " + sum1); 
// 출력값 모든 학생의 세 과목 총점 1325


// 세 과목 총점의 평균 
double avg = sum1 / (students.length *3);
System.out.println("모든 학생의 세 과목 총점의 평균 " + avg);
// 출력값 세 과목 총점의 평균 88.0
```



**1.2. 입력받은 학생 한명의 성적, 학생 한명의 총점, 학생 한명의 평균을 구하세요** 

```java
// 학생 한명의 성적 출력 


// 일단 이름의 위치를 찾는 코딩
String name = "홍길동";
// 검색 
int findIndex = -1;        
// 2차배열에 있는 이름 중에서 없는 이름을 검색한다면 예를 들어 향단이를 검색했는데 없을 수 있기 때문에 -1을 넣어둠
// 0번지에는 이미 홍길동이 들어가 있으므로! 0으로 초기화해두면 없는 이름을 검색했을 때 홍길동으로 나옴 
for(int i = 0; i < students.length;i++) {
	if(name.equals(students[i][0])) {
		findIndex = i; 
		break;
	}
}
System.out.println("findIndex" + findIndex);
			

// 학생 한명의 총점	
int s_sum = 0; 
for(int j = 1; j < students[findIndex].length;j++) {
		s_sum = s_sum + Integer.parseInt(students[findIndex][j]);
}
System.out.println(s_sum);
// 출력값 홍길동 국어90 영어85 수학100


// 학생 한명의 평균
double s_avg = s_sum /3 ;
System.out.println(s_avg);
```



**1.3. 국어 성적의 총점과 평균을 구하세요.** 

```java
// 국어의 총점, 평균 
int sum3 = 0;
double k_avg = 0.0; 
for(int i = 0; i < students.length;i++) {
	sum3 = sum3 + Integer.parseInt(students[i][1]);
}
k_avg = sum3 / students.length;
System.out.println("다섯명 국어 총점 "+ sum3 + "," + " 다섯명 국어 평균" + k_avg);


// 출력값 다섯명 국어 총점 435, 다섯명 국어 평균87.0
```



### 2. 메소드 실습 

**2.1. 배열의 값에 *2배의 연산된 값이 산출되도록 메소드를 작성하세요.**

**`int num1[] = {1, 2, 3, 4, 5};** `

```java
// 함수 작성
static int[] numberDouble(int num1[]) {		
	for (int i = 0; i < num1.length; i++) {
		num1[i] = num1[i] * 2;
	}
	
	return num1;
}


// 호출
int num1[] = {1, 2, 3, 4, 5}; 
	
num1 = numberDouble(num1);
System.out.println(Arrays.toString(num1));


// 출력값 
// [2, 4, 6, 8, 10]
```



**2.2. 주어진 배열에 담긴 값의 위치를 바꾸는 작업을 반복하여 뒤섞이게 하세요. Math.random() 사용**

**`int[] original = {1,2,3,4,5,6,7,8,9};`**

```java 
// 함수 작성 
static int[] shuffle(int original[]) {
		
	for(int i = 0;i < 1000; i++) {	// 섞는 횟수 1000번
		
		int x = (int)(Math.random() * 9);	
        // 0 ~ 8번지  {1,2,3,4,5,6,7,8,9} - 0번지부터 8번지까지 존재한다
		int y = (int)(Math.random() * 9);	// 0 ~ 8
// 예를 들어 x에 2가 나오고 y에 7이 나오면 2번지와 7번지의 위치를 바꾸는 것이다 
// 그리고 int로 캐스팅하는 이유는 math.random은 double로 나오기 때문이다
			
		int temp = original[x];			// 0 1 2 3 4 5 6 7 8
		original[x] = original[y];
		original[y] = temp;					// 섞는 단계 
	}
		
	return original;
}


// 호출
int original[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
original = shuffle(original);
System.out.println( Arrays.toString(original) );


// 출력값 
// 랜덤한 배열 출력 예:  [6, 1, 5, 4, 7, 9, 8, 3, 2]
```



**2.3. 주어진 문자열이 모두 숫자로만 이루어져있는지 확인해서 모두 숫자로만 이루어져 있으면 true를 반환하고, 그렇지 않으면 false를 반환하세요.**

```java
// 함수 작성 
static boolean isNumber(String str) {
		
	boolean b = true;			// 초기화 
		
	for(int i = 0;i < str.length(); i++) {
		char c = str.charAt(i);
	//	System.out.println(c);	// 잘 뜨는지 확인
		
		int asc = (int)c;
		if(asc < 48 || asc > 57) {		
// 48 == '0', 57 == '9' 	0 ~ 9	// 아스키로 하는게 가장 좋은 방법 
			b = false;
		}			
			
// if(c == '0'	&& c != '1' && c != '2' && c != '3' && c != '4' &&)		
// 이건 좀 소모적 방법이다.  
	}		
	return b;
}


// 호출 
boolean b = isNumber("19374");
if(b == true) {
	System.out.println("모두 숫자입니다");
}else {
	System.out.println("문자가 있습니다");
}
```



**2.4. 계산기 연산부분 method로 바꿔보세요.**

```java
// 함수작성 
static int calculator(int num1, int num2, String oper) {		
	int result = 0;
	
	switch( oper ) {
		case "+":
			result = num1 + num2;
			break;
		case "-":
			result = num1 - num2;
			break;
		case "*":
			result = num1 * num2;
			break;
		case "/":
			result = num1 / num2;
			break;
	}
	return result;
}


// 호출
result = calculator(num1, num2, operator);
```



**2.5. 선택정렬 정렬처리부분 method로 바꿔보세요.**

```java
// 함수 작성 
static int[] sorting(int[] numbers, String updown) {
	int temp;		                         
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
	return numbers;
}


// 호출 
numbers = sorting(numbers,updown);
```

