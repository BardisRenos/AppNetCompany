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
 * The Service layer of User
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;


    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll().stream().map(CustomerMapper::toCustomerDTO).collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerById(Integer id) throws CustomerNotFoundException {

        return customerRepository.findById(id).map(CustomerMapper::toCustomerDTO)
                .orElseThrow(()-> new CustomerNotFoundException(
                        String.format("There is no customer with the id : %d", id)));
    }

}

