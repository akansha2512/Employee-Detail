package com.blogging.employee.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name="employees", schema = "employee_schema")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int empID;

    @Column(unique = true, length = 100, nullable = false)
    private String empName;
    private int age;
    private String department;
    private double salary;



}
