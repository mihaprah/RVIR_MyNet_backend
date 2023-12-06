package com.project.mynet.exceptions;


public class NotFoundCustomException  extends RuntimeException{
    private int statusCode;

    public NotFoundCustomException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }
    public int getStatusCode() {
        return statusCode;
    }


}