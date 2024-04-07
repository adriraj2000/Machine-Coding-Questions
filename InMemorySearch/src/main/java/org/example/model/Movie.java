package org.example.model;

import lombok.Data;

@Data
public class Movie extends Document{
    private String name;
    private double rating;
    private int year;
    public Movie(String name, double rating, int year){
        this.name = name;
        this.rating = rating;
        this.year = year;
    }
}
