package com.ps.assignment.employeeManagement.dto;

import org.springframework.http.HttpStatus;

public class ErrorResponse {
    
    
	private HttpStatus status;
	
	private String message;
	
	private Long timeSTamp = System.currentTimeMillis();
    
    private Boolean success = Boolean.FALSE;

    public ErrorResponse() {
    }

    public ErrorResponse(HttpStatus status, String message, Long timeSTamp, Boolean success) {
        this.status = status;
        this.message = message;
        this.timeSTamp = timeSTamp;
        this.success = success;
    }


    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTimeSTamp() {
        return timeSTamp;
    }

    public void setTimeSTamp(Long timeSTamp) {
        this.timeSTamp = timeSTamp;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "ErrorResponse [status=" + status + ", message=" + message + ", timeSTamp=" + timeSTamp + ", success="
                + success + "]";
    }

}
