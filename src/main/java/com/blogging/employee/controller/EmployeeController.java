package com.blogging.employee.controller;


import com.blogging.employee.dto.EmployeeDTO;
import com.blogging.employee.entities.Employee;
import com.blogging.employee.services.EmployeeServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor
public class EmployeeController {

//    @Autowired
    public final EmployeeServices employeeServices;

//    public EmployeeController(EmployeeServices employeeServices) {
//        this.employeeServices = employeeServices;
//    }

    @PostMapping("/create")
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO employeeDTO){
        System.out.println("create api executed");
        log.info("create api ocntroller is executed");
        EmployeeDTO create =employeeServices.createEmployee(employeeDTO);
        return new ResponseEntity<>(create, HttpStatus.CREATED);
    }

    @GetMapping("/getAllEmployee")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployee(){
        return ResponseEntity.ok(this.employeeServices.getAllEmployee());
    }

//    @GetMapping("/")
//    public String getAllEmployees(){
//        Employee emp= employeeServices.getAllEmployees();
//        return ("list of records printed" +emp);
//    }

    @PutMapping("/updateEmployee/{empID}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@RequestBody EmployeeDTO employeeDTO, @PathVariable Integer empID){
        EmployeeDTO update = this.employeeServices.updateEmployee(employeeDTO, empID);
        return ResponseEntity.ok(update);

    }

    @DeleteMapping("/deleteEmployee/{empID}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Integer empID){
        this.employeeServices.deleteEmployee(empID);
        return new ResponseEntity<>("Employee Deleted successfuly ", HttpStatus.OK);
    }


}
