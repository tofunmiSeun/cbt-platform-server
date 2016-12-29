package com.project.repository;

import com.project.model.TestResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by BABAWANDE on 12/29/2016.
 */
@Repository
public interface TestResultRepository  extends JpaRepository<TestResult, Long> {
}
