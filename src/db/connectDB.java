package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connectDB {
public class ConnectDB {

	}
	//	VAR BLOCK
	private Connection connection;
	private static connectDB instance;
	
// 	HOOK DB		(KetNoiDB)
	private connectDB() {
		try {
			String url = "jdbc:sqlserver://localhost:1433;databaseName=CuaHangBanSach; trustServerCertificate=true";
			connection = DriverManager.getConnection(url , "sa", "123456789");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

// 	CONNECTION	(LuongKetNoi)
	public Connection getConnection() {
		return connection;
	}
	
	
//	INSTANCE	(PhienLamViec)
	public synchronized static connectDB getInstance() {
		if(instance == null)
			instance = new connectDB();
		return instance;
	}

//	CLOSING		(NgatKetNoi)
	public void close() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		Connection connection = connectDB.getInstance().getConnection();
		if (connection != null) {
			System.out.println("Thanh cong");
		} else {
			System.out.println("Failed");
		}
	}
}
