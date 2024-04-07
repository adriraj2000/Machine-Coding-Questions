package org.example.service;

import org.example.exception.UserManagementException;
import org.example.model.User;
import org.example.validator.UserValidator;

import java.util.HashMap;
import java.util.Map;

public class UserManagement{
    private Map<String, User> users;

    public UserManagement() {
        this.users = new HashMap<>();
    }

    public void registerUser(String userId, String password, String name) throws UserManagementException {
        UserValidator.validateUser(userId, password, name);
        if (!users.containsKey(userId)) {
            User newUser = new User(userId, password, name, false);
            users.put(userId, newUser);
            System.out.println("User registered successfully");
        } else {
            throw new UserManagementException("User already exists");
        }
    }

    public User loginUser(String userId, String password) throws UserManagementException {
        if (users.containsKey(userId)) {
            User user = users.get(userId);
            if (user.getPassword().equals(password)) {
                System.out.println("Login successful");
                return user;
            } else {
                throw new UserManagementException("Invalid password");
            }
        } else {
            throw new UserManagementException("User not found");
        }
    }
}
