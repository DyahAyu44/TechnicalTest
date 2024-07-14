package com.Example.AssessmentJava.controllers;

import com.Example.AssessmentJava.models.Departments;
import com.Example.AssessmentJava.services.DepartmentsService;
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
public class DepartmentsRestController {

    @Autowired
    private DepartmentsService departmentsService;

    @GetMapping("departments")
    public ResponseEntity<List<Departments>> getAllDepartments() {
        try {
            List<Departments> departments = this.departmentsService.getAllDepartments();
            return new ResponseEntity<List<Departments>>(departments, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("departments/{id}")
    public ResponseEntity<?> getDepartmentsById(@PathVariable("id") Long id) {
        try {
            Departments departments = this.departmentsService.getDepartmentsById(id);
            return new ResponseEntity<>(departments, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("departments")
    public ResponseEntity<Departments> saveDepartments(@RequestBody Departments departments) {
        try {
            this.departmentsService.simpanDepartments(departments);
            return new ResponseEntity<Departments>(departments, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, String> result = new HashMap<>();
            result.put("Message", e.getMessage());
            result.put("Data", null);
            return new ResponseEntity(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("departments/{id}")
    public ResponseEntity<?> editDepartments(@RequestBody Departments departments, @PathVariable("id") Long id) {
        try {
            departments.setId(id);
            this.departmentsService.simpanDepartments(departments);
            Map<String, Object> result = new HashMap<>();
            String message = "Edit Data Berhasil!";
            result.put("Message", message);
            result.put("Data", departments);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, String> result = new HashMap<>();
            result.put("Message", e.getMessage());
            result.put("Data", null);
            return new ResponseEntity(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("departments/{id}")
    public ResponseEntity<?> deleteDepartments(@PathVariable("id") Long id) {
        try {
            Departments departments = this.departmentsService.getDepartmentsById(id);
            if (departments != null) {
                this.departmentsService.deleteDepartments(id);
                return ResponseEntity.status(HttpStatus.OK).body("Departments Deleted");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Departments Not Found");
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
