package com.project.controller;

import com.project.model.User;
import com.project.model.UserLoginResponseObject;
import com.project.model.UserProfile;
import com.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.FileAlreadyExistsException;
import java.util.Map;

/**
 * Created by BABAWANDE on 12/18/2016.
 */
@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping( value = "/register", method = RequestMethod.POST)
    User registerUser(@RequestBody User user) throws Exception{

        if(userService.getByEmail(user.getEmailAddress()) != null){
            throw new FileAlreadyExistsException("email already exists");
        }

        user.setPassword(getHashedPasswordForUser(user));
        userService.createUser(user);

        return user;
    }

    @RequestMapping( value = "/{email}/enrich-student")
    void enrichUserWithInfo(@PathVariable String email, @RequestBody UserProfile userProfile){

        User user = userService.getByEmail(email);

        user.setUserProfile(userProfile);

        userService.save(user);

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

        String hashedPassword = getHashedPasswordForUser(dummyUser);
        if (!hashedPassword.equals(thisUser.getPassword())){
            // Incorrect password
            responseObject.setStatus(UserLoginResponseObject.INCORRECT_PASSWORD);
            responseEntity = new ResponseEntity<>(responseObject, HttpStatus.ACCEPTED);
            return responseEntity;
        }

        responseObject.setStatus(UserLoginResponseObject.ACCEPTED);
        responseObject.setUser(thisUser);
        responseEntity = new ResponseEntity<>(responseObject, HttpStatus.ACCEPTED);
        return responseEntity;
    }


    private String getHashedPasswordForUser(User user){
        byte [] userEmailInBytes = user.getEmailAddress().getBytes();
        byte [] userPasswordInBytes = user.getPassword().getBytes();

        byte [] hash = new byte[userEmailInBytes.length + userPasswordInBytes.length];

        for (int i = 0; i < hash.length; i++){
            if (i < userEmailInBytes.length){
                hash[i] = userEmailInBytes[i];
            }
            else{
                hash[i] = userPasswordInBytes[i - userEmailInBytes.length];
            }
        }

        return new String (hash);
    }
}
