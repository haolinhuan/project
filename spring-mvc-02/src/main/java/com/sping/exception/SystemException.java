package com.sping.exception;

public class SystemException extends Exception{

    private String message;

    public SystemException(String message) {
        this.message=message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
