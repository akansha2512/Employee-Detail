package com.blogging.employee.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class EmployeeDTO {


    private Integer empID;
    @NotEmpty
    @Size(min=4, message = "Must be atleast 4 char")
    private String empName;
    private Integer age;
    private String department;
    private double salary;



}
