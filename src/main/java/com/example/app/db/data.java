package com.example.app.db;

import com.example.app.dao.CustomerRepository;
import com.example.app.resources.Customer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Configuration class for initializing the database with sample data.
 */
@Configuration
public class data {

    /**
     * Initializes the database with sample customer data.
     *
     * @param customerRepository the repository for managing customer data.
     * @return a CommandLineRunner that populates the database with sample customers.
     */
    @Bean
    public CommandLineRunner initDB(CustomerRepository customerRepository) {

        return args -> {

            List<Customer> customers = new ArrayList<>(Arrays.asList(
                    new Customer(1, "Renos", "Bardis", 100),
                    new Customer(2, "John", "Doe", 50),
                    new Customer(3, "Nick", "Smith", 45)));

            customerRepository.saveAll(customers);

        };
    }
}
