package com.project.repository;

import com.project.model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by BABAWANDE on 12/19/2016.
 */
@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {

}
