package com.project.mynet.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(NotFoundCustomException.class)
    public ResponseEntity<Object> handleSupplierNotFoundException(NotFoundCustomException ex, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(
                ex.getMessage(),
                ex.getStatusCode()
        );
        return new ResponseEntity<>(errorMessage, HttpStatus.valueOf(ex.getStatusCode()));
    }
    static class ErrorMessage {
        private String message;
        private int statusCode;

        public ErrorMessage(String message, int statusCode) {
            this.message = message;
            this.statusCode = statusCode;
        }

        public int getStatusCode() {
            return statusCode;
        }



        public String getMessage() {
            return message;
        }
    }
}
