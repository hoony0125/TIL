## Java 13

#### 상속(Inheritance)

### 상속 1

객체 지향 프로그램에서는 부모 클래스의 필드와 메소드를를 자식 클래스에게 물려줄 수 있다. 프로그램에서는 부모 클래스를 상위 클래스라고 부르기도 하고, 자식 클래스를 하위 클래스라고 부른다. 다만, Private접근 제한을 갖는 필드와 메소드는 상속되지 않는다. 상속은 이미 잘 개발이 되어있는 클래스를 재사용하여 새로운 클래스를 만들기 때문에 중복을 줄여주기 때문에 효율적이고 시간 절약적인 방법이다. 



예시를 통해 상속에 대해 좀 더 알아보자 

**부모 클래스** 

```java
package cls;

public class ParentClass {
	
	private String name;
	protected int number; 
	private int age; 
	// protected는 외부에서는 접근이 안되고 자식클래스에서만 접근이 허용된다. (요즘은 사용빈도가 떨어진다)
	/*
	public ParentClass() {
		System.out.println("ParentClass ParentClass()");
	}
	*/
	public ParentClass(String name, int number) {
	 // this();             // 기본 생성자를 통하게 된다.
		this.name = name;
		this.number = number;
	}
	
	
	
	public ParentClass(String name, int number, int age) {
		super();
		this.name = name;
		this.number = number;
		this.age = age;
	}



	public void pMethod() {
		System.out.println("ParentClass pMethod()");
		
		
	}
	protected String getName() {
		return name;
	}
	protected void setName(String name) { 
                            // 이렇게 getter setter에도 protected를 쓸 수도 있다.  
		this.name = name;	   		// 실제로 보면, 메소드에는 99% public을 쓴다. 
	}
}

```



**자식클래스** 

```java
package cls;

public class ChildClass extends ParentClass{ // ParentClass를 상속받겠다
	
	String address;
	
	public ChildClass() {
		super("성춘향", 16); 
// this : 자기참조, super : 부모참조(부모 메소드 호출)
// this와 super는 같이 쓰지 못한다, this나 super의 위치는 생성자의 바로 아래에 와야한다.
// super와 this는 메소드 내의 제일 위에 와야한다. 
		System.out.println("ChildClass func()"); 
// 이렇게 자식클래스에서 생성자가 있어도 순서상 부모클래스의 생성자를 먼저 호출하고, 그 다음으로 자식클래스의 생성자를 호출한다.												 
	}
    
	 
	public ChildClass(String name, int number, String address, int age) {
		super(name,number,age);
		this.address = address;
	}
// 부모클래스의 필드와 자식클래스의 필드를 혼합해서 받을 수도 있다. 
// 메인클래스에서 매개변수를 받아오면, name, number, age는 부모클래스로 올라가서 들어가고, address는 자식클래스에 있기 때문에 자식클래스에서 들어간다.
	
    
	public void func() {
		System.out.println("ChildClass func()");
	
		// name = "일지매" 
        // 부모클래스의 필드를 보면, private이 붙어있어서 name에 접근이 안된다. 
		
		setName("일지매"); 
        // 이렇게 접근할 수 있다. (사실 큰 의미는 없다.)
	
		number = 123; 
        // protected된 것은 자식클래스에서만 접근이 허용된다. 
	}
}

```



**Main Class**

```java
package main;

import cls.ChildClass;
import cls.ParentClass;

public class MainClass_inheritance {

	public static void main(String[] args) {
		/*
		 	inheritance : 상속 
		 	
		 	부모 클래스 -> 자식 클래스 
		 	variable(변수) 
		 	method(함수)
 			 
		 */

	ChildClass cc = new ChildClass();
	cc.pMethod();
	
	ChildClass cc1 = new ChildClass("염철수", 1002, "서울시", 25);
	
	}
}
```



### 상속 2

ParentClass pc = new ChildClass(); 이렇게 부모클래스의 객체를 자식클래스로 받는 경우에 대한 예시와 오버라이딩 개념을 살펴보자.

**MainClass**

```java
package main;

import cls.ChildClass;
import cls.ParentClass;

public class MainClass_inheritance2 {
	
	public static void main(String[] args) {
        
			ChildClass cc = new ChildClass();
        	/* 출력값
        	ParentClass ParentClass()
			ChildClass ChildClass()  */ 
        
			cc.method();
        	/* 출력값
        	ChildClass method()  */  
        
			cc.func();
        	/* 출력값
        	ParentClass func()  */         
	
        
        
			//    객체(pc) : 부모     생성 : 자식 
			ParentClass pc = new ChildClass();
			/* 출력값
			 ParentClass ParentClass()
			 ChildClass ChildClass() */
			
			pc.method();
			/* 출력값
			 ChildClass method() */
			
			pc.func();
			/*  출력값
			 ParentClass func() */
			
			ChildClass c = (ChildClass)pc;
        // 이렇게 자식클래스로 캐스팅할 수도 있다. 
			c.process();
			/*  출력값 
			 ParentClass method()
			 ChildClass method() */
	}
}

```



**부모클래스**

