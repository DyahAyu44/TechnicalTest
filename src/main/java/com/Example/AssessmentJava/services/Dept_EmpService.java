package com.Example.AssessmentJava.services;

import com.Example.AssessmentJava.models.Dept_Emp;
import com.Example.AssessmentJava.repositories.Dept_EmpRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Dept_EmpService {

    @Autowired
    private Dept_EmpRepo dept_empRepo;

    public List<Dept_Emp> getAllDeptEmp() {
        return this.dept_empRepo.findAll();
    }

    public void simpanDeptEmp(Dept_Emp dept_emp) {
        this.dept_empRepo.save(dept_emp);
    }

    public Dept_Emp getDeptEmpById(Long id) {
        return this.dept_empRepo.findById(id).orElse(null);
    }

    public void deleteDeptEmp(Long id) {
        this.dept_empRepo.deleteById(id);
    }
}
