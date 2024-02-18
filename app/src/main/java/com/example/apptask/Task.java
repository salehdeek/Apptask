package com.example.apptask;

import java.io.Serializable;

public class Task implements Serializable {
    private String id;
    private String description;
    private String dueDate;
    private boolean isDone;

    public Task(String id, String description, String dueDate, boolean isDone) {
        this.id = id;
        this.description = description;
        this.dueDate = dueDate;
        this.isDone = isDone;
    }

    // getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getDueDate() { return dueDate; }
    public void setDueDate(String dueDate) { this.dueDate = dueDate; }
    public boolean isDone() { return isDone; }
    public void setDone(boolean done) { isDone = done; }

    @Override
    public String toString() {
        return description + " - Due: " + dueDate + (isDone ? " (Done)" : " (Due)");
    }
}
