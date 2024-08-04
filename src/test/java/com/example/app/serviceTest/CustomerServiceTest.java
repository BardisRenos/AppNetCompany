package com.example.app.serviceTest;

import com.example.app.dao.CustomerRepository;
import com.example.app.dto.CustomerDTO;
import com.example.app.exception.CustomerNotFoundException;
import com.example.app.resources.Customer;
import com.example.app.service.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllCustomers() {
        // Arrange
        Customer customer1 = new Customer(1, "Renos", "Bardis", 100);
        Customer customer2 = new Customer(2, "John", "Doe", 50);
        List<Customer> customerList = Arrays.asList(customer1, customer2);
        when(customerRepository.findAll()).thenReturn(customerList);

        // Act
        List<CustomerDTO> result = customerService.getAllCustomers();

        // Assert
        assertEquals(2, result.size());
        assertEquals(customer1.getCustomerID(), result.get(0).getCustomerId());
        assertEquals(customer2.getName(), result.get(1).getName());
    }

    @Test
    void testGetCustomerById_Success() throws CustomerNotFoundException {
        // Arrange
        Customer customer = new Customer(1, "Renos", "Bardis", 100);
        when(customerRepository.findById(1)).thenReturn(Optional.of(customer));

        // Act
        CustomerDTO result = customerService.getCustomerById(1);

        // Assert
        assertNotNull(result);
        assertEquals(customer.getCustomerID(), result.getCustomerId());
        assertEquals(customer.getName(), result.getName());
    }

    @Test
    void testGetCustomerById_NotFound() {
        // Arrange
        when(customerRepository.findById(1)).thenReturn(Optional.empty());

        // Act & Assert
        CustomerNotFoundException thrown = assertThrows(
                CustomerNotFoundException.class,
                () -> customerService.getCustomerById(1),
                "Expected getCustomerById to throw, but it didn't"
        );

        assertEquals("There is no customer with the id : 1", thrown.getMessage());
    }

    @Test
    void testInsertNewCustomer() {
        // Arrange
        Customer customer = new Customer(1, "Renos", "Bardis", 100);
        when(customerRepository.save(customer)).thenReturn(customer);

        // Act
        CustomerDTO result = customerService.insertNewCustomer(customer);

        // Assert
        assertNotNull(result);
        assertEquals(customer.getCustomerID(), result.getCustomerId());
        assertEquals(customer.getName(), result.getName());
        assertEquals(customer.getSurname(), result.getSurname());
        assertEquals(customer.getBalance(), result.getBalance());

        verify(customerRepository, times(1)).save(customer);
    }
}
