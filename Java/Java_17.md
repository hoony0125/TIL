## Java 17 

#### LinkedList, HashMap, 이진 트리 구조 및 TreeMap

### 1. LinkedList

LinkedList는 구현 클래스로서 ArrayList와  사용법은 같으나, 내부 구조는 다르다. 내부 배열에 객체를 저장해 인덱스로 관리하는 ArrayList와 달리, LinkedList는 인접 참조를 연결해 체인처럼 관리한다. 

LinkedList에서는 특정 인덱스의 객체를 제거할 경우, 해당 객체를 기준으로 바로 앞뒤 링크만 변경이 되고, 나머지 링크는 변경되지 않는다. 이는 삽입할 때도 동일하다. 그러므로 체인의 중간에 객체 삭제와 삽입이 자주 일어나는 경우에는 ArrayList보다 LinkedList가 더 나은 성능을 보여준다. 



- **형식**

```java
List<타입 파라미터> list = new LinkedList<타입 파라미터>();
```



- **예시**

  ArrayList를 LinkedList로 넘기는 방법 

  ```java
  package main;
  
  import java.util.ArrayList;
  import java.util.LinkedList;
  import java.util.Iterator;
  
  public class MainClass_linkedlist {
  
  	public static void main(String[] args) {
  
  		ArrayList<String> alist = new ArrayList<String>();
  		
  		alist.add("tigers");
  		
  		String str = new String("lions");
  		alist.add(str);
  		
  		alist.add("giants");
  		
  		// arraylist를 linkedlist에 넘기는 방법 
  		LinkedList<String> list = new LinkedList<String>( alist );
  		
  		for( String s : list ) {
  			System.out.println(s);
  		}
  		System.out.println();
  		list.add("twins");
  		
  		// 맨 앞에 Object를 추가(linkedlist에만 있음) 
  		list.addFirst("bears");
  		
  		// 맨 뒤에 object를 추가 
  		list.addLast("kings");
  		/*
  		for(String s : list) {
  			System.out.println(s);
  		}
  		*/
  		
  // iterator : List<String> 컬렉션에서 요소를 순차적으로 처리하기 위한 반복자 
  		Iterator<String> it;
  		it = list.iterator();
  		
  		while(it.hasNext()) {
  			String value = it.next();
  			System.out.println(value);
  		}
  	
  	}
  }	// 출력값 lions giants twins kings
  ```

  

### 2. HashMap

HashMap에 앞서 Map컬렉션에 대해 먼저 살펴보자. Map 컬렉션은 키와 값으로 구성된 Entry 객체를 저장하는 구조로 되어있다. 키와 값은 모두 객체이며, 키는 중복 저장이 불가하나 값은 중복 저장이 가능하다. 만약 기존에 있는 키와 동일한 키로 값을 저장할 경우, 기존의 값은 없어지고 새로운 값으로 대체된다. 

그 중 HashMap은 대표적인 Map 컬렉션이다. HashMap에서 키로 사용할 객체는 hashCode()와 equals() 메소드를 재정의하여 동일한 키가 될 조건을 가져야 한다. 구체적으로 hashCode()의 리턴값이 같아야 하며, equals() 메소드가 true를 리턴해야 하는 것이 그 조건이다. 

보통 많이 쓰이는 키 타입은 String이다. String은 문자열이 같은 경우에 동일한 객체가 될 수 있도록 hashCode()와 equals() 메소드가 재정의되어 있다. 또한 HashMap에서는 키와 값의 기본타입(byte, short, int, float, double, boolean, char)를 쓸 수 없고 클래스 및 인터페이스 타입만 가능하다. 

HashMap 생성 방법은 다음과 같다. 

```java
Map<key, value> hMap = new HashMap<key,value>(); 
```

  

아래의 예시들을 통해 이해해보자

- **예시1**

```java
// 인스턴스를 클래스로 만든 경우 
// HashMap<Integer, String> hMap  = new HashMap<Integer, String>();
		
// 인스턴스를 인터페이스로 만든 경우 
Map<Integer, String> hMap = new HashMap<Integer, String>();
	

// 추가 
hMap.put(1, "하나");
hMap.put(new Integer(2), "둘");
hMap.put(3, "셋");
		

// 값을 산출 key -> value 
String value = hMap.get(2); // 만약 key가 apple이면 value로 사과 출력
System.out.println(value);
		
	
// 삭제
String del = hMap.remove(2); // 지운값이 뭔지 보여줌 
System.out.println(del);
// 지워진 값을 보여줄 뿐이지만, 지워진 상태이다. 현재 1,3만 있는 상태 
		

// 검색
// 있다 / 없다   key값을 먼저 있는지없는지 찾고, 있으면 출력
boolean b = hMap.containsKey(3); 
if (b) {   // b가 트루이면, 즉, 3이 있을때
	String val = hMap.get(3);
	System.out.println(val);
}
		
		
// 수정 
hMap.replace(3, "쓰리");  
System.out.println(hMap.get(3));


// 추가 > 있는 key값에 대해 추가를 하면 수정이 되어버린다. 
hMap.put(3, "삼");
System.out.println(hMap.get(3));
		
hMap.put(4, "사");
hMap.put(5, "오");
		
		
// map에 들어있는 모든 키와 값을 출력 
// (있는 요소가 나오는 것이지, 넣은 순서대로 나오는게 아니다(선형구조가 아니라 트리구조니깐))
Iterator<Integer> it = hMap.keySet().iterator();
		
while(it.hasNext()) {
			
	Integer key1 = it.next();
	System.out.print("key: " + key1 + " ");
			
	String V = hMap.get(key1);
	System.out.println("value: " + V);
}
```



