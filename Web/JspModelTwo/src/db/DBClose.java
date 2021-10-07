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
