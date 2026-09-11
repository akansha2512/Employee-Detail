package com.blogging.employee.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name="employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, length = 100, nullable = false)
    private String name;
    private int age;
    private String department;
    private double salary;
    private String address;
    @OneToOne(mappedBy = "employee",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private EmployeeProfile employeeProfile;
}
