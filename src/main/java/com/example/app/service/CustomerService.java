package com.example.app.service;


import com.example.app.dto.CustomerDTO;
import com.example.app.exception.CustomerNotFoundException;
import com.example.app.resources.Customer;

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

    /**
     * Inserts a new customer into the repository and converts the saved entity to a DTO.
     *
     * @param customer the customer entity to be saved.
     * @return the DTO representation of the saved customer.
     */
    CustomerDTO insertNewCustomer(Customer customer);
}
