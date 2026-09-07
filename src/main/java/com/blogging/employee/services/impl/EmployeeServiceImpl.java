package com.blogging.employee.services.impl;

import com.blogging.employee.dto.EmployeeDTO;
import com.blogging.employee.entities.Employee;
import com.blogging.employee.repositiories.EmployeeRepo;
import com.blogging.employee.services.EmployeeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeServices {

    @Autowired
    public EmployeeRepo employeeRepo;

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        Employee employee = this.dtoToEmployee(employeeDTO);
        Employee savedEmployee =this.employeeRepo.save(employee);
        return this.employeeToDTO(savedEmployee);
    }

    @Override
    public List<EmployeeDTO> getAllEmployee() {
        List<Employee> employees =this.employeeRepo.findAll();
        List<EmployeeDTO> employeeDTO = employees.stream().map(employee ->
                this.employeeToDTO(employee)).collect(Collectors.toList());
        return employeeDTO;
    }

    @Override
    public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO, Integer empID) {
        Employee employee = this.employeeRepo.findById(empID).orElseThrow(()->
                new RuntimeException("EmpID not found"));

        employee.setEmpName(employeeDTO.getEmpName());
        employee.setAge(employeeDTO.getAge());
        employee.setDepartment(employee.getDepartment());
        employee.setSalary(employeeDTO.getSalary());

        Employee updateEmployee = this.employeeRepo.save(employee);
        return this.employeeToDTO(updateEmployee);

    }

    @Override
    public void deleteEmployee(Integer empID) {
        Employee employee = this.employeeRepo.findById(empID).orElseThrow(()-> new RuntimeException("EmpID not found"));
        this.employeeRepo.delete(employee);
    }

    @Override
    public Employee getAllEmployees() {
        return null;
    }


    public Employee dtoToEmployee(EmployeeDTO employeeDTO){
        Employee employee = new Employee();
//        employee.setEmpID(employeeDTO.getEmpID());
        employee.setEmpName(employeeDTO.getEmpName());
        employee.setAge(employeeDTO.getAge());
        employee.setDepartment(employeeDTO.getDepartment());
        employee.setSalary(employeeDTO.getSalary());

        return employee;
    }

    public EmployeeDTO employeeToDTO(Employee employee){
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setEmpID(employee.getEmpID());
        employeeDTO.setEmpName(employee.getEmpName());
        employeeDTO.setAge(employee.getAge());
        employeeDTO.setDepartment(employee.getDepartment());
        employeeDTO.setSalary(employee.getSalary());

        return employeeDTO;
    }
}
