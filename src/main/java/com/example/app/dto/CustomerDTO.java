package com.example.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * Data Transfer Object for Customer.
 */
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class CustomerDTO implements Serializable {

    /**
     * The unique identifier for a customer.
     */
    private Integer customerId;

    /**
     * The name of the customer.
     */
    private String name;

    /**
     * The surname of the customer.
     */
    private String surname;

    /**
     * The balance of the customer.
     */
    private Integer balance;
}
