package com.blogging.employee.services.impl;

import com.blogging.employee.conversion.EmployeeConversion;
import com.blogging.employee.dto.EmployeeDTO;
import com.blogging.employee.entities.Employee;
import com.blogging.employee.entities.EmployeeProfile;
import com.blogging.employee.repositiories.EmployeeProfileRepo;
import com.blogging.employee.repositiories.EmployeeRepo;
import com.blogging.employee.services.EmployeeServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeServices {

    public final EmployeeRepo employeeRepo;
    public final EmployeeProfileRepo employeeProfileRepo;
    public final EmployeeConversion employeeConversion;

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        Employee employee = employeeConversion.dtoToEmployee(employeeDTO);
        Employee savedEmployee = employeeRepo.save(employee);

        return employeeConversion.employeeToDTO(savedEmployee);
    }

    @Override
    public List<EmployeeDTO> createMultiEmpl(List<EmployeeDTO> listEmployeeDTO) {
        List<Employee> employees = listEmployeeDTO.stream()
                .map(employeeDTO -> employeeConversion.dtoToEmployee(employeeDTO))
                .collect(Collectors.toList());
        List<Employee> savedEmployee = employeeRepo.saveAll(employees);

        List<EmployeeDTO> employeeDto = savedEmployee.stream()
                .map(employee -> employeeConversion.employeeToDTO(employee)).
                collect(Collectors.toList());

        return employeeDto;
    }

    @Override
    public List<EmployeeDTO> getAllEmployee() {
        List<Employee> employees = employeeRepo.findAll();
        List<EmployeeDTO> employeeDTO = employees.stream().map(employee ->
                employeeConversion.employeeToDTO(employee)).collect(Collectors.toList());

        return employeeDTO;
    }

    @Override
    public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO, Long id) {
        Employee employee = employeeRepo.findById(id).orElseThrow(() -> new RuntimeException("Employee Id is not found"));

        employee.setName(employeeDTO.getName());
        employee.setAge(employeeDTO.getAge());
        employee.setDepartment(employeeDTO.getDepartment());
        employee.setSalary(employeeDTO.getSalary());
        employee.setAddress(employeeDTO.getAddress());

        EmployeeProfile profile = employee.getEmployeeProfile();
        profile.setPhone_no(employeeDTO.getEmployeeProfile().getPhone_no());
        profile.setCity(employeeDTO.getEmployeeProfile().getCity());

        Employee updateEmployee = employeeRepo.save(employee);
        return employeeConversion.employeeToDTO(updateEmployee);

    }

    @Override
    public void deleteEmployee(Long id) {
        Employee employee = employeeRepo.findById(id).orElseThrow(() -> new RuntimeException("Employee Id is not found"));
        employeeRepo.delete(employee);
    }



//    public EmployeeDTO getEmployeeById(Long id) {
//
//        return employeeRepo.findById(id)
//                .map(employeeConversion::employeeToDTO)
//                .orElseThrow(() ->
//                        new RuntimeException("Employee id " + id + " does not exist")
//                );
//    }

    public EmployeeDTO getEmployeeById(Long id) {

        return employeeRepo.findById(id)
                .map(employeeConversion::employeeToDTO)
                .orElseThrow(() ->
                        new RuntimeException("Employee id " + id + " does not exist")
                );
    }
}
