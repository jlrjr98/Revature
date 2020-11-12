package com.app.bank.dao.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BankConnection {
	
	private static Connection connection;

	private BankConnection() {
		super();
	}
	
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName(DbUtilProps.DRIVER);
		String url=DbUtilProps.URL;			
		String username=System.getenv("postgresUserName");
		String password=System.getenv("postgresPassword");
		connection=DriverManager.getConnection(url, username, password);
		return connection;
		
	}
	
	

}
