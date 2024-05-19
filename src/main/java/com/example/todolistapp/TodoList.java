package com.example.todolistapp;

import java.sql.SQLException;

public interface TodoList {
    void createTask(Task task) throws SQLException;
    Task readTask(int id) throws SQLException;

    void updateTask(Task task) throws SQLException;

    void deleteTask(int id) throws SQLException;
}
