package com.Example.AssessmentJava.controllers;

import com.Example.AssessmentJava.models.Titles;
import com.Example.AssessmentJava.services.TitlesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class TitlesRestController {

    @Autowired
    private TitlesService titlesService;

    @GetMapping("titles")
    public ResponseEntity<List<Titles>> getAllTitles() {
        try {
            List<Titles> titles = this.titlesService.getAllTitles();
            return new ResponseEntity<List<Titles>>(titles, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("titles/{id}")
    public ResponseEntity<?> getTitlesById(@PathVariable("id") Long id) {
        try {
            Titles titles = this.titlesService.getTitlesById(id);
            return new ResponseEntity<>(titles, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("titles")
    public ResponseEntity<Titles> saveTitles(@RequestBody Titles titles) {
        try {
            this.titlesService.simpanTitles(titles);
            return new ResponseEntity<Titles>(titles, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, String> result = new HashMap<>();
            result.put("Message", e.getMessage());
            result.put("Data", null);
            return new ResponseEntity(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("titles/{id}")
    public ResponseEntity<?> editTitles(@RequestBody Titles titles, @PathVariable("id") Long id) {
        try {
            titles.setId(id);
            this.titlesService.simpanTitles(titles);
            Map<String, Object> result = new HashMap<>();
            String message = "Edit Data Berhasil!";
            result.put("Message", message);
            result.put("Data", titles);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, String> result = new HashMap<>();
            result.put("Message", e.getMessage());
            result.put("Data", null);
            return new ResponseEntity(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("titles/{id}")
    public ResponseEntity<?> deleteTitles(@PathVariable("id") Long id) {
        try {
            Titles titles = this.titlesService.getTitlesById(id);
            if (titles != null) {
                this.titlesService.deleteTitles(id);
                return ResponseEntity.status(HttpStatus.OK).body("Titles Deleted");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Titles Not Found");
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
