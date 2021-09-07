## Java10

#### (객체지향프로그래밍, 생성자)

### 1. 객체지향프로그래밍

== Object Oriented Programming (OOP)

**1.1. 이론**

**객체**란 추상적으로 생각할 수 있거나 물리적으로 존재하는 것 중 자신의 속성을 가지고 있으며, 다른 것과 식별 가능한 것이다. 

**객체 모델링**이란 현실에 있는 객체를 소프트웨어 객체로 설계하는 것인데, 현실 세계 객체의 속성과 동작을 추려내 소프트웨어 객체의 필드와 메소드로 정의하는 과정이다. 

객체들은 각각 독립적으로 존재하고, 다른 객체와 메소드라는 상호작용 수단을 통해 다른 객체와 서로 상호작용하며 동작한다. 객체가 다른 객체의 기능을 이용하는 것을 **메소드 호출**이라고 한다. 객체는 다른 객체의 메소드를 호출해 원하는 결과를 얻는다. 

**객체 지향 프로그래밍**이란, 만들려고 하는 완성품인 객체를 모델링하고, 집합 관계에 있는 부품 객체와 사용 관계에 있는 객체를 하나씩 설계한 뒤 조립하는 방식으로 프로그램을 개발하는 기법이다. 

**1.2. 객체 지향 프로그래밍의 특징**

- 캡슐화(접근 제한자/접근지정자 사용)

  \- 객체의 필드와 메소드를 하나로 묶고, 실제로 구현하는 내용을 숨기는 것을 말한다. 외부 객체는 객체 내부의 구조는 알지 못하고, 그저 객체가 노출하여 제공하는 필드와 메소드만 이용이 가능하다. 

  | 접근 제한자 | 적용 대상                    | 접근할 수 없는 클래스                          |
  | ----------- | ---------------------------- | ---------------------------------------------- |
  | public      | 클래스, 필드, 생성자, 메소드 | 없음                                           |
  | protected   | 필드, 생성자, 메소드         | 자식 클래스가 아닌 다른 패키지에 소속된 클래스 |
  | default     | 클래스, 필드, 생성자, 메소드 | 다른 패키지에 소속된 클래스                    |
  | private     | 필드, 생성자, 메소드         | 모든 외부 클래스                               |

  \- 필드에서는 90%이상이 private을 사용한다. 외부에서 접근하기 위해선 getter와 setter를 사용해야 한다.

  \- getter는 외부에서 접근할수 있도록 하는 함수이고 setter는 외부에서 값을 변경할 수 있도록 하는 함수이다.

  \- 메소드에서는 90%이상이 public을 사용한다.

   

- 상속

  \- 부모 역할을 하는 상위 객체와 자식 역할을 하는 하위 객체가 있는데, 상위객체가 가지고 있는 필드와 메소드를 하위객체가 물려받아 사용할 수 있다. 

- 다형성

  \- 다형성은 같은 타입이지만 실행의 결과는 다양한 객체를 이용가능한 성질을 말한다. 바꿔 말하자면, 하나의 타입에 여러 객체를 대입해서 다양한 기능을 이용할 수 있는 것이다. 다형성을 위한 방법으로는 부모 클래스 또는 인터페이스의 타입변환이 있다. 둘 중에 뭐가 좋다거나, 인터페이스 사용에 대해서는 갑론을박이 있지만, 알아두는 것이 좋다. 

  (뒤에서 다룰 예정)

**1.3. 객체와 클래스**

**클래스**는 자바에서 객체를 위한 설계도 역할을 한다. 클래스에는 객체 생성을 위한 필드와 메소드가 정의되어 있다. 

클래스가 설계도라면, 객체는 자동차에 해당한다. 즉, 자바에서는 설계도를 통해 여러 대의 자동차를 만드는 것이다. 클래스를 통해 만들어진 객체를 해당 클래스의 인스턴스(instance)라고 한다. 

