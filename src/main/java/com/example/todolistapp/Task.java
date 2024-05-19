package com.example.todolistapp;

import java.sql.Date;

public class Task {
    private int id;
    private String name;

    private Date dueDate;
    private boolean completed;

    public Task(int id, String name, Date dueDate, boolean completed) {
        this.id = id;
        this.name = name;
        this.dueDate = dueDate;
        this.completed = completed;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public boolean isCompleted() {
        return completed;
    }

}
