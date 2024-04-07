package org.example.service;

import org.example.model.Gender;
import org.example.model.User;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserService {
    private Map<String, User> userMap;
    public UserService(){
        userMap = new HashMap<>();
    }

    public void addUser(String userName, Gender gender, int age){
        User user = new User(UUID.randomUUID().toString(),userName, gender, age);
        userMap.put(user.getId(), user);
    }

    public User getUser(String userId){
        return userMap.get(userId);
    }
}
