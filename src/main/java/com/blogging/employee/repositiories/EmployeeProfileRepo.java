package com.blogging.employee.repositiories;


import com.blogging.employee.entities.EmployeeProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeProfileRepo extends JpaRepository<EmployeeProfile, Long> {
}
