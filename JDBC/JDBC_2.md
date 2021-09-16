## JDBC 2

#### Insert, Update, Select, Delete 

#### 0. 커넥션 초기화 

`DBConnection.initConnection();	`

### 1. Insert

MainClass에서 값을 입력받아  Insert하는 형태의 예를 보자.

```java
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import db.DBConnection;

public class InsertTest {
	
		
	public int insert(String id, String name, int age) {
		
		String sql = " INSERT INTO USERDTO(ID, NAME, AGE, JOINDATE) "						//쿼리문이 sql로 들어간다.
				   + "VALUES( '" + id + "', '" + name + "'," + age + ", SYSDATE) "; 
		Connection conn = DBConnection.getConnection();			
        											// Connection은 getConnection을 받는 엄밀히 말하면 인터페이스이다.
		Statement stmt = null;   					// null로 초기화			
		
		int count = 0; 
		
		try {
			stmt = conn.createStatement();			// 현재 상황을 보기 위해 null로 초기화하고, 현재 상태를 확인한다.
			
			count = stmt.executeUpdate(sql);    
            // 현재 상황을 가지고 쿼리문(sql)으로 업데이트 그리고 count로 하는 이유는 몇개가 insert되는지 볼려고! db가 몇개 바뀌었나
													
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {									// finally부분은 데이터와의 연결을 마치고 난 후 뒷처리라고 생각하면 된다. 
			try {
			
			if(stmt != null) {						//  메모리 영역이 열려있으면 닫아라 
				stmt.close();
			}
			if(conn != null) {						// 커넥션 연결이 열려있으면 닫아라 
				conn.close();						// 커넥션을 끊어라 
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
	return count;	
		
	}	
}

```



- **MainClass에서 호출** 

```
InsertTest it = new InsertTest();
		
String id = "bbb";
String name = "성춘향";
int age = 16;
int count = it.insert(id, name, age);
if(count > 0) {
	System.out.println("정상적으로 추가되었습니다.");
}
```



### 2. Update

MainClass에서 이름과 나이를 받아 수정해보자 

```java
package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import db.DBClose;
import db.DBConnection;

public class UpdateTest {

	public int update(String name, int age, String id) {
		
		String sql = " UPDATE USERDTO "
				   + " SET NAME = '" + name + "', AGE = " + age + " "
				   + " WHERE ID = '" + id + "' ";
		
		Connection conn = DBConnection.getConnection();
		Statement stmt = null;
		
		int count = 0;
		
		try {
			stmt = conn.createStatement();
			
			count = stmt.executeUpdate(sql);			
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally {
			
			DBClose.close(conn, stmt, null);			
		}
		
		return count;
	}
}
```



- **MainClass에서 호출** 

```
		String name = "홍두께";
		int age = 22;
		
		UpdateTest ut = new UpdateTest();
		
		int count = ut.update(name, age, "aaa"); 
		if(count > 0) {
			System.out.println("정상적으로 수정되었습니다");
		}
```





### 3. Select 

Select는 두가지 경우가 나뉘는데, 하나의 데이터만 가져오는 경우와 다수의 데이터를 가져오는 경우이다. 

- **하나의 데이터만 가져오기**

  Statement로 하기 & PreparedStatement로 하기 

