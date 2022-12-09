package dao;

import java.sql.*;

public class OutidDao {
	// 탈퇴아이디 입력
	public int insertMemberId(Connection conn, String memberId) throws Exception {
		int row = 0;
		PreparedStatement stmt = null;
		
		// 쿼리문 작성
		String sql = "INSERT INTO outid(member_id, createdate) values(?, NOW())";
		// 쿼리 객체 생성
		stmt = conn.prepareStatement(sql);
		// 쿼리문 ?값 지정
		stmt.setString(1, memberId);
		// 쿼리 실행
		row = stmt.executeUpdate();
		// 자원 반납
		stmt.close();
		return row;
	}	
}
