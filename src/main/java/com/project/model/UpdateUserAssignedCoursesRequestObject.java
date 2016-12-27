package com.project.model;

import java.util.List;

/**
 * Created by Tofunmi on 27/12/2016.
 */
public class UpdateUserAssignedCoursesRequestObject {
    public UpdateUserAssignedCoursesRequestObject(){}
    private String emailAddress;
    private List<Course> coursesToUpdate;

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public List<Course> getCoursesToUpdate() {
        return coursesToUpdate;
    }

    public void setCoursesToUpdate(List<Course> coursesToUpdate) {
        this.coursesToUpdate = coursesToUpdate;
    }
}
