package com.project.repository;

import com.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by BABAWANDE on 12/18/2016.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmailAddress(String emailAddress);
}
