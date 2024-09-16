package org.mehtor.utility;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.mehtor.utility.Constants.*;

public class DatabaseConnection {
	private static DatabaseConnection instance;
	public static final String dbName = DB_NAME;
	public static final String url = "jdbc:postgresql://" + DB_HOSTNAME + ":" + DB_PORT + "/" + DB_NAME;
	public static final String username = DB_USERNAME;
	public static final String password = DB_PASSWORD;
	private Connection connection;
	
	private DatabaseConnection() {
		try {
			this.connection = DriverManager.getConnection(url, username, password);
		}
		catch (SQLException e) {
			System.out.println("Veri tabanı bağlantisi olusturulamadi" + e.getMessage());
		}
	}
	
	public static synchronized DatabaseConnection getInstance() {
		if (instance == null) {
			instance = new DatabaseConnection();
		}
		return instance;
	}
	
	public Connection getConnection() {
		try {
			if (connection.isClosed()) {
				connection = ConnectionProvider.getInstance().getConn();
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

}