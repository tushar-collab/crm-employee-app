package com.crm.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.crm.dto.ResponseDto;

import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {

        @ExceptionHandler(ResourceNotFoundException.class)
        public ResponseEntity<ResponseDto> handleResourceNotFoundException(
                        ResourceNotFoundException exception,
                        WebRequest webRequest) {
                ResponseDto responseDto = new ResponseDto(false, new Date(), 0, exception.getMessage(),
                                HttpStatus.NOT_FOUND.toString(), null);
                return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
        }

        @ExceptionHandler(ConstraintViolationException.class)
        public ResponseEntity<ResponseDto> handleValidationExceptions(ConstraintViolationException ex) {
                StringBuilder errorMessage = new StringBuilder();
                ex.getConstraintViolations().forEach(violation -> {
                        errorMessage.append(violation.getMessage())
                                        .append("; ");
                });

                return ResponseEntity.badRequest()
                                .body(new ResponseDto(
                                                false, new Date(), 0,
                                                errorMessage.toString().trim(),
                                                HttpStatus.FAILED_DEPENDENCY.toString(), 0));
        }

        @ExceptionHandler(JpaObjectRetrievalFailureException.class)
        public ResponseEntity<ResponseDto> handleJpaObjectRetrievalFailureExceptionExceptions(
                        JpaObjectRetrievalFailureException ex) {
                StringBuilder errorMessage = new StringBuilder();
                errorMessage.append("An error occurred while retrieving the object: ")
                                .append(ex.getMessage());
                return ResponseEntity.badRequest()
                                .body(new ResponseDto(
                                                false, new Date(), 0,
                                                errorMessage.toString().trim(),
                                                HttpStatus.INTERNAL_SERVER_ERROR.toString(), 0));
        }

        @ExceptionHandler(RuntimeException.class)
        public ResponseEntity<ResponseDto> handleRuntimeExceptions(RuntimeException ex) {
                StringBuilder errorMessage = new StringBuilder();
                errorMessage.append("An error occurred while retrieving the object: ")
                                .append(ex.getMessage());
                return ResponseEntity.badRequest()
                                .body(new ResponseDto(
                                                false, new Date(), 0,
                                                errorMessage.toString().trim(),
                                                HttpStatus.INTERNAL_SERVER_ERROR.toString(), 0));
        }

        @ExceptionHandler(Exception.class)
        public ResponseEntity<ResponseDto> handleException(Exception ex) {
                return ResponseEntity.badRequest()
                                .body(new ResponseDto(
                                                false, new Date(), 0,
                                                ex.getMessage(),
                                                HttpStatus.BAD_REQUEST.toString(), 0));
        }

}
