package com.blogging.employee.controller;


import com.blogging.employee.dto.CustomResponse;
import com.blogging.employee.dto.EmployeeDTO;
import com.blogging.employee.dto.EmployeeProfileDTO;
import com.blogging.employee.services.EmployeeProfileServices;
import com.blogging.employee.validation.CustomValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employeeProfile")
@RequiredArgsConstructor
public class EmployeeProfileController {

    public final EmployeeProfileServices employeeProfileServices;
    public final CustomValidation customValidation;


    @PostMapping("/employees/{empId}/create/employee_profile")
    public CustomResponse<EmployeeProfileDTO> createEmplProfile(@RequestBody EmployeeProfileDTO employeeProfileDTO , @PathVariable Long empId){
       String validation = customValidation.ProfileValidation(employeeProfileDTO);
       if(validation != null){
           return new CustomResponse<>(
                   HttpStatus.BAD_REQUEST.value(),
                   validation
           );
       }
        EmployeeProfileDTO create = employeeProfileServices.createEmplProfile(employeeProfileDTO, empId );
        return  new CustomResponse<>(
                HttpStatus.CREATED.value(),
                "Employee profile created successfully",
                create
        );
    }

    @GetMapping("/getAllEmployeeProfile")
    public CustomResponse<List<EmployeeProfileDTO>> getAllEmplProfile(){
        List<EmployeeProfileDTO> employeeProfileDTOS = employeeProfileServices.getAllEmplProfile();
        return new CustomResponse<>(
                HttpStatus.OK.value(),
                "Employee fetched Successfully",
                employeeProfileDTOS
        );
    }

    @PutMapping("/update_profile/{profile_id}")
    public  CustomResponse<EmployeeProfileDTO> updateEmplProfile(@RequestBody EmployeeProfileDTO employeeProfileDTO, @PathVariable Long profile_id){
        String validation = customValidation.ProfileValidation(employeeProfileDTO);

        if (validation != null) {
            return new CustomResponse<>(
                    HttpStatus.BAD_REQUEST.value(),
                    validation
            );
        }

        EmployeeProfileDTO updateEmplProfile = employeeProfileServices.updateEmplProfile(employeeProfileDTO, profile_id);
        return new CustomResponse<EmployeeProfileDTO>(
                HttpStatus.OK.value(),
                "Employee updated successfully",
                updateEmplProfile
        );
    }

    @DeleteMapping("/deleteEmployeeProfile/{profile_id}")
    public CustomResponse<EmployeeProfileDTO> deleteEmployee(@PathVariable Long profile_id){
        employeeProfileServices.deleteEmployee(profile_id);
        return new CustomResponse<>(
                HttpStatus.OK.value(),
                "Employee Profile deleted successfully"
        );
    }
}
