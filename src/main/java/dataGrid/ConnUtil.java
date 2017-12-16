package dataGrid;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * 工具类：连接数据库+字符转换
 * @author wsz
 * @date 2017年12月16日
 */
public class ConnUtil {

	private static String DRIVER = "com.mysql.jdbc.Driver";
	
	private static String URL = "jdbc:mysql://localhost:3306/mybatis?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
	
	private static String USR ="wsz";
	
	private static String PASSWORD ="wsz";
	
	public static Connection getConnection() {
		try {
			Class.forName(DRIVER);
			try {
				Connection connection = DriverManager.getConnection(URL, USR, PASSWORD);
				return connection;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			System.out.println("类加载失败");
			e.printStackTrace();
		}
		return null;
	}
	
	public static void clearSelectConn(Connection conn,PreparedStatement pst, ResultSet rs) {
		try {
			if(rs != null) rs.close();
			if(pst != null) pst.close();
			if(conn != null) conn.close();
		}catch(SQLException  e) {
			e.printStackTrace();
		}
	}
	
	public static void clearUpdateConn(Connection conn, PreparedStatement pst) {
		try {
			if(conn != null )conn.close();
			if(pst != null) pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static String nullToEmpty(Object obj) {
		return obj == null ? "" : String.valueOf(obj);
	}
}
