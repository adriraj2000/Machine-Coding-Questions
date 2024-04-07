package org.example.models;

import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
public class Task {
    private static int counter = 1;
    private int id;
    private String description;
    private Date deadline;
    private Set<String> tags;
    private boolean completed;

    public Task(String description, Date deadline, Set<String> tags) {
        this.id = counter++;
        this.description = description;
        this.deadline = deadline;
        this.tags = tags;
        this.completed = false;
    }
}
