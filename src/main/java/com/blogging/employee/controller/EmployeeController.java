package com.blogging.employee.controller;

import com.blogging.employee.dto.CustomResponse;
import com.blogging.employee.dto.EmployeeDTO;
import com.blogging.employee.services.EmployeeServices;
import com.blogging.employee.validation.CustomValidation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor
public class EmployeeController {

    public final EmployeeServices employeeServices;
    public final CustomValidation customValidation;

    @PostMapping("/create")
    public CustomResponse<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        String validation = customValidation.Validation(employeeDTO);

        if (validation != null) {
            return new CustomResponse<>(
                    HttpStatus.BAD_REQUEST.value(),
                    validation
            );
        }
        EmployeeDTO create = employeeServices.createEmployee(employeeDTO);
        return new CustomResponse<>(
                HttpStatus.CREATED.value(),
                "Employee created successfully",
                create
        );

    }

    @PostMapping("/createMultiEmpl")
    public CustomResponse<List<EmployeeDTO>> createMultiEmpl(@RequestBody List<EmployeeDTO> listEmployeeDTO) {

        for (EmployeeDTO employeeDTO : listEmployeeDTO) {
            String validation = customValidation.Validation(employeeDTO);
            if (validation != null) {
                return new CustomResponse<>(
                        HttpStatus.BAD_REQUEST.value(),
                        validation
                );
            }
        }

        List<EmployeeDTO> createEmpl = employeeServices.createMultiEmpl(listEmployeeDTO);
        return new CustomResponse<>(
                HttpStatus.CREATED.value(),
                "Employee created successfully",
                createEmpl
        );
    }

    @GetMapping("/getAllEmployee")
    public CustomResponse<List<EmployeeDTO>> getAllEmployee() {
        List<EmployeeDTO> employees = employeeServices.getAllEmployee();
        return new CustomResponse<>(
                HttpStatus.OK.value(),
                "Employee fetched Successfully",
                employees
        );
    }


    @PutMapping("/updateEmployee/{id}")
    public CustomResponse<EmployeeDTO> updateEmployee(@RequestBody EmployeeDTO employeeDTO, @PathVariable Long id) {

        try{
            String validation = customValidation.Validation(employeeDTO);

            if (validation != null) {
                return new CustomResponse<>(
                        HttpStatus.BAD_REQUEST.value(),
                        validation
                );
            }

            EmployeeDTO update = employeeServices.updateEmployee(employeeDTO, id);
            return new CustomResponse<EmployeeDTO>(
                    HttpStatus.OK.value(),
                    "Employee updated successfully",
                    update
            );
        }catch (RuntimeException e){
            return new CustomResponse<>(
                    404,
                    e.getMessage()
            );
        }
    }

    @DeleteMapping("/deleteEmployee/{id}")
    public CustomResponse<String> deleteEmployee(@PathVariable Long id) {

       try{
           employeeServices.deleteEmployee(id);
           return new CustomResponse<>(HttpStatus.OK.value(), "Employee deleted successfully");
       }catch (RuntimeException e){
           return new CustomResponse<>(
                   404,
                   e.getMessage()
           );
       }
    }

//    @GetMapping("/getEmplId/{id}")
//    public EmployeeDTO getEmployeeById(@PathVariable Long id) {
//        EmployeeDTO employee = employeeServices.getEmployeeById(id);
//        return employee;
//    }
@GetMapping("/getEmplId/{id}")
public Object getEmployeeById(@PathVariable Long id) {

    try {
        return employeeServices.getEmployeeById(id);

    } catch (RuntimeException e) {

        return new CustomResponse<>(
                404,
                e.getMessage()
        );
    }
}
}
