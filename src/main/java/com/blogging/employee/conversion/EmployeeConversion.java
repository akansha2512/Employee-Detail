package com.blogging.employee.conversion;

import com.blogging.employee.dto.EmployeeDTO;
import com.blogging.employee.entities.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeConversion {

    public Employee dtoToEmployee(EmployeeDTO employeeDTO){
        Employee employee = new Employee();
//        employee.setEmpID(employeeDTO.getEmpID());
        employee.setName(employeeDTO.getName());
        employee.setAge(employeeDTO.getAge());
        employee.setDepartment(employeeDTO.getDepartment());
        employee.setSalary(employeeDTO.getSalary());
        employee.setAddress(employeeDTO.getAddress());
        return employee;
    }

    public EmployeeDTO employeeToDTO(Employee employee){
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setName(employee.getName());
        employeeDTO.setAge(employee.getAge());
        employeeDTO.setDepartment(employee.getDepartment());
        employeeDTO.setSalary(employee.getSalary());
        employeeDTO.setAddress(employee.getAddress());
        return employeeDTO;
    }
}
