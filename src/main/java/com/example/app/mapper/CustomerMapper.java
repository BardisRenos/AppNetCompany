package com.example.app.mapper;

import com.example.app.dto.CustomerDTO;
import com.example.app.resources.Customer;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

/**
 * Service class for mapping Customer entities to CustomerDTOs.
 */
@Service
public class CustomerMapper {

    /**
     * Converts a Customer entity to a CustomerDTO.
     *
     * @param customer the Customer entity to convert.
     * @return the converted CustomerDTO.
     */
    public static CustomerDTO toCustomerDTO(Customer customer) {
        return new ModelMapper().map(customer, CustomerDTO.class);
    }
}
