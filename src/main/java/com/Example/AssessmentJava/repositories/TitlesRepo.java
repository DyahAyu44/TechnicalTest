package com.Example.AssessmentJava.repositories;

import com.Example.AssessmentJava.models.Titles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TitlesRepo extends JpaRepository<Titles, Long> {
}
