package com.Example.AssessmentJava.services;

import com.Example.AssessmentJava.models.Dept_Manager;
import com.Example.AssessmentJava.repositories.Dept_ManagerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Dept_ManagerService {

    @Autowired
    private Dept_ManagerRepo dept_managerRepo;

    public List<Dept_Manager> getAllDeptManager() {
        return this.dept_managerRepo.findAll();
    }

    public void simpanDeptManager(Dept_Manager dept_manager) {
        this.dept_managerRepo.save(dept_manager);
    }

    public Dept_Manager getDeptManagerById(Long id) {
        return this.dept_managerRepo.findById(id).orElse(null);
    }

    public void deleteDeptManager(Long id) {
        this.dept_managerRepo.deleteById(id);
    }
}
