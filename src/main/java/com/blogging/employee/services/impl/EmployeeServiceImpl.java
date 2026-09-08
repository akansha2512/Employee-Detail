package com.blogging.employee.services.impl;

import com.blogging.employee.conversion.EmployeeConversion;
import com.blogging.employee.dto.EmployeeDTO;
import com.blogging.employee.entities.Employee;
import com.blogging.employee.repositiories.EmployeeRepo;
import com.blogging.employee.services.EmployeeServices;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeServices {

//    @Autowired
    public final EmployeeRepo employeeRepo;

    public final EmployeeConversion employeeConversion;

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        Employee employee = employeeConversion.dtoToEmployee(employeeDTO);
        Employee savedEmployee =this.employeeRepo.save(employee);
        return employeeConversion.employeeToDTO(savedEmployee);
    }

    @Override
    public List<EmployeeDTO> getAllEmployee() {
        List<Employee> employees =this.employeeRepo.findAll();
        List<EmployeeDTO> employeeDTO = employees.stream().map(employee ->
                employeeConversion.employeeToDTO(employee)).collect(Collectors.toList());
        return employeeDTO;
    }

    @Override
    public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO, Integer empID) {
        Employee employee = this.employeeRepo.findById(empID).orElseThrow(()->
                new RuntimeException("EmpID not found"));

        employee.setName(employeeDTO.getName());
        employee.setAge(employeeDTO.getAge());
        employee.setDepartment(employee.getDepartment());
        employee.setSalary(employeeDTO.getSalary());
        employee.setAddress(employeeDTO.getAddress());
        Employee updateEmployee = this.employeeRepo.save(employee);
        return employeeConversion.employeeToDTO(updateEmployee);

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

    @Override
    public EmployeeDTO getEmployeeById(Integer id) {
        Employee employee = this.employeeRepo.findById(id).orElseThrow(() -> new RuntimeException("Emp Id is not found"));
        return employeeConversion.employeeToDTO(employee);
    }


}
