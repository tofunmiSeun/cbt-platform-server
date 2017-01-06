package com.project.controller;

import com.project.model.Course;
import com.project.model.Question;
import com.project.model.TestResult;
import com.project.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by BABAWANDE on 12/27/2016.
 */
@RestController
@RequestMapping( value = "/api/questions")
public class QuizController {

    @Autowired
    QuizService quizService;

    @RequestMapping( value = "/{courseId}", method = RequestMethod.GET)
    private QuestionResponseObject getQuestionsForCourse(@PathVariable Long courseId){
        QuestionResponseObject responseObject = new QuestionResponseObject();
        //TODO: change this implementation to get fresh questions
        responseObject.questions = quizService.getByCourseId(courseId);
        return responseObject;
    }

    @RequestMapping( value = "/save-result", method = RequestMethod.POST)
    void saveTestResult(@RequestBody TestResult result){
        quizService.saveTestResult(result);
    }

    public class QuestionResponseObject{
        public List<Question> questions;
    }

}
