package org.example.service;

import org.example.model.User;
import org.example.model.UserType;

import java.util.HashMap;
import java.util.Map;

public class UserService {
    private Map<String, User> userMap;
    public UserService(){
        userMap = new HashMap<>();
    }

    public void createUser(String userName, UserType userType){
        User user = new User(userName, userType);
        userMap.put(userName, user);
    }

    public User getUser(String userName){
        return userMap.get(userName);
    }
}
