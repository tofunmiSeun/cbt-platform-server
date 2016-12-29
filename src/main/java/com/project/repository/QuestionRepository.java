package com.project.repository;

import com.project.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by BABAWANDE on 12/27/2016.
 */
public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findByCourseId(Long courseId);
}
