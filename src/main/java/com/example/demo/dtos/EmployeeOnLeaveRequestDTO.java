package com.example.demo.dtos;

import java.time.Instant;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EmployeeOnLeaveRequestDTO {
	private String employeeId;
	private Instant startDate;
	private Instant endDate;
}
