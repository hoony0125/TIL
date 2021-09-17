## JDBC 3 

#### Practice 

데이터로 학생 번호, 이름, 국어, 영어, 수학 점수를 저장하고, MainClass에서 사용자의 선택과 이름 입력에 따라 학생정보를 추가, 삭제, 검색, 수정, 모두 출력 할 수 있는 코드를 작성해보자 

- **테이블 생성** 

```sql
CREATE TABLE STUDENT(
	NUM NUMBER(5) PRIMARY KEY,
	NAME VARCHAR2(30) NOT NULL,
	KOR NUMBER(3),
	ENG NUMBER(3),
	MATH NUMBER(3)
);

CREATE SEQUENCE STUSEQ
INCREMENT BY 1 
START WITH 100;

SELECT *
FROM STUDENT;
```



- **MainClass 생성(메뉴)**

```java
package main;

import java.util.Scanner;
import java.util.List;
import dao.StudentDao;
import dto.StudentDto;

public class MainClass_studentprogram {

	public static void main(String[] args) {
	
		
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
					List<StudentDto> list = dao.allprint();
					
					for(StudentDto user : list) {
						System.out.println(user.toString());
					}
					break;			
			}			
		}
	}
}

```



- **DBConnection과 DBClose 클래스 생성**

  ```java
  package db;
  
  import java.sql.Connection;
  import java.sql.DriverManager;
  import java.sql.SQLException;
  
  public class DBConnection {
  
  	public static void initConnection() {			// static method로 만들어놔서 불러올 때는 DBConnection.initConnection만 쓰면된다. 
  		try {
  			Class.forName("oracle.jdbc.driver.OracleDriver");
  			
  			System.out.println("Driver Loading Success!");
  		} catch (ClassNotFoundException e) {			
  			e.printStackTrace();
  		}
  	}
  	
  	public static Connection getConnection() {		// static method로 만들어놔서 불러올 때는 DBConnection.getConnection만 쓰면된다. 
  		Connection conn = null;		
  		try {
  			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "HR", "HR");
  			
  			System.out.println("DB Connection Success!");			
  		} catch (SQLException e) {			
  			e.printStackTrace();
  		}
  		
  		return conn;
  	}
  }
  ```

  

  ```java
  package db;
  
  import java.sql.Connection;
  import java.sql.ResultSet;
  import java.sql.SQLException;
  import java.sql.Statement;
  							// DBClose는 사실 DBConnection에 같이 만들어서 해도된다. 다만 가독성이 이렇게 하면 좀 더 올라가니깐 이렇게 했다. 
  public class DBClose {		// static method로 만들어놔서 불러올 때는 DBClose.close만 쓰면된다. 
  
  	public static void close(Connection conn, Statement stmt, ResultSet rs) {
  		try {
  			
  			if(conn != null) {
  				conn.close();
  			}
  			if(stmt != null) {
  				stmt.close();
  			}
  			if(rs != null) {
  				rs.close();
  			}
  			
  		} catch (SQLException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
  	}
  }
  
  ```



- **StudentDto 생성** 

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



