package com.example.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.entity.User;

import com.example.config.DatabaseConfig;

public class UserModel {
	
	public List<User> listUsers(){
		
		List<User> listUsers = new ArrayList<>();
		
		// connection object intilization
		Connection connection = DatabaseConfig.getConnection();
		
		// Statement interface cannot accept parameters and useful when you are using static SQL statements at runtime, If you want to run SQL query only once then this interface
		Statement stmt = null;		
		ResultSet rs = null;
		
		// create db query
		String query = "select * from users";
		
		try {
			// create the statement
			stmt = connection.createStatement();
			
			// execution the statement
			rs = stmt.executeQuery(query);
			
			// execute statment
			while(rs.next()) {
				listUsers.add(new User(rs.getInt("user_id"),rs.getString("username"),rs.getString("email")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listUsers;		
	}

	public void addUser(User newUser) {
		Connection connection = null;
		PreparedStatement stmt = null;
		try {
			connection = DatabaseConfig.getConnection();
			String userName = newUser.getUsername();
			String email = newUser.getEmail();
			
			String query = "insert into users (username, email) values (?, ?)";
			
			stmt = connection.prepareStatement(query);
			stmt.setString(1, userName);
			stmt.setString(2, email);
			
			//Returns a ResultSet object. Use this method when you expect to get a result set, it is only used with SELECT statement.
			stmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void updateUser(User updatedUser) {
		Connection connection = null;
		
		//It is used when you want to use SQL statements many times. The PreparedStatement interface accepts input parameters at runtime. 
		PreparedStatement stmt = null;
		
		try {
			connection = DatabaseConfig.getConnection();
			int UserId = updatedUser.getUser_id();
			String username = updatedUser.getUsername();
			String email = updatedUser.getEmail();
			
			//(?) are placeholders. These placeholders are used in prepared statements in Java to represent values that will be set later using specific setter methods (setString, setInt, etc.) on the PreparedStatement object.
			String query = "update users set username = ?, email = ? where user_id = ?";
			
			stmt = connection.prepareStatement(query);
			stmt.setString(1, username);
			stmt.setString(2, email);
			stmt.setInt(3, UserId);
			
			//Returns a boolean value of true if a ResultSet object can be retrieved; otherwise, it returns false. Use this method to execute SQL DDL statements or when you need to use truly dynamic SQL (This method can run both select and insert/update statements).
			stmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteUser(int userID) {
		Connection connection = null;
		PreparedStatement stmt = null;
		try {
			connection = DatabaseConfig.getConnection();
			String query = "delete from users where user_id = ?";
			stmt = connection.prepareStatement(query);
			stmt.setInt(1, userID);
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}



