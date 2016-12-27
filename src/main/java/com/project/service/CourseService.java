package com.project.service;

import com.project.model.Course;
import com.project.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by BABAWANDE on 12/26/2016.
 */
@Service
public class CourseService {
    @Autowired
    CourseRepository courseRepository;

    public List<Course> getAllCourses(){
        return  courseRepository.findAll();
    }
    public void save(Course course){
        courseRepository.save(course);
    }
}
