package com.project.ECommerceApplication.controller;

import com.project.ECommerceApplication.exception.ResourceNotFoundException;
import com.project.ECommerceApplication.exception.ValidationException;
import com.project.ECommerceApplication.response.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON).body(new ApiError(
                "Resource Not Found",
                ex.getMessage(),
                LocalDateTime.now()
        ));
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ApiError> handleValidationException(ValidationException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON).body(new ApiError(
                "Validation Error",
                ex.getMessage(),
                LocalDateTime.now()
        ));

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGenericException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).body(new ApiError(
                "Internal Server Error",
                ex.getMessage(),
                LocalDateTime.now()
        ));

    }
}
