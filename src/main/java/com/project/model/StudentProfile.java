package com.project.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by BABAWANDE on 12/18/2016.
 */
@Entity
public class StudentProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private Faculty faculty;

    @OneToOne
    private Department department;
    private Integer numericalValueOfStudentLevel;

    @ElementCollection
    private List<Course> courses;


    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumericalValueOfStudentLevel() {
        return numericalValueOfStudentLevel;
    }

    public void setNumericalValueOfStudentLevel(Integer numericalValueOfStudentLevel) {
        this.numericalValueOfStudentLevel = numericalValueOfStudentLevel;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