```java
	/* Statement사용 
	public UserDto search(String id) {
		
		String sql = " SELECT ID, NAME, AGE, JOINDATE "
			   + " FROM USERDTO "
			   + " WHERE ID = '" + id + "' ";
	
		Connection conn = DBConnection.getConnection();
		Statement stmt = null;
		ResultSet rs = null; 
		
		UserDto dto = null; 
		
		try {
			stmt = conn.createStatement();								
			// 현재 상태를 생성해주는 것 
				
			rs = stmt.executeQuery(sql);								
			// insert와 update와 delete는 executeUpdate이지만, select는 executeQuery를 쓴다. 
			
			if(rs.next() == true) {										
			// true는 생략가능  true라는 것은 DB로부터 넘어 온 데이터가 있다는 것이다. 
				
				String _id = rs.getString("id");
				String _name = rs.getString("name");					
			// 뒤에 있는 id와 name은 데이터에 있는 컬럼명 앞에 _id와 _name은 구분해주기 위해 언더				바를 사용 
				int _age = rs.getInt("age");
				String _joindate = rs.getString("joindate");
				
				dto = new UserDto(_id, _name, _age, _joindate); 		
				// dto에 가지고 온 데이터를 넣어주는 것! 
			}
	
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBClose.close(conn, stmt, rs);
		}
		return dto;
	}	
	*/
	



	// PreparedStatement 사용 
	public UserDto search2(String id) {
		
		String sql = " SELECT ID, NAME, AGE, JOINDATE "
				   + " FROM USERDTO "
				   + " WHERE ID = ? ";
		
		Connection conn = DBConnection.getConnection();
		PreparedStatement psmt = null;
		ResultSet rs = null; 						
        // resultset은 select에서만 필요하다.
        // 나머지는 몇개가 삽입, 수정, 삭제 되었는지는 이렇게 개수로 접근을 하기 때문에
		
		UserDto dto = null; 
		
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1,id);					
            // 물음표 있는 곳으로 id가 쏙 들어간다 sql 쿼리문에서 바느질 할 필요가 없다.(바느질:따				옴표 붙이는 작업)
			
			rs = psmt.executeQuery();
			
			if(rs.next() == true) {										
                // == true는 생략가능!  true라는 것은 DB로부터 넘어 온 데이터가 있다는 것이다. 
						
				String _id = rs.getString("id");
				String _name = rs.getString("name");					
                // 뒤에 있는 id와 name은 데이터에 있는 컬럼명이다.
                //앞에 _id와 _name은 구분해주기 위해 언더바를 사용했다.
				int _age = rs.getInt("age");
				String _joindate = rs.getString("joindate");
						
				dto = new UserDto(_id, _name, _age, _joindate); 		
                //  가지고 온 데이터를 dto에 넣어주는 것이다.
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBClose.close(conn, psmt, rs);
		}
		return dto;
	}	
```



- **MainClass에서 호출하기** 

  ```java
  String id = "bbb";
  UserDto dto = null;
  		
  SelectTest st = new SelectTest();
  		
  		
  //dto=st.search(id);		// Statement로 한 경우
  dto=st.search2(id);
  if(dto != null) {
  	System.out.println(dto.toString());
  }
  ```





- 다수의 데이터를 가져오기 

  ```java
  public List<UserDto> getUsers(){
  		
  	String sql = " SELECT ID, NAME, AGE, JOINDATE"
  			   + " FROM USERDTO ";
  	
  	Connection conn = DBConnection.getConnection();
  	PreparedStatement psmt = null;
  	ResultSet rs = null; 
  	
  	List<UserDto> list = new ArrayList<UserDto>();
  		
  	try {
  		conn = DBConnection.getConnection();
  		psmt = conn.prepareStatement(sql);
  		rs =  psmt.executeQuery();
  	
  		while(rs.next() == true) {			
         // 다음 데이터가 있으면 true 계속 돌다가 다음 데이터가 없으면 false가 되고 넘어간다.
  			
  			String _id = rs.getString("id");
  			String _name = rs.getString("name");
  			int _age = rs.getInt("age");
  			String _joindate = rs.getString("joindate");
  				
  			UserDto dto = new UserDto(_id, _name, _age, _joindate);
  			list.add(dto);
  		}
  			
  	} catch (SQLException e) {
  		e.printStackTrace();
  	}finally {
  		DBClose.close(conn, psmt, rs);			
             // 마무리로 DB와 연결된 것이 있으면 닫아라! 함수로 작성
  	}
  		
  	return list;
  		
  }	
  ```

  

- **MainClass에서 호출하기** 

```java
SelectTest st = new SelectTest();
List<UserDto> list = st.getUsers();
	
for(UserDto user : list) {							// for each문 사용 
	System.out.println(user.toString());
}	
```





### 4. Delete

```java
package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import db.DBClose;
import db.DBConnection;

public class DeleteTest {
	
	public int delete(String name) {
		
		String sql = " DELETE FROM USERDTO "
				   + " WHERE NAME = ? ";
		
		
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
		return count;
	}
}

```



- **MainClass에서 호출하기** 

```java
DeleteTest dt = new DeleteTest();
String name = "성춘향";
int count = dt.delete(name);
if(count > 0) {
	System.out.println("정상적으로 삭제되었습니다.");
}
```

