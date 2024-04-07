package org.example.entities;

import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Data
public class User extends SurveyUser{
    private String userId;
    private Map<String, Option> userAnsweredQuestions; // question, options
    public User(String userId){
        this.userId = userId;
        userAnsweredQuestions = new HashMap<>();
    }
}
