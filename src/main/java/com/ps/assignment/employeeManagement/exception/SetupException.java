package com.ps.assignment.employeeManagement.exception;

public class SetupException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public SetupException() {
        super();
    }

    public SetupException(String message) {
        super(message);
    }

    public SetupException(String message, Throwable cause) {
        super(message, cause);
    }

    public SetupException(Throwable cause) {
        super(cause);
    }
    
}
