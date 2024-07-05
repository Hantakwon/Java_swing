 package DB;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class DB {
	public static final String databaseDriver = "com.mysql.cj.jdbc.Driver";
	public static final String databaseUrl =  "jdbc:mysql://localhost:3306/village";
	public static final String databaseUser = "root";
	public static final String databasePassword = "1234";
	
	public static Connection conn = null;
	public static PreparedStatement pstmt = null;
	public static ResultSet rs = null;	
	public static String sql = null;
	
	public static Connection connect() {
		try {
			Class.forName(databaseDriver);
			conn = DriverManager.getConnection(databaseUrl, databaseUser, databasePassword);
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "데이터베이스가 연결되지 않았습니다","경고!!",JOptionPane.WARNING_MESSAGE);
			System.err.println("Connection Error! : " + e.getMessage());
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void close() {
		try {
			if (conn != null) {
				conn.close();
			}
		}
		catch (SQLException e) {
			System.err.println("Connection Closing Failed! : " + e.getMessage());
			e.printStackTrace();
		}
	}
}

