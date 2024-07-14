package com.Example.AssessmentJava.controllers;

import com.Example.AssessmentJava.models.Dept_Manager;
import com.Example.AssessmentJava.services.Dept_ManagerService;
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
public class Dept_ManagerRestController {

    @Autowired
    private Dept_ManagerService deptManagerService;

    @GetMapping("dept_manager")
    public ResponseEntity<List<Dept_Manager>> getAllDeptManager() {
        try {
            List<Dept_Manager> dept_manager = this.deptManagerService.getAllDeptManager();
            return new ResponseEntity<List<Dept_Manager>>(dept_manager, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("dept_manager/{id}")
    public ResponseEntity<?> getDeptManagerById(@PathVariable("id") Long id) {
        try {
            Dept_Manager dept_manager = this.deptManagerService.getDeptManagerById(id);
            return new ResponseEntity<>(dept_manager, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("dept_manager")
    public ResponseEntity<Dept_Manager> saveDeptManager(@RequestBody Dept_Manager dept_manager) {
        try {
            this.deptManagerService.simpanDeptManager(dept_manager);
            return new ResponseEntity<Dept_Manager>(dept_manager, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, String> result = new HashMap<>();
            result.put("Message", e.getMessage());
            result.put("Data", null);
            return new ResponseEntity(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("dept_manager/{id}")
    public ResponseEntity<?> editDeptManager(@RequestBody Dept_Manager dept_manager, @PathVariable("id") Long id) {
        try {
            dept_manager.setId(id);
            this.deptManagerService.simpanDeptManager(dept_manager);
            Map<String, Object> result = new HashMap<>();
            String message = "Edit Data Berhasil!";
            result.put("Message", message);
            result.put("Data", dept_manager);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, String> result = new HashMap<>();
            result.put("Message", e.getMessage());
            result.put("Data", null);
            return new ResponseEntity(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("dept_manager/{id}")
    public ResponseEntity<?> deleteDeptManager(@PathVariable("id") Long id) {
        try {
            Dept_Manager dept_manager = this.deptManagerService.getDeptManagerById(id);
            if (dept_manager != null) {
                this.deptManagerService.deleteDeptManager(id);
                return ResponseEntity.status(HttpStatus.OK).body("Employees Deleted");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Employees Not Found");
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
