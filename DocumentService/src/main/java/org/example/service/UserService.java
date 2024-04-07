package org.example.service;

import org.example.model.User;

import java.util.HashMap;
import java.util.Map;

public class UserService {
    private Map<String, User> userMap;
    public UserService(){
        userMap = new HashMap<>();
    }

    public void addUser(String userName){
        userMap.put(userName, new User(userName));
    }

    public User getUser(String userName){
        return userMap.get(userName);
    }
}
