package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.commons.exceptions.NotFoundException;
import com.example.demo.dtos.IEmployeeProjection;
import com.example.demo.entities.Employee;
import com.example.demo.repositories.EmployeeRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmployeeService {

	private final EmployeeRepository employeeRepository;

	@Autowired
	public EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	public Employee getById(String id) {
		log.info("Getting employee by id: {}", id);
		Optional<Employee> optEmployee = employeeRepository.findById(id);
		return optEmployee.orElseThrow(() -> new NotFoundException("getEmployeeById with id: "+id+" not found"));
	}

	public List<IEmployeeProjection> getManagersOfEmployeeId(String id) {
		log.info("Getting managers of employee id: {}", id);
		return employeeRepository.findAllManagerRcv(id);
	}
}
