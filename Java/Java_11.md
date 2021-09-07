## Java 11

#### Practice(Student 클래스, 선택정렬 클래스화)

### 1. Student 클래스

```java
public class Student {
	// 필드
    String name;
	int ban;
	int no;
	int kor;
	int eng;
	int math;
	
	int sum;
	
    // 호출 시에 kor, eng, math 점수를 매개변수로 받아 sum을 구하고 리턴하는 메소드 
	public int getTotal(int kor, int eng, int math) {
		sum = kor + eng + math; 
		return sum;
	}
	// 매개변수를 받지 않고, 호출되면 바로 평균값을 리턴하는 매소드
	public double getAverage() {
		double avg = sum / 3; 		
		return avg;
	}
}
```



MainClass에서 메소드 호출 

```java
public class MainClass {

	public static void main(String[] args) {
		// 클래스 변수 = new 생성자
		Student s = new Student();
		// s라는 참조변수로 Student클래스의 필드를 사용한다. 
		s.name = "홍길동";
		s.ban = 1;
		s.no = 1;
		s.kor = 100;
		s.eng = 60;
		s.math = 76;
		
		System.out.println("이름:" + s.name );
		System.out.println("총점:" + s.getTotal(s.kor, s.eng, s.math) ); 
		System.out.println("평균:" + s.getAverage() );
	}
}
```



### 2. 선택정렬의 클래스화 

Sorting클래스

```java
import java.util.Scanner;

public class Sorting {
	// 필드로 갖고있는게 좋은 경우는 해당 필드로 두가지 이상의 처리가 이뤄지는 경우이다. 
	// 만약 한가지 처리만 하게 되면 지역변수를 쓰는게 낫다.
	private int number[];	// 배열을 필드로 선언 
	private String updown;	// 오름차순 또는 내림차순을 받기 위한 필드 
	
	// 1. 입력
	public void input() {
		Scanner sc = new Scanner(System.in);
		
		// 배열 내의 입력받을 수의 갯수
		System.out.print("정렬할 수의 총수 = ");
		int count = sc.nextInt();
		
		// 배열의 할당
		number = new int[count];
		
		// 숫자들의 입력
		for(int i = 0;i < number.length; i++) {
			System.out.print( (i + 1) + "번째 수 = " );
			number[i] = sc.nextInt();
		}
		
		// 오름/내림
		System.out.print("오름/내림 = ");
		updown = sc.next();
	}
	
	// 2. 정렬
	public void sorting() {
		int temp;
		for(int i = 0;i < number.length - 1; i++) {				
			for(int j = i + 1;j < number.length; j++) {
				
				if(updown.equals("오름")) {						
					if(number[i] > number[j]) {
						temp = number[i];
						number[i] = number[j];
						number[j] = temp;
					}						
				}
				else if(updown.equals("내림")) {						
					if(number[i] < number[j]) {
						temp = number[i];
						number[i] = number[j];
						number[j] = temp;
					}
				}				
			}			
		}
	} 
	
	// 3. 출력
	public void print() {
		for(int i = 0;i < number.length; i++) {
			System.out.print(number[i] + " ");
		}
	}	
}
```

