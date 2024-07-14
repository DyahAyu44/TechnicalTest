package com.Example.AssessmentJava.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "employees")
public class Employees {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long Id;

    @Column(name = "emp_no", unique = true, nullable = false, length = 11)
    private Integer Emp_No;

    @Column(name = "birth_date")
    private Date Birth_Date;

    @Column(name = "first_name", length = 14)
    private String First_Name;

    @Column(name = "last_name", length = 16)
    private String Last_Name;

    @Column(name = "gender")
    private String Gender;

    @Column(name = "hire_date")
    private Date Hire_Date;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Integer getEmp_No() {
        return Emp_No;
    }

    public void setEmp_No(Integer emp_No) {
        Emp_No = emp_No;
    }

    public Date getBirth_Date() {
        return Birth_Date;
    }

    public void setBirth_Date(Date birth_Date) {
        Birth_Date = birth_Date;
    }

    public String getFirst_Name() {
        return First_Name;
    }

    public void setFirst_Name(String first_Name) {
        First_Name = first_Name;
    }

    public String getLast_Name() {
        return Last_Name;
    }

    public void setLast_Name(String last_Name) {
        Last_Name = last_Name;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public Date getHire_Date() {
        return Hire_Date;
    }

    public void setHire_Date(Date hire_Date) {
        Hire_Date = hire_Date;
    }
}