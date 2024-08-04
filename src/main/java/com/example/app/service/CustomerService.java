package com.example.app.service;


import com.example.app.dto.CustomerDTO;
import com.example.app.exception.CustomerNotFoundException;

import java.util.List;

/**
 * Service interface for managing customers.
 */
public interface CustomerService {

    /**
     * Retrieves all customers.
     *
     * @return a list of all customer DTOs.
     */
    List<CustomerDTO> getAllCustomers();

    /**
     * Retrieves a customer by their ID.
     *
     * @param id the ID of the customer to retrieve.
     * @return the customer DTO with the given ID.
     * @throws CustomerNotFoundException if no customer is found with the given ID.
     */
    CustomerDTO getCustomerById(Integer id) throws CustomerNotFoundException;
}
