package com.example.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

/**
 * Exception thrown when a customer is not found.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CustomerNotFoundException extends Exception {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new CustomerNotFoundException with the specified detail message.
     *
     * @param message the detail message.
     */
    public CustomerNotFoundException(String message) {
        super(message);
    }
}
