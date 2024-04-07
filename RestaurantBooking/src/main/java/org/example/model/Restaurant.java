package org.example.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Restaurant {
    private int id;
    private String name;
    private String city;
    private String area;
    private String cuisine;
    private int costForTwo;
    private boolean isVeg;
    private List<Availability> availability;

    public Restaurant(int id, String name, String city, String area, String cuisine, int costForTwo, boolean isVeg) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.area = area;
        this.cuisine = cuisine;
        this.costForTwo = costForTwo;
        this.isVeg = isVeg;
        this.availability = new ArrayList<>();
    }

    public void removeAvailability(Availability a){
        availability.remove(a);
    }
}
