package com.project.controller;

import com.project.model.*;
import com.project.service.UserService;
import com.project.utils.UserCredentialUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by BABAWANDE on 12/18/2016.
 */
@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    @Autowired
    UserService userService;

    UserCredentialUtils userCredentialUtils = new UserCredentialUtils();

    @RequestMapping( value = "/register", method = RequestMethod.POST)
    ResponseEntity registerUser(@RequestBody User user) throws Exception{

        ResponseEntity responseEntity;
        UserSignUpResponseObject responseObject = new UserSignUpResponseObject();

        if(userService.getByEmail(user.getEmailAddress()) != null){
            // email already used
            responseObject.setStatus(UserSignUpResponseObject.EMAIL_ALREADY_IN_USE);
            responseEntity = new ResponseEntity<>(responseObject, HttpStatus.ACCEPTED);
            return responseEntity;
        }

        user.setPassword(userCredentialUtils.getHashedPasswordForUser(user));
        user.setLoggedIn(true);
        userService.createUser(user);

        user.setPassword(null);

        responseObject.setStatus(UserSignUpResponseObject.ACCEPTED);
        responseObject.setUser(user);
        responseEntity = new ResponseEntity<>(responseObject, HttpStatus.ACCEPTED);
        return responseEntity;
    }

    @RequestMapping( value = "/login", method = RequestMethod.POST)
    ResponseEntity login(@RequestBody Map<String, String> loginCredentials){

        String userEmail = loginCredentials.get("emailAddress");
        String password = loginCredentials.get("password");

        ResponseEntity responseEntity;
        UserLoginResponseObject responseObject = new UserLoginResponseObject();

        if (!(userEmail != null && password != null)){
            // Invalid credentials sent
            responseObject.setStatus(UserLoginResponseObject.INVALID_CREDENTIALS);
            responseEntity = new ResponseEntity<>(responseObject, HttpStatus.ACCEPTED);
            return responseEntity;
        }
        User thisUser = userService.getByEmail(userEmail);

        if (thisUser == null){
            // User does not exist for this email
            responseObject.setStatus(UserLoginResponseObject.NO_ACCOUNT_FOR_THIS_EMAIL);
            responseEntity = new ResponseEntity<>(responseObject, HttpStatus.ACCEPTED);
            return responseEntity;
        }

        User dummyUser = new User();
        dummyUser.setEmailAddress(userEmail);
        dummyUser.setPassword(password);

        String hashedPassword = userCredentialUtils.getHashedPasswordForUser(dummyUser);
        if (!hashedPassword.equals(thisUser.getPassword())){
            // Incorrect password
            responseObject.setStatus(UserLoginResponseObject.INCORRECT_PASSWORD);
            responseEntity = new ResponseEntity<>(responseObject, HttpStatus.ACCEPTED);
            return responseEntity;
        }

        thisUser.setLoggedIn(true);
        userService.save(thisUser);

        thisUser.setPassword(null);
        responseObject.setStatus(UserLoginResponseObject.ACCEPTED);
        responseObject.setUser(thisUser);
        responseEntity = new ResponseEntity<>(responseObject, HttpStatus.ACCEPTED);
        return responseEntity;
    }

    @RequestMapping( value = "/{email}/enrich-student", method = RequestMethod.POST)
    void enrichUserWithInfo(@PathVariable String email, @RequestBody StudentProfile studentProfile){

        User user = userService.getByEmail(email);

        user.setStudentProfile(studentProfile);

        userService.save(user);

    }
}
