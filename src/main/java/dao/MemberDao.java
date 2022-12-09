package dao;

import vo.*;

import java.sql.*;

public class MemberDao {
	// 아이디 중복검사
	// param String : 사용할 아이디
	// return boolean value : true(member와 outid테이블에 둘다 없다=사용가능한 아이디) / false(사용불가 아이디)
	public Boolean checkMemberId(Connection conn, String memberId) throws Exception {
		boolean result = true;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		
		String sql = "SELECT t.member_id"
				+ " FROM (SELECT member_id FROM member UNION SELECT member_id FROM outid) t"
				+ " WHERE t.member_id = ?";
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, memberId);
		rs = stmt.executeQuery();
		if(rs.next()) { // rs값이 있다면 false 반환 사용할수 없는 아이디가 된다.
			result = false;
		}
	
		return result;
	}	
	
	// 회원가입
	public int insertMember(Connection conn, Member member) throws Exception {
		int row = 0;	
		PreparedStatement stmt = null;
	
		// 쿼리문 작성
		String sql = "INSERT INTO member (member_id, member_pw, member_name) values(?, PASSWORD(?), ?)";
		// 쿼리 객체 생성
		stmt = conn.prepareStatement(sql);
		// 쿼리문 ?값 지정
		stmt.setString(1, member.getMemberId());
		stmt.setString(2, member.getMemberPw());
		stmt.setString(3, member.getMemberName());
		// 쿼리 실행
		row = stmt.executeUpdate();
		stmt.close();
	
		return row;
	}
	// 로그인
	public Member login(Connection conn, Member paramMember) throws Exception {
		Member resultMember = null;		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		// 쿼리문 작성
		String sql = "SELECT member_id memberId, member_name memberName FROM member WHERE member_id = ? AND member_pw = PASSWORD(?)";
		// 쿼리 객체 생성
		stmt = conn.prepareStatement(sql);
		// 쿼리문 ?값 지정
		stmt.setString(1, paramMember.getMemberId());
		stmt.setString(2, paramMember.getMemberPw());
		// 쿼리 실행
		rs = stmt.executeQuery();
		if(rs.next()) {
			resultMember = new Member();
			resultMember.setMemberId(rs.getString("memberId"));
			resultMember.setMemberName(rs.getString("memberName"));
		}
		
		// 자원 반납
		stmt.close();
		rs.close();
		return resultMember; // 로그인 실패시 null, 성공하면 Member객체
	}
	// 회원탈퇴
	public int deleteMember(Connection conn, String memberId) throws Exception {
		// int outIdRow = 0;
		int row = 0;	
		// PreparedStatement outIdStmt = null;
		PreparedStatement stmt = null;
	
		// 자동커밋 off -> conn.setAutoCommit(false);
		// 쿼리문 작성
		/* 1)
		String outSql = "INSERT INTO outid(member_id, createdate) values(?, NOW())";
		outIdStmt = conn.prepareStatement(outSql);
		outIdStmt.setString(1, memberId);
		outIdRow = outIdStmt.executeUpdate(); // conn.setAutoCommit(false) -> 자동커밋X
		*/
		// 2)   
		String sql = "DELETE FROM member WHERE member_id = ?";
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, memberId);
		row = stmt.executeUpdate(); // conn.setAutoCommit(false) -> 자동커밋X
		// conn.commit(); // 1)+2)를 일괄처리 -> 트랜잭션			
		// 자원반납
		stmt.close();
		
		return row;
	}
}
