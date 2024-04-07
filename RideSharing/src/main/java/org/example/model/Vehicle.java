package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Vehicle {
    private String owner;
    private String name;
    private String vehicleNumber;
    private int availableSeats;
}
