package org.example.models;

import lombok.Data;

@Data
public class Statistics {
    private int tasksAdded;
    private int tasksCompleted;
    private int tasksSpilledOver;

    public Statistics(int tasksAdded, int tasksCompleted, int tasksSpilledOver) {
        this.tasksAdded = tasksAdded;
        this.tasksCompleted = tasksCompleted;
        this.tasksSpilledOver = tasksSpilledOver;
    }
}
