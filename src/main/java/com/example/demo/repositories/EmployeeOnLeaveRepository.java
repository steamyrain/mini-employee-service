package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.EmployeeOnLeave;

@Repository
public interface EmployeeOnLeaveRepository extends JpaRepository<EmployeeOnLeave, String> {

	List<EmployeeOnLeave> findAllByEmployeeIdOrderByRequestDateDesc(String employeeId);
}
