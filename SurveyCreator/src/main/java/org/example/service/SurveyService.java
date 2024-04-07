package org.example.service;

import org.example.entities.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SurveyService {
    private Map<String, Survey> surveyMap;
    private QuestionService questionService;
    private UserService userService;
    private static SurveyService instance;
    private SurveyService(QuestionService questionService, UserService userService){
        this.userService = userService;
        this.questionService = questionService;
        surveyMap = new HashMap<>();
    }

    public void createSurvey(String userId, List<Question> questionList){
        if(userService.getUser(userId) != null && userService.getUser(userId) instanceof Admin){
            Survey survey = new Survey();
            survey.getQuestionList().addAll(questionList);
            surveyMap.put(survey.getSurveyId(), survey);
        }
        else{
            System.out.println("User does not have admin access");
        }
    }

    public void getAverageRating(String userId, List<Question> questionList){
        if(userService.getUser(userId) != null && userService.getUser(userId) instanceof Admin){
            for(Question question : questionList){
                double averageScore = (double)question.getTotalScore()/ question.getTotalPeopleAnswered();
                System.out.println("Average score for " + question.getQuestionName() + ":" + averageScore);
            }
        }
        else{
            System.out.println("User does not have admin access");
        }
    }

    public void answer(String userId, String questionId, Option option){
        Question question = questionService.getQuestion(questionId);
        if(question == null){
            System.out.println("Question does not exists");
            return;
        }

        if(userService.getUser(userId) != null && userService.getUser(userId) instanceof User){
            User user = (User) userService.getUser(userId);
            if(user.getUserAnsweredQuestions().containsKey(questionId)){
                System.out.println("User has already submitted survey");
                return;
            }
            int totalScore = question.getTotalScore();
            int totalPeopleAnswered = question.getTotalPeopleAnswered();
            totalScore += option.getWeightage();
            totalPeopleAnswered += 1;
            question.setTotalScore(totalScore);
            question.setTotalPeopleAnswered(totalPeopleAnswered);
            userService.editUserAnsweredQuestions(userId, questionId, option);
        }
    }

    public static SurveyService getInstance(QuestionService questionService, UserService userService) {
        if(instance == null){
            instance = new SurveyService(questionService, userService);
        }
        return instance;
    }
}
