package com.project.controller;

import com.project.model.*;
import com.project.service.QuizService;
import com.project.service.StudentProfileService;
import com.project.service.UserService;
import com.project.utils.UserCredentialUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

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
    @Autowired
    StudentProfileService studentProfileService;
    @Autowired
    QuizService quizService;

    UserCredentialUtils userCredentialUtils = new UserCredentialUtils();

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    ResponseEntity registerUser(@RequestBody User user) throws Exception {

        ResponseEntity responseEntity;
        UserSignUpResponseObject responseObject = new UserSignUpResponseObject();

        if (userService.getByEmail(user.getEmailAddress()) != null) {
            // email already used
            responseObject.setStatus(UserSignUpResponseObject.EMAIL_ALREADY_IN_USE);
            responseEntity = new ResponseEntity<>(responseObject, HttpStatus.OK);
            return responseEntity;
        }

        user.setPassword(userCredentialUtils.getHashedPasswordForUser(user));
        user.setLoggedIn(true);
        userService.createUser(user);

        user.setPassword(null);

        responseObject.setStatus(UserSignUpResponseObject.ACCEPTED);
        responseObject.setUser(user);
        responseEntity = new ResponseEntity<>(responseObject, HttpStatus.OK);
        return responseEntity;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    ResponseEntity login(@RequestBody Map<String, String> loginCredentials) {

        String userEmail = loginCredentials.get("emailAddress");
        String password = loginCredentials.get("password");

        ResponseEntity responseEntity;
        UserLoginResponseObject responseObject = new UserLoginResponseObject();

        if (!(userEmail != null && password != null)) {
            // Invalid credentials sent
            responseObject.setStatus(UserLoginResponseObject.INVALID_CREDENTIALS);
            responseEntity = new ResponseEntity<>(responseObject, HttpStatus.OK);
            return responseEntity;
        }
        User thisUser = userService.getByEmail(userEmail);

        if (thisUser == null) {
            // User does not exist for this email
            responseObject.setStatus(UserLoginResponseObject.NO_ACCOUNT_FOR_THIS_EMAIL);
            responseEntity = new ResponseEntity<>(responseObject, HttpStatus.OK);
            return responseEntity;
        }

        User dummyUser = new User();
        dummyUser.setEmailAddress(userEmail);
        dummyUser.setPassword(password);

        String hashedPassword = userCredentialUtils.getHashedPasswordForUser(dummyUser);
        if (!hashedPassword.equals(thisUser.getPassword())) {
            // Incorrect password
            responseObject.setStatus(UserLoginResponseObject.INCORRECT_PASSWORD);
            responseEntity = new ResponseEntity<>(responseObject, HttpStatus.OK);
            return responseEntity;
        }

        thisUser.setLoggedIn(true);
        userService.save(thisUser);

        thisUser.setPassword(null);
        responseObject.setStatus(UserLoginResponseObject.ACCEPTED);
        responseObject.setUser(thisUser);
        if (thisUser.getStudentProfile() != null && thisUser.getStudentProfile().getCourses() != null){
            responseObject.setRegisteredCourses(thisUser.getStudentProfile().getCourses());

            List<Question> questionsForRegisteredCourses = quizService.getQuestionsForCourses(responseObject.getRegisteredCourses(), 40);
            responseObject.setQuestions(questionsForRegisteredCourses);
        }
        responseEntity = new ResponseEntity<>(responseObject, HttpStatus.OK);
        return responseEntity;
    }

    @RequestMapping(value = "/{email}/enrich-student", method = RequestMethod.POST)
    void enrichUserWithInfo(@PathVariable String email, @RequestBody StudentProfile studentProfile) {

        User user = userService.getByEmail(email);
        if (user == null) {
            throw new HttpServerErrorException(HttpStatus.NOT_FOUND);
        }

        studentProfile = studentProfileService.saveStudentProfile(studentProfile);
        user.setStudentProfile(studentProfile);
        userService.save(user);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    ResponseEntity logout(@RequestBody SignOutUserRequestObject requestObject) {
        if (requestObject == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        User user = userService.getByEmail(requestObject.emailAddress);
        for (Long questionId : requestObject.attemptedQuestionsIds){
            if (!user.getStudentProfile().getAttemptedQuestionsIds().contains(questionId)){
                user.getStudentProfile().getAttemptedQuestionsIds().add(questionId);
            }
        }
        
        quizService.saveTestResult(requestObject.testResultsPendingUpload);

        user.setLoggedIn(false);
        userService.save(user);

        return new ResponseEntity(HttpStatus.OK);
    }
}
