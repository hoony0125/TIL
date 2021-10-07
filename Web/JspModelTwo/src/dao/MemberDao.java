package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DBClose;
import db.DBConnection;
import dto.MemberDto;

public class MemberDao {
// 이 클래스에 있는 메소드는 getInstance를 통해 어디서든지 부를 수 있다
// 싱글톤형태

	private static MemberDao dao = null;
	
	private MemberDao() {
		DBConnection.initConnection();
	}
	
	public static MemberDao getInstance() {
		if(dao == null) {
			dao = new MemberDao();
		}
		return dao;
	}
	
	public boolean addMember(MemberDto dto) {
		
		String sql = " INSERT INTO MEMBER(ID, PWD, NAME, EMAIL, AUTH) "
				+ "    VALUES(?, ?, ?, ?, 3) ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/3 addMember success");	
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getPwd());
			psmt.setString(3, dto.getName());
			psmt.setString(4, dto.getEmail());			
			System.out.println("2/3 addMember success");
			
			count = psmt.executeUpdate();
			System.out.println("3/3 addMember success");
			
		} catch (SQLException e) {			
			e.printStackTrace();
			System.out.println("addMember fail");
		} finally {
			DBClose.close(conn, psmt, null);
		}
		
		return count>0?true:false;
	}
	
	
	
	
	// 아이디 중복확인 쿼리 작성 후 dao작성
	
	public boolean getId(String id) {
		String sql = " SELECT COUNT(*) "
				   + " FROM MEMBER "
				   + " WHERE ID=? ";
	
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		int findId = 0;
		
		
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/3 getId success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id.trim());
			System.out.println("2/3 getId success");
			
			rs=psmt.executeQuery();	
			System.out.println("3/3 getId success");
			
			if(rs.next()) {
				findId=rs.getInt(1); 
			}
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			System.out.println("getId fail");
		}finally {
			DBClose.close(conn, psmt, rs);
		}
		return findId>0?true:false;
	}
	
	
	public MemberDto login(String id,String pwd) {		// loginAf에서 id,pwd를 받는다(?)
		
		String sql = " SELECT ID, NAME, EMAIL, AUTH "
				   + " FROM MEMBER "
				   + " WHERE ID=? AND PWD=? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		MemberDto mem = null;
		
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/3 login success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1,id);
			psmt.setString(2,pwd);
			System.out.println("2/3 login success");
			
			rs = psmt.executeQuery();
			System.out.println("3/3 login success");
			
			if(rs.next() == true) {
				String _id = rs.getString("id");		// "id","name","email","auth" 대신 1,2,3,4로 적어도된다. 
				String _name = rs.getString("name");
				String _email = rs.getString("email");
				Integer _auth = rs.getInt("auth");
				
				mem = new MemberDto(_id, null, _name, _email, _auth);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			System.out.println("login fail");
		}finally {
			DBClose.close(conn, psmt, rs);
		}
		
		return mem;
	}
	
	
}








