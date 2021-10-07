package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBClose;
import db.DBConnection;
import dto.BbsDto;
import dto.MemberDto;

public class BbsDao {	// 싱글톤으로!

	private static BbsDao dao = null;
	
	private BbsDao() {
	}
	
	public static BbsDao getInstance() {
		if(dao == null) {
			dao = new BbsDao();
		}
		return dao;
	}
	
	public List<BbsDto> getBbsList() {
		
		String sql = " SELECT SEQ, ID, REF, STEP, DEPTH, "
				+ "           TITLE, CONTENT, WDATE, "
				+ "           DEL, READCOUNT "
				+ "    FROM BBS "
				+ "    ORDER BY REF DESC, STEP ASC ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<BbsDto> list = new ArrayList<BbsDto>();
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/4 getBbsList success");
			
			psmt = conn.prepareStatement(sql);
			System.out.println("2/4 getBbsList success");
			
			rs = psmt.executeQuery();
			System.out.println("3/4 getBbsList success");
			
			while(rs.next()) {
				BbsDto dto = new BbsDto(rs.getInt(1), 
										rs.getString(2), 
										rs.getInt(3), 
										rs.getInt(4), 
										rs.getInt(5), 
										rs.getString(6), 
										rs.getString(7), 
										rs.getString(8), 
										rs.getInt(9), 
										rs.getInt(10));
				list.add(dto);
			}
			System.out.println("4/4 getBbsList success");
			
		} catch (SQLException e) {			
			e.printStackTrace();
			System.out.println("getBbsList fail");
		} finally {
			DBClose.close(conn, psmt, rs);
		}
		
