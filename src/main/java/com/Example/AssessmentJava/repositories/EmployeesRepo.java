package com.Example.AssessmentJava.repositories;

import com.Example.AssessmentJava.models.Employees;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeesRepo extends JpaRepository<Employees, Long> {
}
