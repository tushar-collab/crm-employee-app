package com.ps.assignment.employeeManagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ps.assignment.employeeManagement.dto.ErrorResponse;

@ControllerAdvice
public class GlobalExpcetionHandler {

	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleExpection (Exception exc){
		ErrorResponse error = new ErrorResponse();
		
		error.setSuccess(Boolean.FALSE);
		error.setMessage(exc.getMessage());
		error.setStatus(HttpStatus.BAD_REQUEST);
		error.setTimeSTamp(System.currentTimeMillis());
		
		return new ResponseEntity<ErrorResponse>(error,HttpStatus.BAD_REQUEST);
	}
	
	
}
