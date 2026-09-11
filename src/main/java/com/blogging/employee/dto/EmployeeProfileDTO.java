package com.blogging.employee.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EmployeeProfileDTO {

    private Long id;

    @NotBlank(message = "Phone No is required")
    private String phone_no;

    @NotBlank(message = "City is required")
    @Size(min = 2, max = 100, message = "City must be between 2 and 100 characters")
    private String city;
    private Long emp_id;

}
