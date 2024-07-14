package com.Example.AssessmentJava.services;

import com.Example.AssessmentJava.models.Salaries;
import com.Example.AssessmentJava.repositories.SalariesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalariesService {

    @Autowired
    private SalariesRepo salariesRepo;

    public List<Salaries> getAllSalaries() {
        return this.salariesRepo.findAll();
    }

    public void simpanSalaries(Salaries salaries) {
        this.salariesRepo.save(salaries);
    }

    public Salaries getSalariesById(Long id) {
        return this.salariesRepo.findById(id).orElse(null);
    }

    public void deleteSalaries(Long id) {
        this.salariesRepo.deleteById(id);
    }
}
