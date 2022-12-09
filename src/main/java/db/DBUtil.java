package db;

import java.sql.*;

public class DBUtil {
	// 드라이버 로딩 연결
	public Connection getConnection() throws Exception {
		
		Class.forName("org.mariadb.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/db58", "root", "wkqk1234");
		
		return conn;
	}
	// 자원 반납
	public void close(ResultSet rs, PreparedStatement stmt, Connection conn) throws Exception {
		if(rs != null) {
			rs.close();
		}
		if(stmt != null) {
			stmt.close();
		}
		if(conn != null) {
			conn.close();
		}
	}
}
