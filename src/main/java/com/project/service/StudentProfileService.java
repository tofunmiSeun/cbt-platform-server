package com.project.service;

import com.project.model.StudentProfile;
import com.project.repository.StudentProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Tofunmi on 26/12/2016.
 */
@Service
public class StudentProfileService {
    @Autowired
    StudentProfileRepository studentProfileRepository;

    public StudentProfile saveStudentProfile(StudentProfile profile){
        return studentProfileRepository.save(profile);
    }
}
