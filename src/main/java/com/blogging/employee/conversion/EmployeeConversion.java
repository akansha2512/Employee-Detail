package com.blogging.employee.conversion;

import com.blogging.employee.dto.EmployeeDTO;
import com.blogging.employee.dto.EmployeeProfileDTO;
import com.blogging.employee.entities.Employee;
import com.blogging.employee.entities.EmployeeProfile;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EmployeeConversion {

    public Employee dtoToEmployee(EmployeeDTO employeeDTO){
        Employee employee = new Employee();
        employee.setName(employeeDTO.getName());
        employee.setAge(employeeDTO.getAge());
        employee.setDepartment(employeeDTO.getDepartment());
        employee.setSalary(employeeDTO.getSalary());
        employee.setAddress(employeeDTO.getAddress());

        EmployeeProfile employeeProfile=new EmployeeProfile();
        employeeProfile.setPhone_no(employeeDTO.getEmployeeProfile().getPhone_no());
        employeeProfile.setCity(employeeDTO.getEmployeeProfile().getCity());

        employeeProfile.setEmployee(employee);
        employee.setEmployeeProfile(employeeProfile);



        return employee;
    }

    public EmployeeDTO employeeToDTO(Employee employee) {

        EmployeeDTO employeeDTO = new EmployeeDTO();

        employeeDTO.setId(employee.getId());
        employeeDTO.setName(employee.getName());
        employeeDTO.setAge(employee.getAge());
        employeeDTO.setDepartment(employee.getDepartment());
        employeeDTO.setSalary(employee.getSalary());
        employeeDTO.setAddress(employee.getAddress());

        EmployeeProfileDTO employeeProfileDTO = new EmployeeProfileDTO();

        employeeProfileDTO.setId(employee.getEmployeeProfile().getId());
        employeeProfileDTO.setPhone_no(employee.getEmployeeProfile().getPhone_no());
        employeeProfileDTO.setCity(employee.getEmployeeProfile().getCity());
        employeeProfileDTO.setEmp_id(
                employee.getEmployeeProfile().getEmployee().getId()
        );

        employeeDTO.setEmployeeProfile(employeeProfileDTO);

        return employeeDTO;
    }


    public EmployeeProfile dtoToEmplProfile(EmployeeProfileDTO employeeProfileDTO){
        EmployeeProfile employeeProfile = new EmployeeProfile();
        employeeProfile.setPhone_no(employeeProfileDTO.getPhone_no());
        employeeProfile.setCity(employeeProfileDTO.getCity());
        return employeeProfile;
    }

    public  EmployeeProfileDTO employeeProfileToDTO(EmployeeProfile employeeProfile){
        EmployeeProfileDTO employeeProfileDTO = new EmployeeProfileDTO();
        employeeProfileDTO.setId(employeeProfile.getId());
        employeeProfileDTO.setPhone_no(employeeProfile.getPhone_no());
        employeeProfileDTO.setCity(employeeProfile.getCity());
//        employeeProfileDTO.setEmp_id(employeeProfile.getId());
        employeeProfileDTO.setEmp_id(employeeProfile.getEmployee().getId());
        return employeeProfileDTO;
    }
}
