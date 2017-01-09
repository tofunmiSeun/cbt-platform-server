package com.project.service;

import com.project.model.Course;
import com.project.model.Question;
import com.project.model.TestResult;
import com.project.repository.QuestionRepository;
import com.project.repository.TestResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by BABAWANDE on 12/29/2016.
 */
@Service
public class QuizService {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    TestResultRepository testResultRepository;

    public List<Question> getByCourseId(Long courseId){
        return questionRepository.findByCourseId(courseId);
    }

    public List<Question> getQuestionsForCourses(Map<Long, List<Long>> mapOfCoursesWithQuestionsToExempt){
        List<Question> questionsList = new ArrayList<>();
        Set<Long> listOfCoursesId = mapOfCoursesWithQuestionsToExempt.keySet();
        for (Long id : listOfCoursesId){
            List<Question> questions = questionRepository.findQuestions(id, mapOfCoursesWithQuestionsToExempt.get(id));
            for (Question q : questions){
                questionsList.add(q);
            }
        }

        return questionsList;
    }

    public List<Question> getQuestionsForCourses(List<Course> courses){
        List<Question> questionsList = new ArrayList<>();
        for (Course c : courses){
            List<Question> questions = questionRepository.findByCourseId(c.getId());
            for (Question q : questions){
                questionsList.add(q);
            }
        }

        return questionsList;
    }

    public void addQuestion(Question question) {
        questionRepository.save(question);
    }

    public void saveTestResult(List<TestResult> results){
        testResultRepository.save(results);
    }
}
