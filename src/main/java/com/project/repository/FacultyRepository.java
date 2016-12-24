package com.project.repository;

import com.project.model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Tofunmi on 24/12/2016.
 */

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {

}
