package com.example.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// This class is can be used  to initilize the database connnection
public class DatabaseConfig {
	
	public static Connection getConnection(){
		
		// initilize the all database info 
		String dbURL = "jdbc:mysql://localhost:3306/studyeasy";
		String dbUsername = "root";
		String dbPassword = "mysql1234";
		String driver = "com.mysql.cj.jdbc.Driver";
		
		Connection con= null;
		try {
			// load or register the driver
			Class.forName(driver);
			
			
			// create or establish the connection
			con = DriverManager.getConnection(dbURL,dbUsername,dbPassword);
			
			 System.out.println("Connection Established");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Con : "+con);
		return con;
	}
}