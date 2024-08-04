package com.example.app.controller;

import com.example.app.dto.CustomerDTO;
import com.example.app.exception.CustomerNotFoundException;
import com.example.app.resources.Customer;
import com.example.app.service.CustomerServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing customers.
 */
@RestController
@RequestMapping("/api/v1/customer/")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerServiceImpl userService;

    /**
     * Retrieves all customers.
     *
     * @return a list of all customers.
     */
    @GetMapping("all")
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerDTO> getAllCustomers() {
        return userService.getAllCustomers();
    }

    /**
     * Retrieves a customer by their ID.
     *
     * @param id the ID of the customer to retrieve.
     * @return the customer with the given ID.
     * @throws CustomerNotFoundException if no customer is found with the given ID.
     */
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO getCustomer(@PathVariable int id) throws CustomerNotFoundException {
        return userService.getCustomerById(id);
    }

    /**
     * Inserts a new customer.
     *
     * @param customer the customer entity to insert.
     * @return the inserted customer as a DTO.
     */
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDTO insertCustomer(@Valid @RequestBody Customer customer) {
        return userService.insertNewCustomer(customer);
    }

}
