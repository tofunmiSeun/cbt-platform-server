package com.project.controller;

import com.project.model.Course;
import com.project.model.Question;
import com.project.model.UpdateUserAssignedCoursesRequestObject;
import com.project.model.User;
import com.project.service.CourseService;
import com.project.service.QuizService;
import com.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by BABAWANDE on 12/26/2016.
 */
@RestController
@RequestMapping(value = "/api/courses")
public class CourseController {

    @Autowired
    CourseService courseService;

    @Autowired
    UserService userService;

    @Autowired
    QuizService quizService;

    @RequestMapping( value = "/all-courses", method = RequestMethod.GET)
     CourseResponseObject getAllCourses(){
        CourseResponseObject responseObject = new CourseResponseObject();
        responseObject.courses = courseService.getAllCourses();
        return responseObject;
    }

    @RequestMapping( value = "/update-courses", method = RequestMethod.POST)
    UpdateCoursesResponseObject updateCoursesForUser(@RequestBody UpdateUserAssignedCoursesRequestObject requestObject){
        UpdateCoursesResponseObject updateCoursesResponseObject = new UpdateCoursesResponseObject();
        try {
            User thisUser = userService.getByEmail(requestObject.getEmailAddress());
            thisUser.getStudentProfile().getCourses().addAll(requestObject.getCoursesToUpdate());
            userService.save(thisUser);

            for (Course c : requestObject.getCoursesToUpdate()){
                List<Question> questions = quizService.getByCourseId(c.getId(), 40);
                updateCoursesResponseObject.questions.addAll(questions);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return updateCoursesResponseObject;
    }

    public class CourseResponseObject {
        public List<Course> courses;
    }

    public class UpdateCoursesResponseObject{
        public List<Question> questions = new ArrayList<>();
    }
}
