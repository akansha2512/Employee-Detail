package com.blogging.employee.services;

import com.blogging.employee.dto.EmployeeDTO;
import com.blogging.employee.entities.Employee;

import java.util.List;


public interface EmployeeServices {

    EmployeeDTO createEmployee(EmployeeDTO employeeDTO);

    List<EmployeeDTO> getAllEmployee();

    EmployeeDTO updateEmployee(EmployeeDTO employeeDTO, Integer empID);

    void deleteEmployee(Integer empId);

    Employee getAllEmployees();
}
