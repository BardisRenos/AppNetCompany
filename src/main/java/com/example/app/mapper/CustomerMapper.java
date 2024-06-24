package com.example.app.mapper;

import com.example.app.dto.CustomerDTO;
import com.example.app.resources.Customer;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {

    public static CustomerDTO toCustomerDTO(Customer customer) {
        return new ModelMapper().map(customer, CustomerDTO.class);
    }
}
