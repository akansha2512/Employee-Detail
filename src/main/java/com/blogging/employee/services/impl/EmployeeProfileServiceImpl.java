package com.blogging.employee.services.impl;

import com.blogging.employee.conversion.EmployeeConversion;
import com.blogging.employee.dto.EmployeeProfileDTO;
import com.blogging.employee.entities.Employee;
import com.blogging.employee.entities.EmployeeProfile;
import com.blogging.employee.repositiories.EmployeeProfileRepo;
import com.blogging.employee.repositiories.EmployeeRepo;
import com.blogging.employee.services.EmployeeProfileServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class EmployeeProfileServiceImpl implements EmployeeProfileServices {

    public final EmployeeConversion employeeConversion;
    public final EmployeeProfileRepo employeeProfileRepo;
    public final EmployeeRepo employeeRepo;

    @Override
    public EmployeeProfileDTO createEmplProfile(EmployeeProfileDTO employeeProfileDTO, Long empID) {
        EmployeeProfile employeeProfile =
                employeeConversion.dtoToEmplProfile(employeeProfileDTO);

        Employee employee = employeeRepo.findById(empID).orElseThrow(()->
                new RuntimeException("EmpID not found"));

        employeeProfile.setEmployee(employee);

        EmployeeProfile savedEmployee1 = employeeProfileRepo.save(employeeProfile);
        return employeeConversion.employeeProfileToDTO(savedEmployee1);
    }

    @Override
    public List<EmployeeProfileDTO> createMultiEmplProfile(List<EmployeeProfileDTO> listEmplProfileDto) {

        return List.of();
    }

    @Override
    public List<EmployeeProfileDTO> getAllEmplProfile() {
        List<EmployeeProfile> employeeProfiles = employeeProfileRepo.findAll();
        List<EmployeeProfileDTO> employeeProfileDTOS = employeeProfiles.stream().map(employeeProfile ->
                employeeConversion.employeeProfileToDTO(employeeProfile)).collect(Collectors.toList());
        return employeeProfileDTOS;
    }

    @Override
    public EmployeeProfileDTO updateEmplProfile(EmployeeProfileDTO employeeProfileDTO, Long profile_id) {
        EmployeeProfile employeeProfile = employeeProfileRepo.findById(profile_id).orElseThrow(() -> new RuntimeException("Profile Id not found"));
        employeeProfile.setPhone_no(employeeProfileDTO.getPhone_no());
        employeeProfile.setCity(employeeProfileDTO.getCity());
        EmployeeProfile updateEmployeeProfile = employeeProfileRepo.save(employeeProfile);
        return employeeConversion.employeeProfileToDTO(updateEmployeeProfile);
    }

    @Override
    public EmployeeProfileDTO getEmplProfileId(Long profile_id) {
        EmployeeProfile employeeProfile = employeeProfileRepo.findById(profile_id).orElseThrow(() -> new RuntimeException("Profile Id not found"));

        return employeeConversion.employeeProfileToDTO(employeeProfile);
    }

    @Override
    public void deleteEmployee(Long profile_id) {
        EmployeeProfile employeeProfile = employeeProfileRepo.findById(profile_id).orElseThrow(() -> new RuntimeException("Profile Id not found"));
        employeeProfileRepo.delete(employeeProfile);
    }
}
