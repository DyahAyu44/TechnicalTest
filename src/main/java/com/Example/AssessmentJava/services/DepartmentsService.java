package com.Example.AssessmentJava.services;

import com.Example.AssessmentJava.models.Departments;
import com.Example.AssessmentJava.repositories.DepartmentsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentsService {

    @Autowired
    private DepartmentsRepo departmentsRepo;

    public List<Departments> getAllDepartments() {
        return this.departmentsRepo.findAll();
    }

    public void simpanDepartments(Departments departments) {
        this.departmentsRepo.save(departments);
    }

    public Departments getDepartmentsById(Long id) {
        return this.departmentsRepo.findById(id).orElse(null);
    }

    public void deleteDepartments(Long id) {
        this.departmentsRepo.deleteById(id);
    }
}
