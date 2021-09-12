## Java 14 

#### 변수, 정적 메소드, final 

### 1. 변수(local, static, parameter, member)

 **Variable(변수)**

---

​																		메모리 영역	(memory		stack		heap		static		system)

local 							지역																			   O

static							정적																												 O

parameter		 지역변수와 비슷																	O

member				클래스에 소속																					  O

---



**MainClass**

```java
package main;

import cls.MyClass;

public class MainClass_variablessample {

	public static void main(String[] args) {

		MyClass.static_var++; 
        // static변수는 어디에 선언되어도 상관없다. 
		
		int number; // local
		
		MyClass cls = new MyClass();
		
        MyClass.static_var++; 
        
		cls.method('B', 1);
		System.out.println();
		
		cls.method('B', 1);
		System.out.println();
		
		cls.method('B', 1);
		System.out.println();
		
		
		MyClass cls1 = new MyClass();  
		
		cls1.method('B', 1);
	}
}

```



**다른 클래스**

```java
package cls;

public class MyClass {
	
	private int number = 0;		
    // member변수 -> heap 
    // 메인에서 클래스가 할당되면서 메모리에 잡힌다.
	
	public static int static_var;	
    // static -> global(전역) variable 
    // 프로그램 시작과 동시에 메모리에 잡힌다. 
	
	final int MEMBERNUMBER = 10; 
    // final field -> 보통은 설정해놓고, 대입할 때 쓰인다.
	// 참고로 상수는 static이면서 final이어야 하며, 클래스에만 포함된다. 그리고 한번 초기값이 저장되		면 변경할 수 없다. 
	
	public void method(char c, int n) {		
        // 매개변수(외부와 통할 수 있는 통로) -> stack
		
		int num=0; 			// local -> stack  포함된 블럭 내에서만 계속 메모리를 잡아둔다. 
		
		num++;
		number++; 			// 멤버변수 또는 instance변수 		
		MyClass.static_var++; 
		
		System.out.println("local 변수 = "+ num);
		
		System.out.println("member 변수 = "+ number);
		
		System.out.println("static 변수 = "+ MyClass.static_var);
	}
}
```



### 2. 정적 메소드 (static method)

**MainClass**

```java
package main;

public class MainClass_staticmethod {

	public static void main(String[] args) {
		/*
		  	멤버 메소드 : class에 소속되어 있는 함수
		 				instance 메소드
		 				
		 	정적 메소드 : class에 소속되어 있는 함수
		 				class 메소드, static 메소드
		 */
        
		Myclass.staticMethod();
	
		
		Myclass cls = new Myclass();  
        // 인스턴스를 받아야만 나올 수 있기 때문에 instance 메소드라고도 함 
		cls.memberMethod();

		Myclass.staticMethod(); 
        // 인스턴스(cls) 호출이 안되어 있어도 static 메소드는 클래스명을 통해서 호출하는 것이다 
	
		Myclass mycls = Myclass.Ins();
	}
}
```



**다른 클래스**

```java
package main;

public class Myclass {
	static int stNumber;
	int memVar;
	
	
	public void memberMethod() {
		System.out.println("Myclass memberMethod()");
	}
	
    
	
	public static void staticMethod() {
		System.out.println("Myclass staticMethod()");
	}  
// static메소드에서는 this와 super를 사용할 수 없다.	
	
    
// static 메소드 쓰는 예시
	public static Myclass Ins() {  
		Myclass m = new Myclass(); // 클래스명과 동일하게!
		m.memVar = 1;  
// 인스턴스 멤버를 정적 블록 안에서 사용하기 위해 객체를 먼저 생성하고 참조변수로 접근한다. 
// 메소드 블록에서는 인스턴스 변수가 쓰일 수 없기 때문에 초기화 할 값이 많아서 설정할 값이 많다고 가정
// 여기서는 인스턴스 변수를 한개라고 했지만, 인스턴스 변수가 많아지면 굉장히 복잡해진다. 
		
		return m;
	}
}
```



### 3. Final

```java
package main;

public class MainClass_finalsample {

	public static void main(String[] args) {
		/*
		 	final
		 	 final은 상수, 메소드, 클래스
		*/
        
		final int NUMBER = 100;	// 상수 number 
		
		int num = NUMBER; // 대입용으로 쓰인 상수 
	
		ChildClass cc = new ChildClass();
		cc.method();
		
	}
}
// final class는 상속금지! (자식클래스를 만들 수 없다.)
/*final*/ class SuperClass{

// Over Ride 금지! 
	public /*final*/ void method() {
		System.out.println("SuperClass method()");
	}
}

class ChildClass extends SuperClass{
	
	public void method() {							// Over Ride
		System.out.println("ChildClass method()");
	}
}
```

