package com.project.repository;

import com.project.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by BABAWANDE on 12/27/2016.
 */
public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findByCourseId(Long courseId);

    @Query("SELECT q FROM Question q WHERE q.courseId = :courseId LIMIT :max")
    List<Question> findByCourseId(@Param("courseId")Long courseId, @Param("max")Integer limit);

    @Query("SELECT q FROM Question q WHERE q.courseId = :courseId AND q.id NOT IN :idsOfQuestionsToExempt LIMIT :max")
    List<Question> findQuestions(@Param("courseId")Long courseId, @Param("idsOfQuestionsToExempt") List<Long> idsOfQuestionsToExempt,
                                 @Param("max")Integer limit);
}
