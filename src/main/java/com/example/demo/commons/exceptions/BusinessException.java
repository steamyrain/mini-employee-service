package com.example.demo.commons.exceptions;

import org.springframework.http.HttpStatus;

public class BusinessException extends Exception {

	private HttpStatus httpStatus;
	private String errorMessage;

	public BusinessException(HttpStatus httpStatus, String errorMessage) {
		this.errorMessage = errorMessage;
		this.httpStatus = httpStatus;
	}

	public BusinessException(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public HttpStatus getHTTPStatus() {
		return this.httpStatus;
	}

	public String getErrorMessage() {
		return this.errorMessage;
	}
}
