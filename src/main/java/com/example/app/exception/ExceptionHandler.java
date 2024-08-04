package com.example.app.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Global exception handler for the application.
 */
@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handles CustomerNotFoundException and returns a 404 Not Found response.
     *
     * @param ex the exception to handle.
     * @return a ResponseEntity containing the exception message and a 404 status code.
     */
    @org.springframework.web.bind.annotation.ExceptionHandler({CustomerNotFoundException.class})
    public ResponseEntity<Object> handleCustomerNotFound(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND);
    }
}
