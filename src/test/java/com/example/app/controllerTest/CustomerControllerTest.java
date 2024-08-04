package com.example.app.controllerTest;

import com.example.app.controller.CustomerController;
import com.example.app.dto.CustomerDTO;
import com.example.app.exception.CustomerNotFoundException;
import com.example.app.resources.Customer;
import com.example.app.service.CustomerServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@EnableWebMvc
public class CustomerControllerTest {

    @Mock
    private CustomerServiceImpl userService;

    @InjectMocks
    private CustomerController customerController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testGetAllCustomers() throws Exception {
        // Arrange
        CustomerDTO customer1 = new CustomerDTO(1, "Renos", "Bardis", 100);
        CustomerDTO customer2 = new CustomerDTO(2, "John", "Doe", 50);
        when(userService.getAllCustomers()).thenReturn(Arrays.asList(customer1, customer2));

        // Act & Assert
        mockMvc.perform(get("/api/v1/customer/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].customerId", is(1)))
                .andExpect(jsonPath("$[0].name", is("Renos")))
                .andExpect(jsonPath("$[1].customerId", is(2)))
                .andExpect(jsonPath("$[1].name", is("John")));
    }

    @Test
    void testGetCustomerById_Success() throws Exception {
        // Arrange
        CustomerDTO customer = new CustomerDTO(1, "Renos", "Bardis", 100);
        when(userService.getCustomerById(1)).thenReturn(customer);

        // Act & Assert
        mockMvc.perform(get("/api/v1/customer/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.customerId", is(1)))
                .andExpect(jsonPath("$.name", is("Renos")));
    }

    @Test
    void testGetCustomerById_NotFound() throws Exception {
        // Arrange
        when(userService.getCustomerById(1)).thenThrow(new CustomerNotFoundException("There is no customer with the id: 1"));

        // Act & Assert
        mockMvc.perform(get("/api/v1/customer/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testInsertCustomer() throws Exception {
        // Arrange
        Customer customer = new Customer(1, "Renos", "Bardis", 100);
        CustomerDTO customerDTO = new CustomerDTO(1, "Renos", "Bardis", 100);
        when(userService.insertNewCustomer(any(Customer.class))).thenReturn(customerDTO);

        // Act & Assert
        mockMvc.perform(post("/api/v1/customer/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.customerId", is(1)))
                .andExpect(jsonPath("$.name", is("Renos")));
    }

}