		return list;
	}
	
	
	public boolean writeBbs(BbsDto dto) {	//BbsDto는 id, title, content를 다 가지고 있기 때문에 
		
		String sql = " INSERT INTO BBS "
				+ "	   	 (SEQ, ID, "
				+ "		  REF, STEP, DEPTH, "
				+ "       TITLE, CONTENT, WDATE, DEL, READCOUNT) "
				+ "     VALUES "
				+ "		  (SEQ_BBS.NEXTVAL, ?,   "							// 1	2	 3
				+ "		  (SELECT NVL(MAX(REF), 0)+1 FROM BBS), 0, 0, "		// 1	2	 3
				+ "        ?, ?, SYSDATE, 0, 0) ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		int count = 0;
		
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/3 writeBbs success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getTitle());
			psmt.setString(3, dto.getContent());
			System.out.println("2/3 writeBbs success");
			
			count = psmt.executeUpdate();
			System.out.println("3/3 writeBbs success");
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("writeBbs fail");
		}finally {
			DBClose.close(conn, psmt, null);
		}
		return count>0?true:false;
	}
	
	
	public BbsDto getBbs(int seq) {
		
		String sql = " SELECT SEQ, ID, REF, STEP, DEPTH, "
				+ "      TITLE, CONTENT, WDATE, DEL, READCOUNT "
				+ "    FROM BBS "
				+ "    WHERE SEQ=? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		BbsDto dto = null;
				
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/4 getBbs success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			System.out.println("2/4 getBbs success");
			
			rs = psmt.executeQuery();
			System.out.println("3/4 getBbs success");
			
			if(rs.next()) {
				dto = new BbsDto(rs.getInt(1), 
								rs.getString(2), 
								rs.getInt(3), 
								rs.getInt(4), 
								rs.getInt(5), 
								rs.getString(6), 
								rs.getString(7), 
								rs.getString(8), 
								rs.getInt(9), 
								rs.getInt(10));				
			}
			System.out.println("4/4 getBbs success");
			
		} catch (SQLException e) {			
			e.printStackTrace();
			System.out.println("getBbs fail");
		} finally {
			
			DBClose.close(conn, psmt, rs);
		}		
		return dto;
	}
	
	public void readcount(int seq) {
		
		String sql = " UPDATE BBS "
				+ "    SET READCOUNT=READCOUNT+1 "
				+ "    WHERE SEQ=? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
	
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/3 readcount success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			System.out.println("2/3 readcount success");
			
			psmt.executeUpdate();
			System.out.println("3/3 readcount success");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("readcount fail");
		} finally {
			DBClose.close(conn, psmt, null);
		}		
	}
	
	public boolean updateBbs(int seq, String title, String content) {
		String sql = " UPDATE BBS SET "
				+ " TITLE=?, CONTENT=? "
				+ " WHERE SEQ=? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/3 S updateBbs");
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, title);
			psmt.setString(2, content);
			psmt.setInt(3, seq);
			
			System.out.println("2/3 S updateBbs");
			
			count = psmt.executeUpdate();
			System.out.println("3/3 S updateBbs");
			
		} catch (Exception e) {			
			e.printStackTrace();
		} finally{
			DBClose.close(conn, psmt, null);			
		}		
		
		return count>0?true:false;
	}
	
	
	public boolean answer(int seq, BbsDto bbs){	// 부모글의 seq , 새로 입력 받았을 때 seq
		
		// update -> 다음 글을 위해 스텝을 늘려주는 역할
		String sql1 = " UPDATE BBS "
				+	  " SET STEP=STEP+1 "	// 3. 스텝을 하나 늘려라 
				+	  " WHERE REF=(SELECT REF FROM BBS WHERE SEQ=?) "	// 1. 현재 가지고있는 SEQ(3)와 같은 REF(3)를 가지며,
				+	  "       AND STEP > (SELECT STEP FROM BBS WHERE SEQ=? ) "; //2. 현재 가지고 있는 SEQ에 해당되는 스텝(3.0)보다 크다면(3.1 3.2 3.3 ...)
		
		// insert
		
		String sql2 = " INSERT INTO BBS(SEQ, ID,  "
				+	  "                 REF, STEP, DEPTH, "
				+	  "  				TITLE, CONTENT, WDATE, DEL, READCOUNT) "
				+ 	  " VALUES(SEQ_BBS.NEXTVAL, ?, "
				+	  "                 (SELECT REF FROM BBS WHERE SEQ=? ), "	// REF
				+	  "  				(SELECT STEP FROM BBS WHERE SEQ=? ) + 1, "	// STEP
				+	  "                 (SELECT DEPTH FROM BBS WHERE SEQ=? ) + 1, "	 // DEPTH
				+	  "                  ?, ?, SYSDATE, 0, 0) ";
	
		Connection conn = null;
		PreparedStatement psmt = null;
		
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);		// 자동적으로 저장되는 것을 끄는 것 
			System.out.println("1/6 answer success");
			
			// update
			psmt = conn.prepareStatement(sql1);
			psmt.setInt(1, seq);
			psmt.setInt(2, seq);
			System.out.println("2/6 answer success");
			
			count = psmt.executeUpdate();
			System.out.println("3/6 answer success");
			
			// psmt 초기화
			psmt.clearParameters();
						
			// insert
			psmt = conn.prepareStatement(sql2);
			psmt.setString(1, bbs.getId());
			psmt.setInt(2, seq);
			psmt.setInt(3, seq);
			psmt.setInt(4, seq);
			psmt.setString(5, bbs.getTitle());
			psmt.setString(6, bbs.getContent());
			System.out.println("4/6 answer success");
			
			count = psmt.executeUpdate();
			System.out.println("5/6 answer success");
			
			conn.commit();			// autocommit을 껐기 때문에 따로 해줘야 한다.
			System.out.println("6/6 answer success");
			
		} catch (SQLException e) {			
			e.printStackTrace();
			System.out.println("answer fail");
			
			try {
				conn.rollback();
			} catch (SQLException e1) {	e1.printStackTrace();	}			
		} finally {			
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			DBClose.close(conn, psmt, null);
		}
		
		return count>0?true:false;		
	}
	
	
	
	public List<BbsDto> getBbsSearchList(String choice, String search) {
		
		String sql = " SELECT SEQ, ID, REF, STEP, DEPTH, "
				+ "           TITLE, CONTENT, WDATE, "
				+ "           DEL, READCOUNT "
				+ "    FROM BBS ";
		
		String sWord = "";
		if(choice.equals("title")) {
			sWord = " WHERE TITLE LIKE '%" + search + "%' ";
		}else if(choice.equals("content")) {
			sWord = " WHERE CONTENT LIKE '%" + search + "%' ";
		}else if(choice.equals("id")) {
			sWord = " WHERE ID= '" + search + "' ";
		}
		
		sql = sql + sWord;
		
		sql = sql + "    ORDER BY REF DESC, STEP ASC ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<BbsDto> list = new ArrayList<BbsDto>();
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/4 getBbsList success");
			
			psmt = conn.prepareStatement(sql);
			System.out.println("2/4 getBbsList success");
			
			rs = psmt.executeQuery();
			System.out.println("3/4 getBbsList success");
			
			while(rs.next()) {
				BbsDto dto = new BbsDto(rs.getInt(1), 
										rs.getString(2), 
										rs.getInt(3), 
										rs.getInt(4), 
										rs.getInt(5), 
										rs.getString(6), 
										rs.getString(7), 
										rs.getString(8), 
										rs.getInt(9), 
										rs.getInt(10));
				list.add(dto);
			}
			System.out.println("4/4 getBbsList success");
			
		} catch (SQLException e) {			
			e.printStackTrace();
			System.out.println("getBbsList fail");
		} finally {
			DBClose.close(conn, psmt, rs);
		}
		
		return list;
	}
	
	public List<BbsDto> getBbsPagingList(String choice, String search, int pageNumber) {
		
		String sql = " SELECT SEQ, ID, REF, STEP, DEPTH, TITLE, CONTENT, WDATE, DEL, READCOUNT " 
				+ "    FROM ";
							// 아래부터 서브쿼리 시작 
				sql+= 		" (SELECT ROW_NUMBER()OVER(ORDER BY REF DESC, STEP ASC) AS RNUM, "
						+ "		 SEQ, ID, REF, STEP, DEPTH, TITLE, CONTENT, WDATE, DEL, READCOUNT "
						+ "	   FROM BBS ";
		
		
		String sWord = "";
		if(choice.equals("title")) {
			sWord = " 		   WHERE TITLE LIKE '%" + search + "%' ";
		}else if(choice.equals("content")) {
			sWord = " 		   WHERE CONTENT LIKE '%" + search + "%' ";
		}else if(choice.equals("id")) {
			sWord = " 		   WHERE ID= '" + search + "' ";
		}
		
		sql = sql + sWord;
		
		sql = sql + "    	   ORDER BY REF DESC, STEP ASC) ";	// 여기까지 서브쿼리 
		
		sql = sql + " WHERE RNUM >= ? AND RNUM <= ? ";
		// sql = sql + " WHERE RNUM BETWEEN ? AND ? ";
		
		// pageNumber = 0 1 2 
		int start, end; // start를 첫 물음표에 end는 다음 물음표에
		start = 1 + 10 * pageNumber;	// pageNumber가 0이면, 1 ~ 10 / 1이면 11 ~ 20
		end = 10 + 10 * pageNumber; 
		
		
		
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<BbsDto> list = new ArrayList<BbsDto>();
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/4 getBbsList success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, start);
			psmt.setInt(2, end);
			System.out.println("2/4 getBbsList success");
			
			rs = psmt.executeQuery();
			System.out.println("3/4 getBbsList success");
			
			while(rs.next()) {
				BbsDto dto = new BbsDto(rs.getInt(1), 
										rs.getString(2), 
										rs.getInt(3), 
										rs.getInt(4), 
										rs.getInt(5), 
										rs.getString(6), 
										rs.getString(7), 
										rs.getString(8), 
										rs.getInt(9), 
										rs.getInt(10));
				list.add(dto);
			}
			System.out.println("4/4 getBbsList success");
			
		} catch (SQLException e) {			
			e.printStackTrace();
			System.out.println("getBbsList fail");
		} finally {
			DBClose.close(conn, psmt, rs);
		}
		
		return list;
	}
		
	// 검색해도 페이징이 필요하기 때문에 검색 코드 중 일부가 들어온다
	public int getAllBbs(String choice, String search) {	// 페이징을 위해 글의 총 개수를 구하는 함수

		String sql = " SELECT COUNT(*) FROM BBS ";
		
		String sWord = "";
		if(choice.equals("title")) {
		sWord = " WHERE TITLE LIKE '%" + search + "%' ";
		}else if(choice.equals("content")) {
		sWord = " WHERE CONTENT LIKE '%" + search + "%' ";
		}else if(choice.equals("id")) {
		sWord = " WHERE ID= '" + search + "' ";
		}		
		sql = sql + sWord;
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		int len = 0;	// 몇개인지 리턴해주는 역할
		
		try {
		conn = DBConnection.getConnection();
		System.out.println("1/3 getAllBbs success");
		
		psmt = conn.prepareStatement(sql);
		System.out.println("2/3 getAllBbs success");
		
		rs = psmt.executeQuery();
		if(rs.next()) {
		len = rs.getInt(1);
		}
		System.out.println("3/3 getAllBbs success");
		
		} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		System.out.println("getAllBbs fail");
		} finally {
		DBClose.close(conn, psmt, rs);
		}
		
		return len;	// 글의 총수를 구함!
		}

	
	public boolean deleteBbs(int seq) {
		String sql = " UPDATE BBS "
				   + " SET DEL=1 "
				   + " WHERE SEQ=? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		int count = 0; 
		
		
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/3 deleteBbs success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			System.out.println("2/3 deleteBbs success");
			
			count = psmt.executeUpdate();
			System.out.println("3/3 deleteBbs success");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("deleteBbs fail");
		}
		return count>0?true:false;	
	}
}

	


