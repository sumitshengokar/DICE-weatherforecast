package com.example.dice.weatherforecast.entity;

public class ErrorEntity {
    private String message;
    private String errorCode;
    private String statusCode;

    public ErrorEntity(String message, String errorCode, String statusCode) {
        this.message = message;
        this.errorCode = errorCode;
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public String toString() {
        return "ErrorEntity{" +
                "message='" + message + '\'' +
                ", errorCode='" + errorCode + '\'' +
                ", statusCode='" + statusCode + '\'' +
                '}';
    }
}
