package com.example.todolistapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TodoListDAO {
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/Tasks";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "opuruiche";
	private Connection connection;
	private ImplTodoList todoList;

	public TodoListDAO() throws SQLException {
		connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
		todoList = new ImplTodoList(connection);
	}

	public ImplTodoList getTodoList() {
		return todoList;
	}

	public void close() throws SQLException {
		if (connection != null && !connection.isClosed()) {
			connection.close();
		}
	}
} 
