package com.Example.AssessmentJava.repositories;

import com.Example.AssessmentJava.models.Salaries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalariesRepo extends JpaRepository<Salaries, Long> {
}
