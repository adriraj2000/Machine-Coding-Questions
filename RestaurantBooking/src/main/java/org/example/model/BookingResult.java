package org.example.model;

import lombok.Data;

@Data
public class BookingResult {
    private boolean success;
    private String message;

    public BookingResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
