## Java 5

#### (for문, while문, do-while문, break, for each문, continue)

### 1. for문

**1.1. for문**

for문은 조건문으로 주어진 지정 횟수에 따라 처리를 반복하는 제어문이다. 

형식:

for (초기화; 조건식 ; 연산식){

​			처리

}



for문의 작동순서: 

for ( 초기화 (1); 조건식 (2),(5),(8),(11); 연산식 (4),(7),(10) ) {

​			처리 (3),(6),(9)					--> 3번 반복했다. 

} 

​					(12)



초기화 한번 이 후에 조건식 - 처리 - 연산식을 계속 반복한다. 

언제까지? 조건식에서 false가 될 때까지! 그러므로 마지막은 조건식에서 끝이 난다. 

```java
for(n = 0; n < 3; n++) {
		System.out.println("for 문++"); 
		// 루프가 세번 돈다. 
}
// 출력값 for 문++가 세번 

for(n = 3; n > 0; n--) {
	System.out.println("for 문--");
}
// 출력값 for 문--가 세번

for(n = 0; n < 7; n = n + 2) {
	System.out.println("for 문 +2 n = " + n);
}
/* 출력값
for 문 +2 n = 0
for 문 +2 n = 2
for 문 +2 n = 4
for 문 +2 n = 6
*/
	
for(int i = 0; i < 10; i++) {
	System.out.println("i = " + i);
}
/* 출력값 
i = 0부터 i = 9까지
*/


for(int i = 1; i <= 10; i++) {
	System.out.println("i= "+i);
}
/* 출력값 
i = 1부터 i = 10까지
*/
```



아래는 무한 루프 예시 

```java
for(int i = 0; ; i++) {
	System.out.println("i = " + i);
}
// 출력값 계속 1 더한값이 더해지는 무한루프 
```



**1.2. 이중 for문(중첩, for 안에 for문)**

```java
for(int i = 0; i < 5; i++) {		// 0 1 2 3 4	
				
	System.out.println("i = " + i);
				
	for(int j = 0; j < 4; j++) {	// 0 1 2 3
		System.out.println("\tj = " + j);
	}
}
```



**1.3. for문 if문 Array 중첩**

```java
int Array[] = {90, 100, 55, 85, 70, 45, 80};  // 과락이 60점이하일 때 과락만 보고싶다.
			
for(int i = 0; i < Array.length; i++) {
	if (Array[i] < 60) {
		System.out.println(Array[i]);
	}
}
/* 출력값 
55
45
*/
```



### 2. while문 

형식: 

초기화 

while (조건식){

​			처리

​			연산식

}



예시:

```java
int w; 
w = 0; //초기화
while(w <5) {
	System.out.println("while loop = " + w);
			
	w++;	
// 연산식 (참고로 w++는 처리 위에 와도 된다. 다만 아래에 쓰는게 일반적)
}
/* 출력값
while loop = 0
while loop = 1
while loop = 2
while loop = 3
while loop = 4
*/
```

 

### 3. do - while문

do - while문은 while문과 다르게 조건식이 충족되지 않아도 무조건 한번은 처리가 일어난다. 그렇기 때문에 상대적으로 사용빈도가 떨어진다. 

형식:

초기화

do{

​			처리

​			연산식

}while(조건식);



예시:

```java
int dw;
dw = 0;
do {
	System.out.println("do while loop");
		
	dw++;
}while(dw < 5);
/* 출력값
do while loop
do while loop
do while loop
do while loop
do while loop
*/
```



### 4. break 

break는 쉽게 말해 loop문에서 탈출시키는(escape) 기능을 한다. 

for문, while문, do - while문과 같이 쓰이는데 특히 for문과 많이 쓰인다. 

형식:

```java
for(int i = 0; i < 10; i++){
	if(조건){
	 break;
	}
}
```



예시:

```java
for(int i = 0; i < 10; i++) {
	System.out.println("for loop i = " + i);
			
	if(i == 5) {
		break;
	}
}
/* 출력값
for loop i = 0
for loop i = 1
for loop i = 2
for loop i = 3
for loop i = 4
for loop i = 5
*/


char cArray[] = {'a', 'b', 'c', 'd'};
		
for(int i = 0; i < cArray.length; i++) {
	System.out.println("i= "+ i);
	if(cArray[i] == 'c') {
		System.out.println("c를 찾았습니다");
		break;
	}
}
/* 출력값
i= 0
i= 1
i= 2
c를 찾았습니다
*/
```



### 5. for each문

형식:

for( 자료형 : 배열(또는 리스트)){

​			처리

}



예시:

```java
// for each문에서 break
int array[] = {11, 22, 33, 44, 55};
for (int num : array) {
	System.out.println(num);
	if(num == 22) {
		break;
	}
}
/* 출력값
11
22
*/

```



### 6. continue

continue은 loop문과 같이 사용하며 생략(skip)기능을 가지고 있다.

형식:

while(조건문){

​			처리1

​			처리2

​			if(조건) {

​						continue;

​			 }

​			 처리3  

}

---> if문의 조건이 성립되어서 continue가 실행되면, 처리3을 생략하고 다시 while 조건문으로 올라간다.



예시: 

```java
for(int i = 0; i < 3; i++) {
	System.out.println("i = " + i);
	System.out.println("for start");
	
	if(i > 1) {
		continue;
	}
	System.out.println("for end"); 
	// 얘만 생략하고 반복문은 돌아간다
}
```



continue를 활용한 while문 예시

```java
// 숫자 입력 5개 
// 조건: 음수를 입력받으면 안된다. 
Scanner scan = new Scanner(System.in);

int number;

int loop = 0;

while(loop < 5) {
	System.out.println((loop+1) + "번째 수 = ");
	
	number = scan.nextInt();
	if(number < 0) {
		System.out.println("음수를 입력하셨습니다. 다시 입력해주세요");
		continue;
	}
				
	loop++;
}
```

