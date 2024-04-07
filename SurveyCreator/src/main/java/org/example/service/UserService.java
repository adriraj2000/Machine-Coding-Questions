package org.example.service;

import org.example.entities.*;

import java.util.HashMap;
import java.util.Map;

public class UserService {
    private Map<String, SurveyUser> userMap;
    public UserService(){
        userMap = new HashMap<>();
    }
    public SurveyUser createUser(String userName, String userType){
        if(userType == "Admin"){
            Admin admin = new Admin(userName);
            userMap.put(userName, admin);
            return admin;
        }
        else if(userType == "User"){
            User user = new User(userName);
            userMap.put(userName, user);
            return user;
        }
        else return null;
    }

    public void editUserAnsweredQuestions(String userName,String questionId,Option option){
        User user = (User) userMap.get(userName);
        Map<String,Option> userAnsweredQuestion = user.getUserAnsweredQuestions();
        userAnsweredQuestion.put(questionId, option);
        user.setUserAnsweredQuestions(userAnsweredQuestion);
        userMap.put(userName, user);
    }

    public SurveyUser getUser(String userName){
        return userMap.get(userName);
    }
}
