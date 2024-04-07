package org.example.service;

import org.example.entities.Option;
import org.example.entities.Question;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestionService {

    private Map<String, Question> questionMap;
    public QuestionService(){
        questionMap = new HashMap<>();
    }
    public Question createQuestion(String questionName, List<Option> options){
        Question question = new Question(questionName, options);
        questionMap.put(question.getQuestionId(), question);
        return question;
    }

    public Question getQuestion(String questionId){
        return questionMap.get(questionId);
    }
}
