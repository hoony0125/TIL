## Java 16 

#### Object 클래스, 제네릭, ArrayList

### 1. Object 클래스 

Object라는 최상위 클래스에는 어떠한 클래스도 다 넣을 수 있다. 그러한 공간, 그릇이다.

```java
int num, num1;
num = 1;
num1 = num; // 이런 경우에는 값1만 넣은 것 
		

MyClass cls = new MyClass();
cls == 일종의 local 변수 == 객체(object) == instance 
              stack          heap

System.out.println(cls);
MyClass clscopy = cls; 				// 이 경우엔 주소를 받고 있다. 
System.out.println(clscopy);
		

Object obj = cls; 
System.out.println(obj);
		
// Object yobj = new YouClass();
// Object mobj = new MyClass();
		
Object arrObj[] = new Object[2]; 	// 객체를 생성한 것이 아니라, 배열을 만든 것이다.
		
arrObj[0] = new MyClass(234, "잡스");
arrObj[1] = new YouClass();
		
if(arrObj[0] instanceof MyClass) {
	MyClass mycls = (MyClass)arrObj[0];
	mycls.method();
}
```



### 2. 제네릭

제네릭은 클래스와 인터페이스, 메소드를 정의할 때 타입을 파라미터로 사용할 수 있게 한다. 타입 파라미터는 코드를 작성할 때 구체적인 타입으로 대체가 되어서 다양한 코드가 생성되도록 해준다. 

- **제네릭의 이점** 

제네릭은 컴파일 시에 잘못 사용된 타입으로 인해 에러를 방지해주고, 종종 리스트에 문자열을 저장하고도 String으로 casting을 하는 등의 불필요한 자료형 변환을 할 필요를 줄여준다. 

- **예시** 

  Box클래스와 BoxMap클래스를 제네릭을 사용해 만들었다. 

  ```java
  // 어떤 자료형을 써야될지 애매할 때!
  // 자료형을 T로 설정을 해놓고, 호출 시에 Integer를 넣으면 T에는 Integer로 들어가는 것이다. 
  // T는 바꿔도 된다.
  class Box<T>{   
  	
  	T temp;
  
  	public Box(T temp) {
  		this.temp = temp;
  	}
  	public T getTemp() {
  		return temp;
  	}
  	public void setTemp(T temp) {
  		this.temp = temp;
  	} 
  }
  
  
  
  class BoxMap<K,V>{
  	K key;
  	V Value;
  	
  	public BoxMap(K key, V value) {
  		this.key = key;
  		this.Value = value;
  	}
  
  	public K getKey() {
  		return key;
  	}
  	public void setKey(K key) {
  		this.key = key;
  	}
  	public V getValue() {
  		return Value;
  	}
  	public void setValue(V value) {
  		Value = value;
  	}
  }
  ```



​		호출

```java
Box<Integer> box = new Box<Integer>( 10 );
System.out.println( box.getTemp() + 23);
		

Box<String> strbox = new Box<>( "Hello" );		// 뒤 꺽새 괄호는 비어있어도 된다.
System.out.println(strbox.getTemp() + 23);
		
		
BoxMap<Integer,String> bmap = new BoxMap<Integer,String>(1001, "김경훈");
System.out.println(bmap.getKey());
System.out.println(bmap.getValue());
```



### 3. ArrayList

ArrayList는 List 인터페이스의 구현 클래스로서, ArrayList에 객체를 추가하면 객체를 인덱스로 관리할 수 있다. 이러한 점에서 일반 배열과 유사하지만, 차이가 있다. 배열의 경우, 생성할 때 크기가 고정되며 사용하는 중간에 크기를 변경할 수 없다. 하지만, ArrayList는 저장용량을 초과해서 객체들이 들어오면, 자동으로 저장 용량이 늘어난다. 

ArrayList에 객체가 추가되면 0번지부터 차례로 저장되며, 특정 객체가 삭제되면 그 뒤에 있는 객체들은 모두 번지가 1씩 당겨진다. 그러므로 객체 사제와 삽입이 자주 발생하는 경우에는 ArrayList를 사용하는 것은 비추이다. 이런 경우에는 LinkedList 추천! (뒤에 다룰 예정)

