package org.example.model;
import lombok.Data;
import java.time.LocalDate;

@Data
public class BookingRequest {
    private int noOfPeople;
    private LocalDate bookingDate;
    private int startTime;
    private int endTime;

    public BookingRequest(int noOfPeople, LocalDate bookingDate, int startTime, int endTime) {
        this.noOfPeople = noOfPeople;
        this.bookingDate = bookingDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
