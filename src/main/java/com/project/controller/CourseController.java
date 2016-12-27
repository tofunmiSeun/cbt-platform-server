package com.project.controller;

import com.project.model.Course;
import com.project.model.UpdateUserAssignedCoursesRequestObject;
import com.project.model.User;
import com.project.service.CourseService;
import com.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping( value = "/all-courses", method = RequestMethod.GET)
     CourseResponseObject getAllCourses(){
        CourseResponseObject responseObject = new CourseResponseObject();
        responseObject.courses = courseService.getAllCourses();
        return responseObject;
    }

    @RequestMapping( value = "/assigned-courses/{email}", method = RequestMethod.GET)
    List<Course> getAssignedCourses(@PathVariable String email){
        return userService.getByEmail(email).getCourses();
    }

    @RequestMapping( value = "/update-courses", method = RequestMethod.POST)
     void updateCoursesForUser(@RequestBody UpdateUserAssignedCoursesRequestObject requestObject){
        try {
            User thisUser = userService.getByEmail(requestObject.getEmailAddress());
            thisUser.getCourses().addAll(requestObject.getCoursesToUpdate());
            userService.save(thisUser);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public class CourseResponseObject {
        public List<Course> courses;
    }
}