```java
// 클래스 선언 
public class Car{
  	필드
    메소드
}

// 클래스로부터 객체 생성
new 클래스()

// 클래스 변수 선언과 객체 생성을 한번에
    클래스 변수 = new 클래스()
    Car Ca = new Car() 
    
   // new 연산자로 객체 생성
   // car() 는 생성자   
    // Ca가 객체, instance
```

풀어서 설명하면, new 연산자로 생성자를 호출해서 객체를 생성하고, 이 때 리턴되는 객체의 번지를 ca에 저장되는 것이다. 

매커니즘을 좀 더 풀어서 설명하자면, memory는 stack, heap, static, system 공간으로 나뉘는데, 우항의 new 연산자로 생성된 객체는 heap영역에 저장된다. 이 때 만약 저장되는 주소 또는 번지가 1000이라고 하면, 1000이라고 하는 번지가 클래스 변수에 저장되는 것이다. (참고로 클래스 변수는 객체의 번지를 저장하는 참조타입변수이다). 클래스 변수에 번지는 stack영역에 저장이 되며,  힙 영역의 객체를 사용하기 위해 사용된다. 참고로 여기서 말하는 번지, 주소는 8자리로 되어 있고, 그 크기는 4바이트(32비트)이다.



**1.4. 계산기 예시** 

클래스 선언(생성)

```java
package cal;

import java.util.Scanner;

public class Calculator {
	
// Calculator 클래스 안에 있는 필드는 매소드에 자동으로 들어가기 때문에 따로 입력할 필요가 없다.
	int num1, num2; 
	String operator; 
	int result; 
	
// Calculator 안의 input메소드
// 입력 
public void input() {
	Scanner sc = new Scanner(System.in);
		
	System.out.print("첫번째 수 = ");
	num1 = sc.nextInt();
		
	System.out.print("(+, -, *, /) = ");
	operator = sc.next();
		
	System.out.print("두번째 수 = ");
	num2 = sc.nextInt();
}
	
// Calculator 클래스 안의 calculator메소드 
public void calculator() {
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
}
// Calculator 클래스 안의 print메소드
public void print() {
	System.out.println( num1 + " " + operator + " " + num2 + " = " + result);
}
```



클래스 외부에서 객체 생성 및 메소드 호출 

```java
Calculator ca = new Calculator();
		
ca.input();
ca.calculator();
ca.print();
	
```



### 2. 생성자

- 생성자의 핵심은 초기화이다. 

- 생성자는 클래스명과 같다.

- return값이 없다.

- 생성자로 Overloading이 가능하다.
- 따로 호출은 불가능하다.
- 생략이 가능하다. 



- 예시

  ```java
  public class MyClass {
      // 필드 
  	private int number;			
  	private String name;
      private double height;
      
  	//생성자	
  	public MyClass() {
  		System.out.println("MyClass MyClass()");
  		number = number;
  		name = name;
  		height = height;
  	}
  	
  	public MyClass(char c) {
  		System.out.println("MyClass MyClass(char c)");
  	}
  	
  	public MyClass(int number,String name, double height) {  // 매개변수 != 필드
  		System.out.println("MyClass MyClass(int number,String name, double height)");
  		this.number = number;
  		this.name = name;		
          // this를 붙여서 필드임을 보여주고, 필드에 매개변수를 넣는 것이다,
  		this.height = height;
  		System.out.println(this.number+this.name+this.height);
  	}
  		
  	public MyClass(int i) {
  		this();     
          // 생성자 안에서 this() 생성자를 호출하면 기본 생성자를 출력한다.(MyClass MyClass())
  		// 생성자 안에서 this()를 호출할 때 위치는 클래스 선언부 바로 밑으로 고정이다. 
  		System.out.println("MyClass MyClass(int i)");
  	}
  }
  ```

  

호출은 아래처럼 매개변수에 맞춰서 한다. 

```java
MyClass cls = new MyClass();
System.out.println();
		
MyClass cls1 = new MyClass('A');
System.out.println();

MyClass cls2 = new MyClass(2, "문자들", 123.11);
System.out.println();
		
MyClass cls3 = new MyClass(3);
System.out.println();
```

