package com.project.repository;

import com.project.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by BABAWANDE on 12/26/2016.
 */
@Repository
public interface CourseRepository extends JpaRepository<Course, Long>{


}
