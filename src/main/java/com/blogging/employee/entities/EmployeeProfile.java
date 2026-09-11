package com.blogging.employee.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class EmployeeProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String phone_no;
    private String city;
    @OneToOne
    @JoinColumn(name = "emp_id")
    private Employee employee;


}
