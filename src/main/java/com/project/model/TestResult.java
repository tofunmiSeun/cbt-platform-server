package com.project.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by BABAWANDE on 12/29/2016.
 */
@Entity
public class TestResult {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long userId;
    private Long courseId;

    private Integer totalQuestionsCount;
    private Integer questionsAnsweredCount;
    private Integer correctAnswerCount;

    private Long timeOfTestInMilliseconds;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Integer getTotalQuestionsCount() {
        return totalQuestionsCount;
    }

    public void setTotalQuestionsCount(Integer totalQuestionsCount) {
        this.totalQuestionsCount = totalQuestionsCount;
    }

    public Integer getQuestionsAnsweredCount() {
        return questionsAnsweredCount;
    }

    public void setQuestionsAnsweredCount(Integer questionsAnsweredCount) {
        this.questionsAnsweredCount = questionsAnsweredCount;
    }

    public Integer getCorrectAnswerCount() {
        return correctAnswerCount;
    }

    public void setCorrectAnswerCount(Integer correctAnswerCount) {
        this.correctAnswerCount = correctAnswerCount;
    }

    public Long getTimeOfTestInMilliseconds() {
        return timeOfTestInMilliseconds;
    }

    public void setTimeOfTestInMilliseconds(Long timeOfTestInMilliseconds) {
        this.timeOfTestInMilliseconds = timeOfTestInMilliseconds;
    }
}
