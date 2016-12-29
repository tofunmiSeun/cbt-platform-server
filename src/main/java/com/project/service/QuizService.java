package com.project.service;

import com.project.model.Question;
import com.project.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by BABAWANDE on 12/29/2016.
 */
@Service
public class QuizService {

    @Autowired
    QuestionRepository questionRepository;

    public List<Question> getByCourseId(Long courseId){
        return questionRepository.findByCourseId(courseId);
    }
}
