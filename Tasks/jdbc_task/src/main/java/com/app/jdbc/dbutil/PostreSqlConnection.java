package com.app.jdbc.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostreSqlConnection {
	
	//create static connection
	private static Connection connection;
	
	
	//private, empty constructor so the class can't be instantiated
	//takes over from default constructor
	private PostreSqlConnection() {
		super();
		
	}

	//create method for calling connection, which includes all connectivity info
	//add throws. Whoever instantiates the code will handle the exceptions.
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("org.postgresql.Driver");
		
		//Info needed to connect
		String url = "jdbc:postgresql://localhost:5432/postgres";
		String username = "postgres";
		String password = "postgres";
		
		//establish connection with getConnection
		connection = DriverManager.getConnection(url, username, password);
		
		//return the connection to whomever has called this method: getConnection()
		return connection;
	}

}
//Single Ton
//	Design Pattern
//	Single Ton Java Class
//	There is only one instance of this in the entire project
//    Static connection
//    Don’t instantiate a static method.