package org.example.models;

import lombok.Data;

import java.util.Date;

@Data
public class TimePeriod {
    private Date startDate;
    private Date endDate;

    public TimePeriod(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }
}