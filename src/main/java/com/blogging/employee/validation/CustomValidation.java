package com.blogging.employee.validation;

import com.blogging.employee.dto.EmployeeDTO;

import com.blogging.employee.dto.EmployeeProfileDTO;
import com.blogging.employee.repositiories.EmployeeRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class CustomValidation {

    public String Validation(EmployeeDTO employeeDTO){

        if(employeeDTO.getName()==null || employeeDTO.getName().isBlank()){
            return "Name is required";
        }

        if(employeeDTO.getName().length()<2 || employeeDTO.getName().length()>100){
            return "Name must be between 2 and 100 characters";
        }
        if(employeeDTO.getAge()==null || employeeDTO.getAge()==0){
            return "Age is required";
        }

        if(employeeDTO.getAge()<18 || employeeDTO.getAge()>=80 ){
            return "Age must be between 18 and 80";
        }

        if (employeeDTO.getDepartment()==null || employeeDTO.getDepartment().isBlank()){
            return "Department must be required";
        }

        if (employeeDTO.getSalary()==null){
            return "Salary must be required";
        }
        if(employeeDTO.getSalary()<=0){
            return "Salary must be greater than 0";
        }

        if (employeeDTO.getAddress()==null || employeeDTO.getAddress().isBlank()){
            return "Address is required";
        }
        return  null;

    }

    public String ProfileValidation(EmployeeProfileDTO employeeProfileDTO){

        if(employeeProfileDTO.getPhone_no()==null || employeeProfileDTO.getPhone_no().isBlank()){
            return "Phone Number is required";
        }

        if(employeeProfileDTO.getCity()==null || employeeProfileDTO.getCity().isBlank()){
            return "City is required";
        }

        if(employeeProfileDTO.getCity().length()<2 || employeeProfileDTO.getCity().length()>100){
            return "City must be between 2 and 100 characters";
        }
        return null;
    }

}
