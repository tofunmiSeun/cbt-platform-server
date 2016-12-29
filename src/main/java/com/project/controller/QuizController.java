package com.project.controller;

import com.project.model.Course;
import com.project.model.Question;
import com.project.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
        responseObject.questions = quizService.getByCourseId(courseId);
        return responseObject;
    }

    public class QuestionResponseObject{
        public List<Question> questions;
    }

}
