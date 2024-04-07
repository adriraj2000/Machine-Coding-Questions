package org.example.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Availability {
    private final LocalDate date;
    private final int startTime;
    private final int endTime;

    public Availability(LocalDate date, int startTime, int endTime) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
