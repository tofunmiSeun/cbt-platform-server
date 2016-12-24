package com.project.service;

import com.project.model.User;
import com.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by BABAWANDE on 12/19/2016.
 */
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User getByEmail(String email){
        return userRepository.findByEmailAddress(email);
    }

    public void createUser(User user){
        save(user);
    }

    public void save(User user){
        userRepository.save(user);
    }

}
