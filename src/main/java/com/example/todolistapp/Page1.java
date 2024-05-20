package com.example.todolistapp;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.sql.SQLException;
import java.sql.Date;

    public class Page1 {
        @FXML
        private TextField taskInput;
        @FXML
        private Button addButton;
        @FXML
        private VBox taskList;

        private ImplTodoList todoList;

        @FXML
        public void initialize() {
            try {
                TodoListDAO dao = new TodoListDAO();
                todoList = dao.getTodoList();
                loadTasks();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            addButton.setOnAction(e -> addTask());
        }

        private void loadTasks() {
            taskList.getChildren().clear();
            try {
                for (Task task : todoList.getAllTasks()) { // Ensure ImplTodoList has getAllTasks() method
                    addTaskToUI(task);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        private void addTask() {
            String taskName = taskInput.getText();gege
            if (!taskName.isEmpty()) {
                Task newTask = new Task(0, taskName, new java.sql.Date(System.currentTimeMillis()), false);
                try {
                    todoList.createTask(newTask);
                    loadTasks();
                    taskInput.clear();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        private void addTaskToUI(Task task) {
            TextField taskField = new TextField(task.getName());
            taskField.setEditable(false);

            Button editButton = new Button("Edit");
            Button deleteButton = new Button("Delete");

            HBox taskBox = new HBox(10, taskField, editButton, deleteButton);
            taskList.getChildren().add(taskBox);

            editButton.setOnAction(e -> editTask(task, taskField));
            deleteButton.setOnAction(e -> deleteTask(task));
        }

        private void editTask(Task task, TextField taskField) {
            if (taskField.isEditable()) {
                try {
                    task = new Task(task.getId(), taskField.getText(), task.getDueDate(), task.isCompleted());
                    todoList.updateTask(task);
                    taskField.setEditable(false);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                taskField.setEditable(true);
            }
        }

        private void deleteTask(Task task) {
            try {
                todoList.deleteTask(task.getId());
                loadTasks();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
