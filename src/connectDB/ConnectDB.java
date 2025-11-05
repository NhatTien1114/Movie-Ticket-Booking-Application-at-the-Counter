package connectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
	private static Connection conn = null;

    public static Connection getConnection() {
    	try {
    		
            if (conn == null || conn.isClosed()) {
            	String url = "jdbc:sqlserver://localhost\\SQLEXPRESS:1433;"
                        + "databaseName=QLBanVe;"
                        + "encrypt=false;"
                        + "trustServerCertificate=true;";
                String user = "sa";
                String pass = "123";
                conn = DriverManager.getConnection(url, user, pass);
                System.out.println("--- Kết nối SQL Server thành công!");
            }
        } catch (SQLException e) {
                e.printStackTrace();
        }
        return conn;
    }

    public static void closeConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("--- Đã đóng kết nối!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
