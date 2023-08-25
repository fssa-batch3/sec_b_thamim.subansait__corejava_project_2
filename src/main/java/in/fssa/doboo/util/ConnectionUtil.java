package in.fssa.doboo.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionUtil {

	public static Connection getConnection() {
		
		String url;
        String userName;
        String passWord;

            url = System.getenv("DATABASE_HOSTNAME");
            userName = System.getenv("DATABASE_USERNAME");
            passWord = System.getenv("DATABASE_PASSWORD");
        
        	            
//        	 url = "jdbc:mysql://164.52.216.41:3306/thamim_subansait_corejava_project";
//        	 userName = "gfMkJwcB811m";
//        	 passWord = "a5e984ca-8fc3-4620-b0db-1a95b679a08b";
        
		Connection connection = null;
		

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			 connection = DriverManager.getConnection(url,userName,passWord);

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		return connection;
	}
	

	public static void close(Connection connection, PreparedStatement ps) {
		try {
			if(ps != null) {
				ps.close();
			}
			if(connection != null) {
				connection.close();
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void close(Connection connection, PreparedStatement ps,ResultSet res) {
		try {
			if(res!=null) {
				res.close();
			}
			if(ps != null) {
				ps.close();
			}
			if(connection != null) {
				connection.close();
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

