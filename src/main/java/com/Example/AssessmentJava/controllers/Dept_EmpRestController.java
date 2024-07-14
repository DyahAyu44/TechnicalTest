package com.Example.AssessmentJava.controllers;

import com.Example.AssessmentJava.models.Dept_Emp;
import com.Example.AssessmentJava.services.Dept_EmpService;
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
public class Dept_EmpRestController {

    @Autowired
    private Dept_EmpService deptEmpService;

    @GetMapping("dept_emp")
    public ResponseEntity<List<Dept_Emp>> getAllDeptEmp() {
        try {
            List<Dept_Emp> dept_emps = this.deptEmpService.getAllDeptEmp();
            return new ResponseEntity<List<Dept_Emp>>(dept_emps, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("dept_emp/{id}")
    public ResponseEntity<?> getDeptEmpById(@PathVariable("id") Long id) {
        try {
            Dept_Emp dept_emp = this.deptEmpService.getDeptEmpById(id);
            return new ResponseEntity<>(dept_emp, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("dept_emp")
    public ResponseEntity<Dept_Emp> saveDeptEmp(@RequestBody Dept_Emp dept_emp) {
        try {
            this.deptEmpService.simpanDeptEmp(dept_emp);
            return new ResponseEntity<Dept_Emp>(dept_emp, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, String> result = new HashMap<>();
            result.put("Message", e.getMessage());
            result.put("Data", null);
            return new ResponseEntity(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("dept_emp/{id}")
    public ResponseEntity<?> editDeptEmp(@RequestBody Dept_Emp dept_emp, @PathVariable("id") Long id) {
        try {
            dept_emp.setId(id);
            this.deptEmpService.simpanDeptEmp(dept_emp);
            Map<String, Object> result = new HashMap<>();
            String message = "Edit Data Berhasil!";
            result.put("Message", message);
            result.put("Data", dept_emp);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, String> result = new HashMap<>();
            result.put("Message", e.getMessage());
            result.put("Data", null);
            return new ResponseEntity(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("dept_emp/{id}")
    public ResponseEntity<?> deleteDeptEmp(@PathVariable("id") Long id) {
        try {
            Dept_Emp dept_emp = this.deptEmpService.getDeptEmpById(id);
            if (dept_emp != null) {
                this.deptEmpService.deleteDeptEmp(id);
                return ResponseEntity.status(HttpStatus.OK).body("Dept_Emp Deleted");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Dept_Emp Not Found");
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}


