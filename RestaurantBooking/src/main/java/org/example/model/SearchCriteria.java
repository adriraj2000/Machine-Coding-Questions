package org.example.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
// SearchCriteria class with builder pattern
public class SearchCriteria {
    private String city;
    private String area;
    private String cuisine;
    private int costForTwo;
    private boolean isVeg;

}