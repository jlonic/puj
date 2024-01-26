package com.example.lab6.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException e){
        HttpStatus status = e.getStatus();
        ErrorResponse errorResponse = new ErrorResponse(status.value(), e.getMessage());
        return new ResponseEntity<>(errorResponse, status);
    }
}
