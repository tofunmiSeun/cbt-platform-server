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
    FacultyResponseObject getFaculties(){
        FacultyResponseObject responseObject = new FacultyResponseObject();
        responseObject.faculties = facultyService.getAllFaculties();
        return responseObject;
    }

    @RequestMapping(value = "/departments/{facultyId}", method = RequestMethod.GET)
    DepartmentResponseObject getDepartmentsForFaculty(@PathVariable Long facultyId){
        DepartmentResponseObject responseObject = new DepartmentResponseObject();
        responseObject.departments = departmentService.getAllDepartmentsForFaculty(facultyId);
        return responseObject;
    }

    public class FacultyResponseObject {
        public List<Faculty> faculties;
    }

    public class DepartmentResponseObject{
        public List<Department> departments;
    }
}
