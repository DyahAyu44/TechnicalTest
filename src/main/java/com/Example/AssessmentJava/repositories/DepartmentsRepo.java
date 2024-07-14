package com.Example.AssessmentJava.repositories;

import com.Example.AssessmentJava.models.Departments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentsRepo extends JpaRepository<Departments, Long> {
}