```java
package cls;

public class ParentClass {
	
	public ParentClass() {
		System.out.println("ParentClass ParentClass()");
	}	
	
	public void method() {
		System.out.println("ParentClass method()");
	}
			
	public void func() {
		System.out.println("ParentClass func()");
	}
}
```



**자식클래스**

```java
package cls;

public class ChildClass extends ParentClass {
	
	public ChildClass() {
		System.out.println("ChildClass ChildClass()");
	}
	
	public void method() {	
 // Over Ride : 상속받은 메소드를 고쳐 기입한다. ParentClass -> ChildClass로 고쳤다. 
		System.out.println("ChildClass method()");
	}

	public void process() {
		super.method();  
        // 이렇게 super를 넣어주면 부모클래스의 메소드를 불러온다. 
		method();
	}	
}
```

###  

### 상속 3

자식 클래스가 여러 개일 때, 부모클래스 하나로 자식클래스를 묶어서 관리하는게 좋다. 

묶지않은 경우와 묶은 경우를 비교해서 확인해보자. 



**MainClass**

```java
package main;

import cls.ChildOneClass;
import cls.ChildTwoClass;
import cls.ParentClass;

public class MainClass_inheritance3 {

	public static void main(String[] args) {

			 
		/* 
		예를 들어, one과 two를 회원모집으로 생각하고 one에는 고급회원, two에는 보통회원을 		 잡는다고 가정해보자.
		그리고 두 집단을 합쳐서 총 10명을 뽑는다고 할 때, 배열로 잡는다고 치면, 
		아래처럼 배열의 길이를 일단 둘 다 10,10으로 최대갯수로 잡아놔야 한다. 
		총 20만큼 메모리를 잡아먹는다 
		*/
		
		ChildOneClass arrCOC[] = new ChildOneClass[10];
		ChildTwoClass arrCTC[] = new ChildTwoClass[10];
	
/* 두개의 다른 클래스를 각자 받아서 관리하는 경우 
가입을 아래처럼 받았다. 그런데 번지도 다 따로따로 관리해야 한다(클래스가 다르니깐) 
		arrCOC[0] = new ChildOneClass();
		arrCTC[0] = new ChildTwoClass();
		arrCTC[1] = new ChildTwoClass();
		arrCTC[2] = new ChildTwoClass();
		arrCOC[1] = new ChildOneClass();
		arrCTC[3] = new ChildTwoClass();
		arrCTC[4] = new ChildTwoClass();
		arrCOC[2] = new ChildOneClass();
		arrCTC[5] = new ChildTwoClass();
		arrCOC[3] = new ChildOneClass();
		
		for (int i = 0; i < arrCOC.length; i++) {
			if(arrCOC[i] != null){
				arrCOC[i].overRideMethod();
			}
		}
		
		for (int i = 0; i < arrCTC.length; i++) {
			if(arrCTC[i] != null){
				arrCTC[i].overRideMethod();
			}
		}
*/
		
		
// 위와 아래 코드는 동일하다. 
// 아래는 부모클래스로 묶어서 관리하는 경우.. => 훨씬 편하다 => 이게 부모클래스로 묶는 이유이다. 
		ParentClass arrPar[] = new ParentClass[10];
		
		arrPar[0] = new ChildOneClass();
		arrPar[1] = new ChildTwoClass();
		arrPar[2] = new ChildTwoClass();
		arrPar[3] = new ChildTwoClass();
		arrPar[4] = new ChildOneClass();
		arrPar[5] = new ChildTwoClass();
		arrPar[6] = new ChildTwoClass();
		arrPar[7] = new ChildOneClass();
		arrPar[8] = new ChildTwoClass();
		arrPar[9] = new ChildOneClass();
		
		for (int i = 0; i < arrPar.length; i++) {
			arrPar[i].overRideMethod();
		}
		
        
        
// instance of: 강제타입변환(캐스팅)을 하기 전에 변환시킬 객체인지 조사하는 것이다.
		for (int i = 0; i < arrPar.length; i++) {
			arrPar[i].overRideMethod();
		
			if(arrPar[i] instanceof ChildOneClass){  
 
				ChildOneClass cOne =(ChildOneClass)arrPar[i];
				cOne.OneMethod();
			}
			else if(arrPar[i] instanceof ChildTwoClass){
				ChildTwoClass cTwo = (ChildTwoClass)arrPar[i];
				cTwo.TwoFunc();
			}
		}
	}
}
```



**부모클래스**

```java
package cls;

public class ParentClass {
	
	public void overRideMethod() {
		System.out.println("ParentClass overRideMethod()");
	}
}
```



**자식1클래스**

```java
package cls;

public class ChildOneClass extends ParentClass{
	public void overRideMethod() {
		System.out.println("ChildOneClass overRideMethod()");
	}
	
	public void OneMethod() {
		System.out.println("ChildOneClass OneMethod()");
	}
}
```



**자식2클래스**

```java
package cls;

public class ChildTwoClass extends ParentClass{
	public void overRideMethod() {
		System.out.println("ChildTwoClass overRideMethod()");
	}
	
	public void TwoFunc() {
		System.out.println("ChildTwoClass Twofunc()");
	}	
}
```



