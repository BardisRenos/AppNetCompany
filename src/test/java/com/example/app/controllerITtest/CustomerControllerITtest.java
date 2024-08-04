package com.example.app.controllerITtest;

import com.example.app.dao.CustomerRepository;
import com.example.app.resources.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Testcontainers
@SpringBootTest
@AutoConfigureMockMvc
@Import(TestContainerConfig.class)
public class CustomerControllerITtest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private CustomerRepository customerRepository;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        Customer customer1 = new Customer(1, "Renos", "Bardis", 100);
        Customer customer2 = new Customer(2, "John", "Doe", 50);

        customerRepository.save(customer1);
        customerRepository.save(customer2);
    }

    @AfterEach
    void delete() {
        customerRepository.deleteAll();
    }

    @Test
    void testGetAllCustomers() throws Exception {
        mockMvc.perform(get("/api/v1/customer/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[1].name").value("John"))
                .andExpect(jsonPath("$[1].surname").value("Doe"))
                .andExpect(jsonPath("$[1].balance").value(50));
    }

    @Test
    void testGetCustomerById_NotFound() throws Exception {
        mockMvc.perform(get("/api/v1/customer/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testInsertCustomer() throws Exception {
        Customer newCustomer = new Customer(3, "Test", "User", 100);
        String customerJson = objectMapper.writeValueAsString(newCustomer);

        mockMvc.perform(post("/api/v1/customer/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerJson))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Test"))
                .andExpect(jsonPath("$.surname").value("User"))
                .andExpect(jsonPath("$.balance").value(100));
    }

    @Test
    void testGetCustomerById() throws Exception {
        // Retrieve the customer by ID
        mockMvc.perform(get("/api/v1/customer/2"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("John"))
                .andExpect(jsonPath("$.surname").value("Doe"))
                .andExpect(jsonPath("$.balance").value(50));
    }
}
