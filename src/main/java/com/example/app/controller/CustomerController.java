package com.example.app.controller;

import com.example.app.dto.CustomerDTO;
import com.example.app.exception.CustomerNotFoundException;
import com.example.app.service.CustomerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer/")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerServiceImpl userService;

    @GetMapping("all")
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerDTO> getAllCustomers() {
        return userService.getAllCustomers();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDTO insertCustomer(@PathVariable int id) throws CustomerNotFoundException {
        return userService.getCustomerById(id);
    }
}
