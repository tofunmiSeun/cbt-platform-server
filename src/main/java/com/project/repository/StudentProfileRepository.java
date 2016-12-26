package com.project.repository;

import com.project.model.StudentProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Tofunmi on 26/12/2016.
 */
@Repository
public interface StudentProfileRepository extends JpaRepository<StudentProfile, Long> {
}
