package org.mehtor.utility;

import java.sql.*;
import java.util.Optional;

import static org.mehtor.utility.Constants.*;

public class ConnectionProvider implements AutoCloseable {
	private Connection conn;
	private static ConnectionProvider instance;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	
	public static synchronized ConnectionProvider getInstance() {
		
		if (instance == null) {
			instance = new ConnectionProvider();
		}
		return instance;
	}
	
	@Override
	public void close() {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		catch (SQLException e) {
			System.out.println("Veritabani baglantisi kapatilirken hata oldu..."+e.getMessage());
		}
	}
	
	public PreparedStatement getPreparedStatement(String sql) throws SQLException {
		conn = getConn();
		return conn.prepareStatement(sql);
	}
	
	public Connection getConn() {
		try {
			if (conn == null|| conn.isClosed()) {
				this.conn = DatabaseConnection.getInstance().getConnection();
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
}