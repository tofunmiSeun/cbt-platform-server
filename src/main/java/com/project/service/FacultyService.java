package com.project.service;

import com.project.model.Faculty;
import com.project.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Tofunmi on 24/12/2016.
 */

@Service
public class FacultyService {

    @Autowired
    FacultyRepository facultyRepository;

    public List<Faculty> getAllFaculties(){
        return facultyRepository.findAll();
    }
    public void saveFaculty(Faculty faculty){
        facultyRepository.save(faculty);
    }
    public Faculty findByName(String name){
        return facultyRepository.findByName(name);
    }
}
