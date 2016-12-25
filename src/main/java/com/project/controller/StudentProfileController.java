package com.project.controller;

import com.project.model.Department;
import com.project.model.Faculty;
import com.project.service.DepartmentService;
import com.project.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Tofunmi on 25/12/2016.
 */
@RestController
@RequestMapping(value = "/api/student-profile")
public class StudentProfileController {


    @Autowired
    FacultyService facultyService;
    @Autowired
    DepartmentService departmentService;

    @RequestMapping(value = "/faculties", method = RequestMethod.GET)
    List<Faculty> getFaculties(){
        return facultyService.getAllFaculties();
    }

    @RequestMapping(value = "/departments/{facultyId}", method = RequestMethod.GET)
    List<Department> getDepartmentsForFaculty(@PathVariable Long facultyId){
        return departmentService.findDepartmentsForFaculty(facultyId);
    }

    @RequestMapping(value = "/levels/{courseDurationInYears}", method = RequestMethod.GET)
    List<Department> getLevelsWithinCourseDuration(@PathVariable Integer courseDurationInYears){
        return null;
        // TODO: finish up
    }
}
