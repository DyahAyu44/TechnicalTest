package com.Example.AssessmentJava.controllers;

import com.Example.AssessmentJava.models.Salaries;
import com.Example.AssessmentJava.services.SalariesService;
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
public class SalariesRestController {

    @Autowired
    private SalariesService salariesService;

    @GetMapping("salaries")
    public ResponseEntity<List<Salaries>> getAllSalaries() {
        try {
            List<Salaries> salaries = this.salariesService.getAllSalaries();
            return new ResponseEntity<List<Salaries>>(salaries, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("salaries/{id}")
    public ResponseEntity<?> getSalariesById(@PathVariable("id") Long id) {
        try {
            Salaries salaries = this.salariesService.getSalariesById(id);
            return new ResponseEntity<>(salaries, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("salaries")
    public ResponseEntity<Salaries> saveSalaries(@RequestBody Salaries salaries) {
        try {
            this.salariesService.simpanSalaries(salaries);
            return new ResponseEntity<Salaries>(salaries, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, String> result = new HashMap<>();
            result.put("Message", e.getMessage());
            result.put("Data", null);
            return new ResponseEntity(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("salaries/{id}")
    public ResponseEntity<?> editSalaries(@RequestBody Salaries salaries, @PathVariable("id") Long id) {
        try {
            salaries.setId(id);
            this.salariesService.simpanSalaries(salaries);
            Map<String, Object> result = new HashMap<>();
            String message = "Edit Data Berhasil!";
            result.put("Message", message);
            result.put("Data", salaries);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, String> result = new HashMap<>();
            result.put("Message", e.getMessage());
            result.put("Data", null);
            return new ResponseEntity(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("salaries/{id}")
    public ResponseEntity<?> deleteSalaries(@PathVariable("id") Long id) {
        try {
            Salaries salaries = this.salariesService.getSalariesById(id);
            if (salaries != null) {
                this.salariesService.deleteSalaries(id);
                return ResponseEntity.status(HttpStatus.OK).body("Salaries Deleted");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Salaries Not Found");
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
