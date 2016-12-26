package com.project.service;

import com.project.model.Department;
import com.project.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by BABAWANDE on 12/24/2016.
 */
@Service
public class DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

<<<<<<< HEAD
    public List<Department> getAllDepartmentsForFaculty(Long facultyId){
        return departmentRepository.findByFacultyId(facultyId);
    }
}
=======
    public List<Department> getAllDepartmentsForFaculty(Long facultyId) {
        return departmentRepository.findByFacultyId(facultyId);
    }

    public void save(Department department){
        departmentRepository.save(department);
    }
}

>>>>>>> 8e70319b004e5f751774b78ac06f31c377468428
