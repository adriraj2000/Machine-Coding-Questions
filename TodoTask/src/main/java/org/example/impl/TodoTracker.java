package org.example.impl;

import org.example.models.ActivityLog;
import org.example.models.Statistics;
import org.example.models.Task;
import org.example.models.TimePeriod;
import org.example.tracker.TaskTracker;

import java.time.LocalDateTime;
import java.util.*;

public class TodoTracker implements TaskTracker {
    private final Map<Integer,Task> tasks;
    private ActivityLog activityLog;

    public TodoTracker() {
        this.tasks = new HashMap<>();
        this.activityLog = new ActivityLog();
    }

    public void addTask(Task task) {
        tasks.putIfAbsent(task.getId(),task);
        activityLog.addToLog(LocalDateTime.now() + ": Task added: " + task.getDescription());
    }

    public void modifyTask(Task updatedTask) {
        Task existingTask = getTask(updatedTask.getId());
        if (existingTask != null) {
            tasks.put(updatedTask.getId(), updatedTask);
            activityLog.addToLog(LocalDateTime.now() + ": Task modified: " + existingTask.getDescription());
        } else {
            System.out.println("Task not found.");
        }
    }

    public void removeTask(Integer taskId) {
        Task taskToRemove = getTask(taskId);
        if (taskToRemove != null) {
            tasks.remove(taskToRemove);
            activityLog.addToLog(LocalDateTime.now() + ": Task removed: " + taskToRemove.getDescription());
        } else {
            System.out.println("Task not found.");
        }
    }

    public Task getTask(Integer taskId) {
        Task task = tasks.get(taskId);
        if(task != null){
            activityLog.addToLog(LocalDateTime.now() + ": Task fetched: " + task.getDescription());
            return task;
        }
        else{
            System.out.println("Task not found.");
            return null;
        }
    }

    public List<String> getActivityLog(Optional<TimePeriod> optionalTimePeriod) {
        return activityLog.getActivityLog(optionalTimePeriod);
    }

    public List<Task> listTasks(String filterCriteria, Comparator<Task> comparing) {
        List<Task> filteredTasks = new ArrayList<>();
        for(Task task : tasks.values()){
            if(task.getTags().contains(filterCriteria))filteredTasks.add(task);
        }
        filteredTasks.sort(comparing);
        return filteredTasks;
    }

    public Statistics getStatistics(Optional<TimePeriod> timePeriod) {
        int tasksAdded = 0;
        int tasksCompleted = 0;
        int tasksSpilledOver = 0;

        if (timePeriod.isPresent()) {
            Date startDate = timePeriod.get().getStartDate();
            Date endDate = timePeriod.get().getEndDate();

            for (Task task : tasks.values()) {
                if (task.getDeadline() != null && task.getDeadline().after(endDate)) {
                    tasksSpilledOver++;
                }

                if (task.getDeadline() != null && task.getDeadline().after(startDate) && task.getDeadline().before(endDate)) {
                    tasksAdded++;
                }

                if (task.isCompleted() && task.getDeadline() != null && task.getDeadline().before(endDate)) {
                    tasksCompleted++;
                }
            }
        }
        else {
            for (Task task : tasks.values()) {
                if (task.getDeadline() != null && task.getDeadline().after(new Date())) {
                    tasksSpilledOver++;
                }

                if (task.isCompleted()) {
                    tasksCompleted++;
                }
                tasksAdded++;
            }
        }
        return new Statistics(tasksAdded, tasksCompleted, tasksSpilledOver);
    }
}
