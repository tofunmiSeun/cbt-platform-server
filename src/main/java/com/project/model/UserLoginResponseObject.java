package com.project.model;

import java.util.List;

/**
 * Created by Tofunmi on 24/12/2016.
 */
public class UserLoginResponseObject {

    public static final int ACCEPTED = 0;
    public static final int INVALID_CREDENTIALS = 1;
    public static final int NO_ACCOUNT_FOR_THIS_EMAIL = 2;
    public static final int INCORRECT_PASSWORD = 3;

    private int status;
    private User user;
    private List<Course> registeredCourses;
    private List<Question> questions;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public void setRegisteredCourses(List<Course> registeredCourses) {
        this.registeredCourses = registeredCourses;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
