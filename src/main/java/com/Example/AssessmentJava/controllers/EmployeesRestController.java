package com.Example.AssessmentJava.controllers;

import com.Example.AssessmentJava.models.Employees;
import com.Example.AssessmentJava.services.EmployeesService;
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
public class EmployeesRestController {

    @Autowired
    private EmployeesService employeesService;

    @GetMapping("employees")
    public ResponseEntity<List<Employees>> getAllEmployees() {
        try {
            List<Employees> employees = this.employeesService.getAllEmployees();
            return new ResponseEntity<List<Employees>>(employees, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("employees/{id}")
    public ResponseEntity<?> getEmployeesById(@PathVariable("id") Long id) {
        try {
            Employees employees = this.employeesService.getEmployeesById(id);
            return new ResponseEntity<>(employees, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("employees")
    public ResponseEntity<Employees> saveEmployees(@RequestBody Employees employees) {
        try {
            this.employeesService.simpanEmployees(employees);
            return new ResponseEntity<Employees>(employees, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, String> result = new HashMap<>();
            result.put("Message", e.getMessage());
            result.put("Data", null);
            return new ResponseEntity(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("employees/{id}")
    public ResponseEntity<?> editEmployees(@RequestBody Employees employees, @PathVariable("id") Long id) {
        try {
            employees.setId(id);
            this.employeesService.simpanEmployees(employees);
            Map<String, Object> result = new HashMap<>();
            String message = "Edit Data Berhasil!";
            result.put("Message", message);
            result.put("Data", employees);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, String> result = new HashMap<>();
            result.put("Message", e.getMessage());
            result.put("Data", null);
            return new ResponseEntity(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("employees/{id}")
    public ResponseEntity<?> deleteEmployees(@PathVariable("id") Long id) {
        try {
            Employees employees = this.employeesService.getEmployeesById(id);
            if (employees != null) {
                this.employeesService.deleteEmployees(id);
                return ResponseEntity.status(HttpStatus.OK).body("Employees Deleted");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Employees Not Found");
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
