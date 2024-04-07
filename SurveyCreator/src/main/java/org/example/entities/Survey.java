package org.example.entities;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class Survey {
    private String surveyId;
    private List<Question> questionList;
    public Survey(){
        surveyId = UUID.randomUUID().toString();
        questionList = new ArrayList<>();
    }
}
