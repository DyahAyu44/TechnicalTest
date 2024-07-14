package com.Example.AssessmentJava.models;

import jakarta.persistence.*;

@Entity
@Table(name = "departments")
public class Departments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long Id;

    @Column(name = "dept_no", unique = true, nullable = false, length = 4)
    private String Dept_No;

    @Column(name = "dept_name", length = 40)
    private String Dept_Name;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getDept_No() {
        return Dept_No;
    }

    public void setDept_No(String dept_No) {
        Dept_No = dept_No;
    }

    public String getDept_Name() {
        return Dept_Name;
    }

    public void setDept_Name(String dept_Name) {
        Dept_Name = dept_Name;
    }
}