- **형식**

  ```java
  List<Integer> list = new ArrayList<Integer>();	// 컬렉션 생성 
  // 이렇게 생성 시 디폴트로 10개의 객체를 받을 수 있는 저장용량을 갖는다. 
  
  list.add(1000); // 컬렉션에 객체 추가
  ```



- **예시** 

  ```java
  // ArrayList에 객체 추가 
  Integer iobj = new Integer(111);
  list.add(iobj);
  			
  list.add(222);
  			
  list.add(new Integer(333));
  			
  int len = list.size();		
  System.out.println("len: "+ len);				// 출력값 3 
  
  
  
  // 원하는 위치에 객체 추가 
  Integer inum = 200; //(wrapper이니깐 바로 숫자 넣기 가능)
  list.add(1,inum);
  			
  for(Integer in : list) {
  	System.out.println(in);				// 출력값 111 200 222 333
  }
  
  
  
  // 검색하는 2가지 방법 (indexOf / for문)
  // indexOf
  int index = list.indexOf(222);
  System.out.println("index: " + index);				// 출력값 index: 2
  
  
  // for문 
  for (int i = 0; i < list.size(); i++) {
  	Integer in = list.get(i);
  	if (in == 200) {
  		System.out.println("index: " + i);				// 출력값 index: 1
  	}										
  }
  
  
  
  // 수정
  Integer newdata = new Integer(300); 
  list.set(3, newdata);
  			
  for(Integer in : list) {
  	System.out.println(in);					// 출력값 111 200 222 300
  	}
  
  
  // 삭제 
  list.remove(1);
  for(Integer in : list) {
  	System.out.println(in);					// 출력값 111 222 300
  }							
  ```

  

- 인터페이스 사용 예시 

```java
ArrayList<MyClass> mlist = new ArrayList<MyClass>();

// 추가 방법1과 방법2
MyClass mycls = new MyClass(1, "김경훈", 185.1);
mlist.add(mycls); // 여기에서 1, 김경훈, 185.1이 들어가게 된다 
				
mlist.add(new MyClass(2, "이몽룡", 172.3));
				
mycls = new MyClass(3,"홍길동", 190.2);
mlist.add(mycls);
				
mlist.add(2, new MyClass(2,"향단이", 160.5)); // 2에 향단이를 끼워넣기 
				
				
for (int i = 0; i < mlist.size(); i++) {
	/*
	MyClass my = mlist.get(i)
	System.out.println(my.toString());
	*/
	System.out.println(mlist.get(i).toString());
}
/*출력값
MyClass [number=1, name=김경훈, height=185.1]
MyClass [number=2, name=이몽룡, height=172.3]
MyClass [number=2, name=향단이, height=160.5]
MyClass [number=3, name=홍길동, height=190.2]
*/
	
		

// 삭제
mlist.remove(2);
for (MyClass my : mlist) {
	System.out.println(my.toString());
}
/*출력값
MyClass [number=1, name=김경훈, height=185.1]
MyClass [number=2, name=이몽룡, height=172.3]
MyClass [number=3, name=홍길동, height=190.2]
*/
			

// 검색
String name = "홍길동";
int _index = -1;
for (int i = 0; i < mlist.size(); i++) {
	MyClass my = mlist.get(i);
	if(name.equals(my.getName())) {
		_index = i;
		break;
	}
}
System.out.println(mlist.get(_index).toString());
// 출력값	MyClass [number=3, name=홍길동, height=190.2]


// 수정 
MyClass newObj = new MyClass();
newObj.setNumber(22);
newObj.setName("LMR");
newObj.setHeight(175.3);
				
mlist.set(1, newObj);
				
for (MyClass my : mlist) {
	System.out.println(my.toString());
}
/*출력값
MyClass [number=1, name=김경훈, height=185.1]
MyClass [number=22, name=LMR, height=175.3]
MyClass [number=3, name=홍길동, height=190.2]
*/
```



바로 위 예시의 dto

```java
package dto;

public class MyClass {
	private int number; 
	private String name;
	private double height;
	
	public MyClass() {
	
	}

	public MyClass(int number, String name, double height) {
		this.number = number;
		this.name = name;
		this.height = height;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	@Override
	public String toString() {
		return "MyClass [number=" + number + ", name=" + name + ", height=" + height + "]";
	}
}
```

