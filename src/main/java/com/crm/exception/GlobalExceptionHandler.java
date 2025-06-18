package com.crm.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.crm.dto.ResponseDto;
import com.crm.entity.ErrorResponseDto;

import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(
            ResourceNotFoundException exception,
            WebRequest webRequest) {
        ErrorResponseDto errorResponseDTO = new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.NOT_FOUND,
                exception.getMessage(),
                LocalDateTime.now());
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.NOT_FOUND);
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
                        false, 0,
                        errorMessage.toString().trim(),
                        "BAD REQUEST", 0));
    }

    @ExceptionHandler(JpaObjectRetrievalFailureException.class)
    public ResponseEntity<ResponseDto> handleJpaObjectRetrievalFailureExceptionExceptions(JpaObjectRetrievalFailureException ex) {
        StringBuilder errorMessage = new StringBuilder();
        errorMessage.append("An error occurred while retrieving the object: ")
                .append(ex.getMessage());
        return ResponseEntity.badRequest()
                .body(new ResponseDto(
                        false, 0,
                        errorMessage.toString().trim(),
                        "BAD REQUEST", 0));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ResponseDto> handleRuntimeExceptions(RuntimeException ex) {
        StringBuilder errorMessage = new StringBuilder();
        errorMessage.append("An error occurred while retrieving the object: ")
                .append(ex.getMessage());
        return ResponseEntity.badRequest()
                .body(new ResponseDto(
                        false, 0,
                        errorMessage.toString().trim(),
                        "BAD REQUEST", 0));
    }

}
