package com.example.app.dao;

import com.example.app.resources.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing Customer entities.
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
