package com.example.app.service;

import com.example.app.dao.CustomerRepository;
import com.example.app.dto.CustomerDTO;
import com.example.app.exception.CustomerNotFoundException;
import com.example.app.mapper.CustomerMapper;
import com.example.app.resources.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


/**
 * The Service layer for managing customers.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    /**
     * Retrieves all customers from the repository and converts them to DTOs.
     *
     * @return a list of all customer DTOs.
     */
    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll().stream().map(CustomerMapper::toCustomerDTO).collect(Collectors.toList());
    }

    /**
     * Retrieves a customer by their ID and converts it to a DTO.
     *
     * @param id the ID of the customer to retrieve.
     * @return the customer DTO with the given ID.
     * @throws CustomerNotFoundException if no customer is found with the given ID.
     */
    @Override
    public CustomerDTO getCustomerById(Integer id) throws CustomerNotFoundException {
        return customerRepository.findById(id).map(CustomerMapper::toCustomerDTO)
                .orElseThrow(()-> new CustomerNotFoundException(
                        String.format("There is no customer with the id : %d", id)));
    }

}

