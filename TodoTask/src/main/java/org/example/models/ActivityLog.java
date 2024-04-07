package org.example.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class ActivityLog {
    private List<String> log;

    public ActivityLog() {
        log = new ArrayList<>();
    }

    public void addToLog(String message) {
        log.add(message);
    }

    public List<String> getActivityLog(Optional<TimePeriod> timePeriod) {
        if (timePeriod.isPresent()) {
            Date startDate = timePeriod.get().getStartDate();
            Date endDate = timePeriod.get().getEndDate();
            return log.stream()
                    .filter(entry -> {
                        String[] parts = entry.split(":");
                        String dateStr = parts[0];
                        try {
                            Date entryDate = new Date(Long.parseLong(dateStr));
                            return entryDate.after(startDate) && entryDate.before(endDate);
                        } catch (NumberFormatException e) {
                            return false;
                        }
                    })
                    .collect(Collectors.toList());
        } else {
            return log;
        }
    }
}