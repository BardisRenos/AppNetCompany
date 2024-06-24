package com.example.app.service;


import com.example.app.dto.CustomerDTO;
import com.example.app.exception.CustomerNotFoundException;

import java.util.List;

public interface CustomerService {
    List<CustomerDTO> getAllCustomers();
    CustomerDTO getCustomerById(Integer id) throws CustomerNotFoundException;
}
