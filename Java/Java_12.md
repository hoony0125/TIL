## Java 12

#### DTO, DAO

### 1. DTO 

- DTO: Data Transfer Object는 계층간 데이터 교환을 위한 객체를 말한다. 여기서 말하는 계층은 Controller, View, Business Layer, Persistent Layer를 말한다. 일반적인 DTO는 로직을 갖고 있지 않는 순수한 데이터 객체이 속성과 그 속성에 접근하기 위한 getter, setter 메소드만 가진 클래스를 말한다. 

  DTO와 거의 흡사한 역할을 하는 VO(Value Object)도 있는데, 둘의 차이는  객체를 받아서 수정할 수 있는지의 여부이다. VO는 Read-only 속성을 가졌기 때문에 읽어올 수는 있으나, 수정은 불가하다는 점에서 DTO와 다르다. 



- DTO 만드는 방법 

  1. 테이블의 필드명을 속성으로 선언한다.

  2. 생성자를 구현한다.(Source > Generate Constructor using Field)

  3. 각 속성에 대한 getter/setter 메서드를 구현한다. (Source > Generate Getters and Setters)

     (\+α.  Generate toString으로 결과를 확인한다.)

```java
package dto;

public class StudentDto {
	private int number;
	private String name;
	private int kor;
	private int eng; 
	private int math; 
	

	public StudentDto(int number, String name, int kor, int eng, int math) {
		super();
		this.number = number;
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
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

	public int getKor() {
		return kor;
	}

	public void setKor(int kor) {
		this.kor = kor;
	}

	public int getEng() {
		return eng;
	}

	public void setEng(int eng) {
		this.eng = eng;
	}

	public int getMath() {
		return math;
	}

	public void setMath(int math) {
		this.math = math;
	}

	@Override
	public String toString() {
		return "StudentDto [number=" + number + ", name=" + name + ", kor=" + kor + ", eng=" + eng + ", math=" + math
				+ "]";
	}
}
```



### 2. DAO

- DAO: Data Access Object는 데이터베이스의 데이터에 접근하기 위한 객체이다. DB에 접근해 CRUD(추가, 읽기, 수정, 삭제) 작업할 수 있는 기능을 담당한다. 

**2.1. CREATE**

```java
package dao;

import java.util.Scanner;

import dto.StudentDto;

// DAO (Data Access Object) - CRUD( Create Read Update Delete ) - 추가 검색 수정 삭제

public class StudentDao {
	Scanner sc = new Scanner(System.in);
	
	private StudentDto stArray[] = new StudentDto[10];
	int count;
	
	public StudentDao() {
		
		stArray[0] = new StudentDto(1001, "홍길동", 90, 85, 100);
		stArray[1] = new StudentDto(1002, "성춘향", 100, 90, 95);
		stArray[2] = new StudentDto(1003, "일지매", 100, 80, 90);
        // 실습하기 위해 그냥 실습용 데이터를 넣어두었다 
        // 여기서 추가하려면 3번지부터 들어가게 count 시작부분 수정이 필요하다
		count = 3;
	}

	public void Create() {
		System.out.print("학생번호 = ");
		int number = sc.nextInt();
		
		System.out.print("학생이름 = ");
		String name = sc.next();
		
		System.out.print("국어 = ");
		int kor = sc.nextInt();
		
		System.out.print("영어 = ");
		int eng = sc.nextInt();
		
		System.out.print("수학 = ");
		int math = sc.nextInt();
		
		stArray[count] = new StudentDto(number, name, kor, eng, math);
		
		count++;
		System.out.println("count:" + count);
		System.out.println("추가 되었습니다");
	}
```



**2.2. READ**

```java
	public void Read() {	       // 학생 정보 검색
		System.out.print("검색할 학생이름을 입력 >> ");
		String name = sc.next();
		// 검색
		int findIndex = -1;
		for(int i = 0;i < stArray.length; i++) {
			StudentDto dto = stArray[i];
			if(dto != null && name.equals(dto.getName())) {
				findIndex = i;
				break;
			}			
		}
		
		// 출력
		if(findIndex == -1) {
			System.out.println("학생명단에 없습니다");
		}
		else {
			StudentDto dto = stArray[findIndex];
			System.out.println(dto.toString());
		}
	}


// 데이터 모두 읽어오기 
	public void allprint() {
		for (int i = 0; i < stArray.length; i++) {
			StudentDto dto = stArray[i];
			if(dto != null && dto.getName().equals("") == false) {		
                // 빈칸이 아니고, 공백이 아니라면 출력 
				System.out.println(dto.toString());
			}
		}
	}
```



