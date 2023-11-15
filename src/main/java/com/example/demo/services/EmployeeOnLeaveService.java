package com.example.demo.services;

import java.time.Instant;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.demo.commons.exceptions.BusinessException;
import com.example.demo.dtos.EmployeeOnLeaveRequestDTO;
import com.example.demo.dtos.IEmployeeProjection;
import com.example.demo.entities.Employee;
import com.example.demo.entities.EmployeeOnLeave;
import com.example.demo.entities.MessageTemplate;
import com.example.demo.repositories.EmployeeOnLeaveRepository;
import com.example.demo.repositories.EmployeeRepository;
import com.example.demo.repositories.MessageTemplateRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmployeeOnLeaveService {

	private final EmployeeRepository employeeRepository;
	private final EmployeeOnLeaveRepository employeeOnLeaveRepository;
	private final MessageTemplateRepository messageTemplateRepository;

	@Autowired
	public EmployeeOnLeaveService(EmployeeRepository empR, EmployeeOnLeaveRepository empOLR, MessageTemplateRepository msgTmpltR) {
		this.employeeRepository = empR;
		this.employeeOnLeaveRepository = empOLR;
		this.messageTemplateRepository = msgTmpltR;
	}

	@Transactional(rollbackOn = {BusinessException.class})
	public void requestOnLeave(EmployeeOnLeaveRequestDTO requestDTO) throws BusinessException {
		String requestEmpId = requestDTO.getEmployeeId();
		Optional<Employee> empOpt = employeeRepository.findById(requestEmpId);
		Employee emp = empOpt.orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND,"Employee id: "+requestEmpId+" not found"));
		EmployeeOnLeave empOL = new EmployeeOnLeave();
		empOL.setEmployee(emp);
		empOL.setStartDate(requestDTO.getStartDate());
		empOL.setEndDate(requestDTO.getEndDate());
		empOL.setRequestDate(Instant.now());
		employeeOnLeaveRepository.save(empOL);
	}

	public List<EmployeeOnLeave> getAllByEmployeeId(String id) {
		return employeeOnLeaveRepository.findAllByEmployeeIdOrderByRequestDateDesc(id);
	}

	@Transactional(rollbackOn = {BusinessException.class})
	public Map<String,String> notifyManager(String requestId) throws BusinessException {
		final List<String> messageKeys = List.of("[employeeName]","[startDate]","[endDate]");
		Optional<EmployeeOnLeave> empOLOpt = employeeOnLeaveRepository.findById(requestId); 
		EmployeeOnLeave empOL = empOLOpt.orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND, "request leave with id: "+requestId+" not found"));
		Employee empOLEmp = empOL.getEmployee();
		List<IEmployeeProjection> managers = employeeRepository.findAllManagerRcv(empOLEmp.getId());
		if (managers.isEmpty()) {
			throw new BusinessException(HttpStatus.NOT_FOUND, "employee by id: "+empOLEmp.getId()+" doesn't have manager");
		}
		List<String> emails = managers.parallelStream().map(m -> m.getEmail()).toList();
		Optional<MessageTemplate> msgTmpOpt = messageTemplateRepository.findByName("LEAVE");
		MessageTemplate msgTmplt = msgTmpOpt.orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND, "message template LEAVE not found"));
		String template = msgTmplt.getTemplate();
		Map<String, String> msgKeyValMap = new HashMap<>();
		msgKeyValMap.put("[employeeName]", empOLEmp.getName());
		msgKeyValMap.put("[startDate]", empOL.getStartDate().atZone(ZoneId.of("Asia/Jakarta")).toLocalDate().toString());
		msgKeyValMap.put("[endDate]", empOL.getEndDate().atZone(ZoneId.of("Asia/Jakarta")).toLocalDate().toString());
		for (String mk : messageKeys) {
			template = template.replace(mk, msgKeyValMap.get(mk));
		}
		Map<String, String> resultMap = Map.of("messageName",msgTmplt.getName(),"subject",emails.toString(),"message",template);
		return resultMap;
	}
}
