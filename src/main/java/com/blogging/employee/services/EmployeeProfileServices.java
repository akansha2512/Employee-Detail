package com.blogging.employee.services;

import com.blogging.employee.dto.EmployeeProfileDTO;

import java.util.List;

public interface EmployeeProfileServices {

    EmployeeProfileDTO createEmplProfile(EmployeeProfileDTO employeeProfileDTO,  Long empID);

    List<EmployeeProfileDTO> createMultiEmplProfile(List<EmployeeProfileDTO> listEmplProfileDto);

    List<EmployeeProfileDTO> getAllEmplProfile();

    EmployeeProfileDTO updateEmplProfile(EmployeeProfileDTO employeeProfileDTO, Long profile_id);

    EmployeeProfileDTO getEmplProfileId(Long profile_id);

    void deleteEmployee(Long profile_id);
}
