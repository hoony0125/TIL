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
