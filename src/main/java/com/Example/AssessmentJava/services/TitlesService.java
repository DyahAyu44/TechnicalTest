package com.Example.AssessmentJava.services;

import com.Example.AssessmentJava.models.Titles;
import com.Example.AssessmentJava.repositories.TitlesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TitlesService {

    @Autowired
    private TitlesRepo titlesRepo;

    public List<Titles> getAllTitles() {
        return this.titlesRepo.findAll();
    }

    public void simpanTitles(Titles titles) {
        this.titlesRepo.save(titles);
    }

    public Titles getTitlesById(Long id) {
        return this.titlesRepo.findById(id).orElse(null);
    }

    public void deleteTitles(Long id) {
        this.titlesRepo.deleteById(id);
    }
}
