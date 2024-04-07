package org.example.entities;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class Question {
    private String questionId;
    private String questionName;
    private List<Option> options;
    private int totalScore;
    private int totalPeopleAnswered;
    public Question(String questionName, List<Option> options){
        questionId = UUID.randomUUID().toString();
        this.questionName = questionName;
        this.options = options;
        totalScore = 0;
        totalPeopleAnswered = 0;
    }
}
