package com.blogging.employee.services;

import com.blogging.employee.dto.EmployeeDTO;
import com.blogging.employee.entities.Employee;

import java.util.List;


public interface EmployeeServices {

    EmployeeDTO createEmployee(EmployeeDTO employeeDTO);

    List<EmployeeDTO> createMultiEmpl(List<EmployeeDTO>  employeeDTO);

    List<EmployeeDTO> getAllEmployee();

    EmployeeDTO updateEmployee(EmployeeDTO employeeDTO, Long id);

    void deleteEmployee(Long id);

    EmployeeDTO getEmployeeById(Long id);
}
