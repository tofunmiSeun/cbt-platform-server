package com.project.controller;

import com.project.model.Course;
import com.project.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by BABAWANDE on 12/26/2016.
 */
@Controller
@RequestMapping(value = "/api/courses")
public class CourseController {

    @Autowired
    CourseService courseService;

    @RequestMapping( value = "/all-courses", method = RequestMethod.GET)
    public List<Course> getAllCourses(){
        return courseService.getAllCourses();
    }
}
