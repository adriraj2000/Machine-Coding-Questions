package org.example;

import org.example.entities.Option;
import org.example.entities.Question;
import org.example.service.QuestionService;
import org.example.service.SurveyService;
import org.example.service.UserService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserService();
        QuestionService questionService = new QuestionService();
        SurveyService surveyService = SurveyService.getInstance(questionService, userService);
        userService.createUser("Razor","Admin");
        Option option1 = new Option("Very Poor", 1);
        Option option2 = new Option("Poor", 2);
        Option option3 = new Option("Average", 3);
        Option option4 = new Option("Good", 4);
        Question question = questionService.createQuestion("How well did you do this round?", List.of(option1,option2,option3,option4));
        Question question2 = questionService.createQuestion("Sample ques", List.of(option1,option2,option3,option4));
        surveyService.createSurvey("Razor",List.of(question));
        userService.createUser("User1","User");
        surveyService.createSurvey("User1",List.of(question2));
        userService.createUser("User2","User");
        surveyService.answer("User1",question.getQuestionId(),option2);
        surveyService.answer("User2",question.getQuestionId(),option3);
        surveyService.getAverageRating("Razor",List.of(question));
    }
}