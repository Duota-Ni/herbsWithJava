package dbtool;
import java.sql.*;
public class herbDB {
	static {
		try {
			String driver = "com.mysql.cj.jdbc.Driver";
			//加载jdbc驱动器
			Class.forName(driver);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	public static Connection getConnection() {
		try {
			String url ="jdbc:mysql://127.0.0.1:3306/plant?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
			   String username = "root";
			   String password = "6182";
			   Connection conn = DriverManager.getConnection(url,username,password);
			   System.out.println("连接数据库成功");
			   return conn;
		}catch (SQLException ex) {
			System.out.println("连接数据库失败");
			ex.printStackTrace();
			return null;
		}
		
	}
	public static void close(Connection conn,Statement stm,ResultSet rs) {
		try {
			if (rs!=null) {
				rs.close();
			}
			if (stm!=null) {
				stm.close();
			}
			if (conn!=null) {
				conn.close();
				System.out.println("数据库连接成功释放");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
	}

}
