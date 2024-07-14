package com.Example.AssessmentJava.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "dept_manager")
public class Dept_Manager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "dept_no", insertable = false, updatable = false)
    public Departments departments;

    @Column(name = "dept_no")
    private Long departmentsId;

    @ManyToOne
    @JoinColumn(name = "emp_no", insertable = false, updatable = false)
    public Employees employees;

    @Column(name = "emp_no")
    private Long employeesId;

    @Column(name = "from_date")
    private Date From_Date;

    @Column(name = "to_date")
    private Date To_Date;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Departments getDepartments() {
        return departments;
    }

    public void setDepartments(Departments departments) {
        this.departments = departments;
    }

    public Long getDepartmentsId() {
        return departmentsId;
    }

    public void setDepartmentsId(Long departmentsId) {
        this.departmentsId = departmentsId;
    }

    public Employees getEmployees() {
        return employees;
    }

    public void setEmployees(Employees employees) {
        this.employees = employees;
    }

    public Long getEmployeesId() {
        return employeesId;
    }

    public void setEmployeesId(Long employeesId) {
        this.employeesId = employeesId;
    }

    public Date getFrom_Date() {
        return From_Date;
    }

    public void setFrom_Date(Date from_Date) {
        From_Date = from_Date;
    }

    public Date getTo_Date() {
        return To_Date;
    }

    public void setTo_Date(Date to_Date) {
        To_Date = to_Date;
    }
}
