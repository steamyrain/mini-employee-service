package com.example.demo.controllers.advices;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.commons.exceptions.BusinessException;
import com.example.demo.commons.exceptions.NotFoundException;
import com.example.demo.dtos.ErrorMessage;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class RestControllerExceptionAdvice {

	@ExceptionHandler(value = {NotFoundException.class})
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ErrorMessage notFoundExceptionHandler(RuntimeException ex) {
		log.error(ex.getMessage());
		return new ErrorMessage(HttpStatus.NOT_FOUND.toString(), ex.getMessage());
	}

	@ExceptionHandler(value = {BusinessException.class})
	public ResponseEntity<Object> BusinessExceptionHandler(BusinessException ex) {
		log.error(ex.getMessage());
		return ResponseEntity.status(ex.getHTTPStatus()).body(new ErrorMessage(ex.getHTTPStatus().toString(), ex.getMessage()));
	}
}
