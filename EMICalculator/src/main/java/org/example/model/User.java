package org.example.model;

import lombok.Data;

@Data
public class User {
    private String username;
    private UserType userType;

    public User(String username, UserType userType) {
        this.username = username;
        this.userType = userType;
    }
}
