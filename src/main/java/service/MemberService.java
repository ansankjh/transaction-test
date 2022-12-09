package service;

import vo.*;
import dao.*;
import java.sql.*;

// 로직, db접근/해지
public class MemberService {
	private OutidDao outidDao;
	private MemberDao memberDao;
	
	// id 중복검사
	public Boolean checkMemberId(String memberId) {
		boolean result = true;
		Connection conn = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/db58", "root", "wkqk1234");
			this.memberDao = new MemberDao();
			result = memberDao.checkMemberId(conn, memberId);
		} catch(Exception e) {						
			e.printStackTrace();
		} finally {			
			if(conn != null) {
				try {
					conn.close();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}		
		return result;
	}
	
	// 회원가입
	public int insertMember(Member member) {
		int row = 0;
		Connection conn = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/db58", "root", "wkqk1234");
			conn.setAutoCommit(false); // 자동커밋(execute()) 해지
			this.memberDao = new MemberDao();
			row = memberDao.insertMember(conn, member);
			conn.commit(); // 쿼리 커밋
		} catch(Exception e) {						
			e.printStackTrace();
		} finally {			
			if(conn != null) {
				try {
					conn.close();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}		
		return row;
	}
	
	// 로그인
	public Member login(Member paramMember) {
		Member resultMember = null;
		Connection conn = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/db58", "root", "wkqk1234");
			this.memberDao = new MemberDao();
			resultMember  = memberDao.login(conn, paramMember);
		} catch(Exception e) {						
			e.printStackTrace();
		} finally {			
			if(conn != null) {
				try {
					conn.close();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}				
		return resultMember;
	}
	// 회원탈퇴
	public void deleteMember(String memberId) {		
		Connection conn = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/db58", "root", "wkqk1234");
			conn.setAutoCommit(false); // 자동커밋(execute()) 해지
			
			this.outidDao = new OutidDao();
			if(this.outidDao.insertMemberId(conn, memberId) == 1) {
				this.memberDao = new MemberDao();
				if(this.memberDao.deleteMember(conn, memberId) != 1) {
					throw new Exception(); // 삭제가 안되면 강제로 예외를 발생시켜 catch절로 이동하여 롤백되도록...
				}
			}
			
			conn.commit(); // 쿼리 커밋
		} catch(Exception e) {
			try {
				conn.rollback();
			} catch(Exception e1) {
				e1.printStackTrace();
			}
						
			e.printStackTrace();
		} finally {			
			if(conn != null) {
				try {
					conn.close();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}	
		// 탈퇴성공 : 1 / 탈퇴실패 : 0
	}
}
