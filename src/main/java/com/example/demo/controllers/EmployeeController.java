package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.services.EmployeeService;

@RestController
@RequestMapping("/api/employee") 
public class EmployeeController {

	private final EmployeeService employeeService;

	@Autowired
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@GetMapping()
	public ResponseEntity<Object> getById(@RequestParam(name = "id")String id) {
		return ResponseEntity.ok(employeeService.getById(id));
	}
	
	@GetMapping("/find-all-managers")
	public ResponseEntity<Object> getManagersOfEmployeeId(@RequestParam(name = "id")String id) {
		return ResponseEntity.ok(employeeService.getManagersOfEmployeeId(id));
	}
}
