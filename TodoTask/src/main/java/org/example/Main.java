package org.example;

import org.example.impl.TodoTracker;
import org.example.models.Statistics;
import org.example.models.Task;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        TodoTracker todoTracker = new TodoTracker();

        // Add some tasks
        Task task1 = new Task("Complete Java project", new Date(), new HashSet<>(Arrays.asList("Java", "Project")));
        Task task2 = new Task("Buy groceries", new Date(), new HashSet<>(Arrays.asList("Shopping")));
        Task task3 = new Task("Read book", new Date(), new HashSet<>(Arrays.asList("Reading")));

        todoTracker.addTask(task1);
        todoTracker.addTask(task2);
        todoTracker.addTask(task3);

        // Modify a task
        Task updatedTask = new Task("Updated task description", new Date(), new HashSet<>(Arrays.asList("Updated")));
        updatedTask.setId(task1.getId());
        todoTracker.modifyTask(updatedTask);

        // Remove a task
        todoTracker.removeTask(task2.getId());

        // Get a task
        Task retrievedTask = todoTracker.getTask(task3.getId());
        if (retrievedTask != null) {
            System.out.println("Retrieved Task: " + retrievedTask.getDescription());
        }

        // List tasks
        List<Task> taskList = todoTracker.listTasks("Shopping",Comparator.comparing(Task::getDeadline));
        System.out.println("Listing tasks based on Shopping tag");
        for(Task task : taskList){
            System.out.println(task);
        }

        // Statistics
        Statistics statistics = todoTracker.getStatistics(Optional.empty());
        System.out.println(statistics);

        // Get activity log
        List<String> activityLog = todoTracker.getActivityLog(Optional.empty());
        System.out.println("Activity Log:");
        for (String entry : activityLog) {
            System.out.println(entry);
        }
    }
}