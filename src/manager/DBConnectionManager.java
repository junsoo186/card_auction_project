package manager;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DBConnectionManager {

	private static DBConnectionManager instance;
	private HikariDataSource dataSource;
	private String url = "jdbc:mysql://localhost:3306/db_card_auction?serverTimezone=Asia/Seoul";
	private String name = "root";
	private String password = "asd123";
	
	private DBConnectionManager() {
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl(url);
		config.setUsername(name);
		config.setPassword(password);
		config.setMaximumPoolSize(20);
		dataSource = new HikariDataSource(config);
	}
	
	public synchronized static DBConnectionManager getInstance() {
		if(instance == null) {
			instance = new DBConnectionManager();
		}
		return instance;
	}
	
	public Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}
	
}
