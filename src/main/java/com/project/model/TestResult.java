package com.project.model;

import javax.persistence.*;
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

    @ElementCollection
    private List<Long> questionsId;

    private Integer totalQuestionsCount;
    private Integer questionsAnsweredCount;
    private Integer correctAnswerCount;

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

    public List<Long> getQuestionsId() {
        return questionsId;
    }

    public void setQuestionsId(List<Long> questionsId) {
        this.questionsId = questionsId;
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
}
