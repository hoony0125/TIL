## Java 6

#### 실습(계산기,한자리 숫자/문자 판별하기, 구구단, 홀수/짝수끼리의 합)

### 1. 계산기 (Calculator) 

함수나 클래스를 사용하지 않고, 문자를 넣으면 그냥 예외가 발생하는 가장 기본적인 기능만을 가진 계산기 구현

```java
import java.util.Scanner;

public class asd {

	public static void main(String[] args) {
		// 1.변수설정 
		Scanner sc = new Scanner(System.in);
		int num1, num2, result = 0;
		String operator;
		
		
		// 2.입력
		
		// 첫번째 숫자
			System.out.print("첫번째 수 = ");
			num1 = sc.nextInt();

		// 연산자 
			System.out.print("(+, -, *, /) = ");
			operator = sc.next();
			
		// 두번째 숫자 
			System.out.print("두번째 수 = ");
			num2 = sc.nextInt();
		
		// 3.1. if문 사용
			/*
			if(operator.equals("+")) {
				result = num1 + num2;
			}
			else if(operator.equals("-")) {
				result = num1 - num2;
			}
			else if(operator.equals("*")) {
				result = num1 * num2;
			}
			else if(operator.equals("/")) {
				result = num1 / num2;
			}
			*/
		// 3.2. switch문 사용 
			switch( operator ) {
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
	
		// 4.결과출력
			
		System.out.println( num1 + " " + operator + " " + num2 + " = " + result);
	}
}

```



### 2. 한자리 숫자/문자 판별하기 

```java
/*
한자리 숫자입력
아스키(ASCII)코드 활용 
0 ~ 9 -> 숫자를 입력하셨습니다.
		 -> 숫자가 아닙니다.
*/
String strNum;	
Scanner sc = new Scanner(System.in);
System.out.print("숫자입력 = ");
strNum = sc.next();
char c = strNum.charAt(0);	 
// charAt은 String을 배열로 바꿔주는 메소드이다. 0번지만 출력해서 하나의 문자(char c) 변수에 넣음 
		
int n = (int)c;
		
if(n >= 48 && n <= 57) {
	System.out.println("숫자입니다");
}
else {
	System.out.println("문자입니다");
}
sc.close();	
```



### 3. 구구단 

```java
// 이중for문 활용
for(int i = 1; i <10; i++) {
	for(int j = 1; j <10; j++) {
		System.out.print(i + " x " + j + " = " + i*j + " ");
	}
	System.out.println();
```



### 4. 100이하 홀수/짝수끼리의 합 

```java
int sum1, sum2;
sum1 = sum2 = 0;     // 0으로 셋팅 == 0으로 초기화 
for(int i = 1; i <= 100; i++) {
	if(i%2 == 1) {
		sum1 = sum1 + i;
	}
	else if(i%2 == 0) {
		sum2 =sum2 + i;
	}
}
System.out.println(sum1);
System.out.println(sum2);
```

