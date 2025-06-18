package com.ps.assignment.employeeManagement.dto;

public class GenericResult {

    private Boolean success;
    private String message;
    private Object data;
    private String status = "SUCCESS";

    public GenericResult() {
    }
    public GenericResult(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }
    public Boolean getSuccess() {
        return success;
    }
    public void setSuccess(Boolean success) {
        this.success = success;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    
}