**2.3. Update**

```java
	
	public void Update() {	// 학생 정보 수정
		System.out.print("수정할 학생이름을 입력 >> ");
		String name = sc.next();
		
		// 검색
		int findIndex = -1;			
		for(int i = 0;i < stArray.length; i++) {
			StudentDto dto = stArray[i];
			if(dto != null && name.equals(dto.getName())) {
				findIndex = i;
				break;
			}			
		}
				
		// 수정
		if(findIndex == -1) {						//-1이라는건 못찾은 경우임 		
			System.out.println("학생명단에 없습니다");
		}
		else {										// 찾은 데이터가 있을 때 
			// 입력
			System.out.println("수정할 데이터를 입력해 주십시오");
			System.out.print("국어 = ");
			int kor = sc.nextInt();
			
			System.out.print("영어 = ");
			int eng = sc.nextInt();
			
			System.out.print("수학 = ");
			int math = sc.nextInt();
			
			StudentDto dto = stArray[findIndex];
			dto.setKor(kor);
			dto.setEng(eng);
			dto.setMath(math);
			
			System.out.println("학생 데이터를 수정했습니다"); 			
		}
	}
```



**2.4. Delete**

```java
	public void Delete() {		// 학생 정보 삭제
		System.out.print("삭제할 학생이름을 입력 >> ");
		String name = sc.next();
		
		// 검색
		int findIndex = -1;		// 배열이 0부터 시작하니깐 -1로 설정
		for(int i = 0;i < stArray.length; i++) {
			StudentDto dto = stArray[i];	
			if(dto != null && name.equals(dto.getName())) {		
                // null(빈칸)이 아니고 이름과 같을 때 
				findIndex = i;
				break;
			}			
	}
```



### 3. Main Class 

```JAVA
package main;

import java.util.Scanner;

import dao.StudentDao;

public class MainClass_studentprogram {

	public static void main(String[] args) {
	
		/*
		StudentDto dto[] = new StudentDto[10];	// 배열만 생성! 객체를 생성한 것이 아니다!		
		dto[0] = new StudentDto(1, "홍길동", 90, 100, 80);   
		// 첫번째 학생에 대한 동적할당, 객체생성을 해주었다. 
		
		System.out.println(dto[0].toString());
		
		int array[] = new int[10];
		
		int number = 123;		
		StudentDto st = new StudentDto();
		*/
		
		/*
		StudentDto arrDto[] = new StudentDto[10];	// 배열만 생성
		
		for(int i = 0;i < arrDto.length; i++) {
			arrDto[i] = new StudentDto();			// 객체를 생성
		}
		*/	
		
		/*
		StudentDto dto1, dto2, dto3, dto4;
		
		dto1 = new StudentDto();
		dto1.setNumber(1001);		
		
		dto2 = new StudentDto();
		dto3 = new StudentDto();
		dto4 = new StudentDto();
		*/
		
		Scanner sc = new Scanner(System.in);
		
		StudentDao dao = new StudentDao();
		
		while(true) {
			System.out.println("1.학생정보 추가");
			System.out.println("2.학생정보 삭제");
			System.out.println("3.학생정보 검색");
			System.out.println("4.학생정보 수정");
			System.out.println("5.학생정보 모두출력");
			
			System.out.println("어느 작업을 하시겠습니까?");
			System.out.print(">> ");
			
			int work = sc.nextInt();
			
			switch(work) {
				case 1:
					dao.Create(); 
					break;
				case 2:
					dao.Delete();
					break;
				case 3:
					dao.Read();
					break;
				case 4:
					dao.Update();
					break;
				case 5:
					dao.allprint();
					break;			
			}			
		}
	}
}
```



출력하면 아래처럼 나온다. 

<img src="../../../Java_12.assets/image-20210912141520323.png" alt="image-20210912141520323" style="zoom:80%;" />