- **예시2**

  ```java
  // 좋아하는 과일 5개 
  Map<String, String> map = new HashMap<String, String>();
  		
  
  // 추가 
  map.put("apple", "사과");
  map.put("grape", "포도");
  map.put("melon", "멜론");
  map.put("pear", "배");
  map.put("blueberry", "블루베리");
  		
  
  // 값을 산출 
  String fruit = map.get("pear");
  System.out.println(fruit);
  		
  
  // 모든 키와 값을 출력
  Iterator<String> it2 = map.keySet().iterator();
  while(it2.hasNext()) {
  	String key2 = it2.next();
  	System.out.print("key: " + key2 + " ");
  		
  	String V2 = map.get(key2);
  	System.out.println("value: " + V2 + " ");
  }
  /*
  출력값 
  key: apple value: 사과 
  key: pear value: 배 
  key: blueberry value: 블루베리 
  key: grape value: 포도 
  key: melon value: 멜론 
  */
  ```



### 3. 이진 트리 구조 및 TreeMap

- **이진 트리 구조**

이진 트리 구조란 여러 개의 노드가 트리 형태로 연결된 구조로, 첫 시작이 되는 루트 노드부터 각 노드에 최대 2개의 노드를 연결할 수 있는 구조를 말한다. 위 아래로 연결된 노드의 형태를 부모-자식 관계에 있다고 한다. 하나의 부모 노드에는 최대 두개의 자식 노드가 연결될 수 있다. 

이러한 노드는 정렬하는 규칙이 있는데, 부모 노드의 값보다 작은 노드라면, 부모 노드의 왼쪽 아래에 위치시키고, 부모 노드보다 큰 노드라면, 부모 노드의 오른쪽 아래에 위치시킨다. 이 규칙을 계속 진행하면 결국 왼쪽 마지막 노드에 가장 작은 값이 오고, 오른쪽 마지막 노드에는 가장 큰 값이 온다. 따라서 [왼쪽 노드 - 부모 노드 - 오른쪽 노드] 순서로 값을 읽으면 오름차순, 반대로 [오른쪽 노드 - 부모 노드 - 왼쪽 노드] 순서로 값을 읽으면 내림차순으로 정렬할 수 있다. 



- **TreeMap**

TreeMap은 이진 트리를 기반으로 한 Map 컬렉션으로서 키와 값이 저장되는 Map.Entry를 저장한다. TreeMap에 객체를 저장하면 자동으로 정렬이 되며 기본적으로 부모 키값보다 낮은 키값은 왼쪽 자식 노드에, 높은 키값은 오른쪽 자식노드에 Map.Entry 객체를 저장한다. 

전반적인 형태가 유사하다는 점에서 HashMap에 Key를 기준으로 Sorting이 가능한 형태라고 볼 수도 있다. 

Map.Entry의 경우, Map.Entry<Key, Value>를 공통적으로 리턴 타입으로 가지며, 메소드에는 firstEntry(). lastEntry(), lowerEntry(), higherEntry()등 이외에도 다양한 메소드들이 있다. 



- **형식**

```java
TreeMap<Key, Value> treemap = new TreeMap<Key, Value>;
```



- **예시 (위의 HashMap예시를 가져와서 정렬해보기)**

```java
// TreeMap
TreeMap<String, String> tMap = new TreeMap<String, String>(map); 
		

// 오름 
//Iterator<String> itKey = tMap.keySet().iterator();
		

// 내림
Iterator<String> itKey = tMap.descendingKeySet().iterator();
		
while(itKey.hasNext()) {
	String k = itKey.next();
	String v = tMap.get(k);
	System.out.println("key: " + k + " value: "+ v);
}

/*
출력값
key: pear value: 배
key: melon value: 멜론
key: grape value: 포도
key: blueberry value: 블루베리
key: apple value: 사과
*/
```

