package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.commons.exceptions.BusinessException;
import com.example.demo.dtos.EmployeeOnLeaveRequestDTO;
import com.example.demo.services.EmployeeOnLeaveService;

@RestController
@RequestMapping("/api/employee-on-leave")
public class EmployeeOnLeaveController {
	private final EmployeeOnLeaveService employeeOnLeaveService;

	@Autowired
	public EmployeeOnLeaveController(EmployeeOnLeaveService empOlS) {
		this.employeeOnLeaveService = empOlS;
	}

	@PostMapping
	public ResponseEntity<Object> requestOnLeave(@RequestBody EmployeeOnLeaveRequestDTO request) throws BusinessException {
		employeeOnLeaveService.requestOnLeave(request);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/requested-by/{employeeId}")
	public ResponseEntity<Object> getAllRequestByEmployeeId(@PathVariable(name = "employeeId") String employeeId) {
		return ResponseEntity.ok(employeeOnLeaveService.getAllByEmployeeId(employeeId));
	}

	@GetMapping("/notify-managers/{requestId}")
	public ResponseEntity<Object> notifyManagers(@PathVariable(name = "requestId") String requestId) throws BusinessException {
		return ResponseEntity.ok(employeeOnLeaveService.notifyManager(requestId));
	}
}
