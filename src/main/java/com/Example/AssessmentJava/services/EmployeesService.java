package com.Example.AssessmentJava.services;

import com.Example.AssessmentJava.models.Employees;
import com.Example.AssessmentJava.repositories.EmployeesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeesService {

    @Autowired
    private EmployeesRepo employeesRepo;

    public List<Employees> getAllEmployees() {
        return this.employeesRepo.findAll();
    }

    public void simpanEmployees(Employees employees) {
        this.employeesRepo.save(employees);
    }

    public Employees getEmployeesById(Long id) {
        return this.employeesRepo.findById(id).orElse(null);
    }

    public void deleteEmployees(Long id) {
        this.employeesRepo.deleteById(id);
    }
}
