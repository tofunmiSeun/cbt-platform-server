package com.project.controller;

import com.project.model.Course;
import com.project.model.Question;
import com.project.model.TestResult;
import com.project.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by BABAWANDE on 12/27/2016.
 */
@RestController
@RequestMapping( value = "/api/questions")
public class QuizController {

    @Autowired
    QuizService quizService;

    @RequestMapping( value = "/all}", method = RequestMethod.GET)
    private List<Question> getQuestions(){
        return quizService.getAllQuestions();
    }

    @RequestMapping( value = "/add}", method = RequestMethod.GET)
    private void getQuestions(@RequestBody Question question){
        quizService.add(question);
    }

    @RequestMapping( method = RequestMethod.GET)
    private QuestionResponseObject getQuestions(@RequestBody Map<Long, List<Long>> requestBody){
        QuestionResponseObject responseObject = new QuestionResponseObject();
        //TODO: change this implementation to get fresh questions
        responseObject.questions = quizService.getQuestionsForCourses(requestBody, 40);
        return responseObject;
    }

    @RequestMapping( value = "/{courseId}", method = RequestMethod.GET)
    private QuestionResponseObject getQuestionsForCourse(@PathVariable Long courseId){
        QuestionResponseObject responseObject = new QuestionResponseObject();
        //TODO: change this implementation to get fresh questions
        responseObject.questions = quizService.getByCourseId(courseId);
        return responseObject;
    }

    @RequestMapping( value = "/{courseId}/{limit}", method = RequestMethod.GET)
    private QuestionResponseObject getQuestionsForCourse(@PathVariable Long courseId, @PathVariable Integer limit){
        QuestionResponseObject responseObject = new QuestionResponseObject();
        //TODO: change this implementation to get fresh questions
        responseObject.questions = quizService.getByCourseId(courseId, limit);
        return responseObject;
    }

    @RequestMapping( value = "/save-results", method = RequestMethod.POST)
    void saveTestResult(@RequestBody List<TestResult> results){
        quizService.saveTestResult(results);
    }

    public class QuestionResponseObject{
        public List<Question> questions;
    }

}