- **StudentDao 생성**

  ```java
  package dao;
  
  import java.sql.Connection;
  import java.sql.PreparedStatement;
  import java.sql.ResultSet;
  import java.sql.SQLException;
  import java.util.ArrayList;
  import java.util.List;
  import java.util.Scanner;
  
  import db.DBClose;
  import db.DBConnection;
  import dto.StudentDto;
  
  
  public class StudentDao {
  	Scanner sc = new Scanner(System.in);				
  	
  
  	
  	public StudentDao() {
  		DBConnection.initConnection();			
          // 생성자 부분에서 만들어주기, 근데 사실 Main클래스에 해줘도 상관은 없다. 
  	
  	}
  
      
      
      
  	public void Create() {					// INSERT
  
  		System.out.print("학생이름 = ");
  		String name = sc.next();
  		
  		System.out.print("국어 = ");
  		int kor = sc.nextInt();
  		
  		System.out.print("영어 = ");
  		int eng = sc.nextInt();
  		
  		System.out.print("수학 = ");
  		int math = sc.nextInt();
  		
  		
  		//insert
  		String sql = " INSERT INTO STUDENT(NUM, NAME, KOR, ENG, MATH) "
  				   + " VALUES(STUSEQ.NEXTVAL, ?, ?, ?, ?) ";
  		
  		Connection conn = DBConnection.getConnection();
  		PreparedStatement psmt = null;
  		int count = 0;
  		
  		try {
  			psmt = conn.prepareStatement(sql);
  			psmt.setString(1, name);
  			psmt.setInt(2, kor);
  			psmt.setInt(3, eng);
  			psmt.setInt(4, math);
  			
  			count = psmt.executeUpdate();
  				
  		} catch (SQLException e) {
  			e.printStackTrace();
  		}finally {
  			DBClose.close(conn, psmt, null);
  		}
  		
  		if(count > 0) {
  			System.out.println("추가 되었습니다");
  		}
  		else {
  			System.out.println("추가되지 않았습니다");
  		}	
  	}
  	
      
      
  	public void Delete() {				// 학생 정보 삭제
  		System.out.print("삭제할 학생이름을 입력 >> ");
  		String name = sc.next();
  
  		String sql = " DELETE FROM STUDENT "	
  				   + " WHERE NAME = ? ";
   // 와 쿼리문에서 테이블인 STUDENT가 당연한건데 STUDENTDTO 적어놓고 한참을 헤맸네.. 
  		Connection conn = DBConnection.getConnection();
  		PreparedStatement psmt = null;
  		int count = 0;
  		
  		
  		try {
  			psmt = conn.prepareStatement(sql);
  			psmt.setString(1, name);
  			
  			count = psmt.executeUpdate();
  			
  		} catch (SQLException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}finally {
  			DBClose.close(conn, psmt, null);
  		}
  		
  		if(count > 0) {
  			System.out.println("삭제 되었습니다");
  		}
  		else {
  			System.out.println("삭제되지 않았습니다");
  		}
  	}
  	
      
  	
  	public void Read() {				// 학생 정보 검색
  		System.out.print("검색할 학생이름을 입력 >> ");
  		
  		String name = sc.next();
  		
  		// select 하나 
  		
  		String sql = " SELECT NUM, NAME, KOR, ENG, MATH "
  				   + " FROM STUDENT "
  				   + " WHERE NAME = ? ";
  		
  		Connection conn = DBConnection.getConnection();
  		PreparedStatement psmt = null;
  		ResultSet rs = null; 
  		
  		StudentDto dto = null; 
  		
  		
  		try {
  			psmt = conn.prepareStatement(sql);
  			psmt.setString(1, name);
  			
  			rs = psmt.executeQuery();
  			
  			if(rs.next() == true) {
  				
  				int _num = rs.getInt("num");
  				String _name = rs.getString("name");
  				int _kor = rs.getInt("kor");
  				int _eng = rs.getInt("eng");
  				int _math = rs.getInt("math");
  				
  				dto = new StudentDto(_num, _name, _kor, _eng, _math);
  			}
  			
  		} catch (SQLException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}finally {
  			DBClose.close(conn, psmt, rs);
  		}
  		System.out.println(dto);
  }
  	
      
      
      
  	public void Update() {						// 학생 정보 수정
  		System.out.print("수정할 학생이름을 입력 >> ");
  		String name = sc.next();
  		
  		System.out.println("수정할 점수를 입력해 주십시오");
  		System.out.print("국어 = ");
  		int kor = sc.nextInt();
  		
  		System.out.print("영어 = ");
  		int eng = sc.nextInt();
  		
  		System.out.print("수학 = ");
  		int math = sc.nextInt();
  					
  		// Update
  		
  		String sql = " UPDATE STUDENT "
  				   + " SET KOR = ?, ENG = ?, MATH = ? "
  				   + " WHERE NAME = ? ";
  		
  		Connection conn = DBConnection.getConnection();
  		PreparedStatement psmt = null;
  		
  		int count = 0;
  		
  		
  		try {
  			psmt = conn.prepareStatement(sql);
  			
  			psmt.setInt(1, kor);
  			psmt.setInt(2, eng);
  			psmt.setInt(3, math);
  			psmt.setString(4, name);
  						
  			count = psmt.executeUpdate();
  		} catch (SQLException e) {
  			e.printStackTrace();
  		}finally {
  			DBClose.close(conn, psmt, null);
  		}
  		
  		if(count > 0) {
  			System.out.println("수정 되었습니다");
  		}
  		else {
  			System.out.println("수정되지 않았습니다");
  		}
  	}
  	
  	
      
      
  	public List<StudentDto> allprint() {			// 학생 모두 출력
  		
  		String sql = " SELECT NUM, NAME, KOR, ENG, MATH "
  				   + " FROM STUDENT ";
  		
  		Connection conn = DBConnection.getConnection();
  		PreparedStatement psmt = null;
  		ResultSet rs = null; 
  		
  		List<StudentDto> list = new ArrayList<StudentDto>();
  		
  		
  		try {
  			psmt = conn.prepareStatement(sql);
  			conn = DBConnection.getConnection();
  			rs = psmt.executeQuery();
  			
  			while(rs.next() == true) {
  				int _num = rs.getInt("num");
  				String _name = rs.getString("name");
  				int _kor = rs.getInt("kor");
  				int _eng = rs.getInt("eng");
  				int _math = rs.getInt("math");
  				
  				StudentDto dto = new StudentDto(_num, _name, _kor, _eng, _math);
  				list.add(dto);
  			}
  			
  		} catch (SQLException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}finally {
  			DBClose.close(conn, psmt, rs);
  		}
  		return list; 
  	
  	}
  }
  ```



후후 기본을 배우고 혼자서 좌충우돌 오랜 시간을 투자해서 해보니 뿌듯하기도 하고, 난 정말 아직 코송이(코딩 애송이)구나 싶다...코송이 화이팅! 