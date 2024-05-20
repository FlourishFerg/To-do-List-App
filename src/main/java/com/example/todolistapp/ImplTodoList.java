package com.example.todolistapp;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static javafx.geometry.Pos.*;

public class ImplTodoList implements TodoList {
    private Connection connection;

    // 11
    public ImplTodoList(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void createTask(Task task) throws SQLException {
        String sql = "INSERT INTO tasks (name, duedate, completed) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, task.getName());
            statement.setDate(2, new java.sql.Date(task.getDueDate().getTime()));
            statement.setBoolean(3, task.isCompleted());


            statement.executeUpdate();
        }
    }

    @Override
    public Task readTask(int id) throws SQLException {
        String sql = "SELECT * FROM tasks WHERE id = ?";
        Task task = null;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                task = new Task(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getDate("duedate"),
                        resultSet.getBoolean("completed")
                );
            }
        }
        return task;
    }

    @Override
    public void updateTask(Task task) throws SQLException {
        String sql = "UPDATE tasks SET name = ?, duedate = ?, completed = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, task.getName());
            statement.setDate(2, new java.sql.Date(task.getDueDate().getTime()));
            statement.setBoolean(3, task.isCompleted());
            statement.setInt(4, task.getId());
            statement.executeUpdate();
        }
    }

    @Override
    public void deleteTask(int id) throws SQLException {
        String sql = "DELETE FROM tasks WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    public List<Task> getAllTasks() throws SQLException {
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT * FROM tasks";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Task task = new Task(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getDate("duedate"),
                        resultSet.getBoolean("completed")
                );
                tasks.add(task);
            }
        }
        return tasks;
    }



}
