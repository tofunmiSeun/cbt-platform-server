package com.project.controller;

import com.project.model.User;
import com.project.model.UserProfile;
import com.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.nio.file.FileAlreadyExistsException;

/**
 * Created by BABAWANDE on 12/18/2016.
 */
@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping( value = "/register", method = RequestMethod.POST)
    void registerUser(@RequestBody User user) throws Exception{

        if(userService.getByEmail(user.getEmailAddress()) != null){
            throw new FileAlreadyExistsException("email already exists");
        }
        userService.createUser(user);
    }

    @RequestMapping( value = "/{email}/enrich-student")
    void enrichUserWithInfo(@PathVariable String email, @RequestBody UserProfile userProfile){

        User user = userService.getByEmail(email);

        user.setUserProfile(userProfile);

        userService.createUser(user);

    }

    @RequestMapping( value = "/validate-user", method = RequestMethod.POST)
    Boolean verifyUser(@RequestBody User user){

        User thisUser = userService.getByEmail(user.getEmailAddress());
        if (thisUser.getHashedPassword().equals(user.getHashedPassword()))
            return true;
        else
            return false;
    }

}
