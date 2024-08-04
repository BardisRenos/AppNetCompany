package com.example.app.resources;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

/**
 * The entity class for the customer database table.
 */
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@Table(name = "customer")
public class Customer implements Serializable {

    /**
     * The unique identifier for a customer.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id", unique = true, nullable = false, updatable = false)
    private Integer customerID;

    /**
     * The name of the customer.
     */
    @Column(name = "name")
    private String name;

    /**
     * The surname of the customer.
     */
    @Column(name = "surname")
    private String surname;

    /**
     * The balance of the customer.
     */
    @Column(name = "balance")
    private Integer balance;

    /**
     * Constructs a new Customer.
     *
     * @param customerID the unique identifier of the customer.
     * @param name the name of the customer.
     * @param surname the surname of the customer.
     * @param balance the balance of the customer.
     */
    public Customer(Integer customerID, String name, String surname, Integer balance) {
        this.customerID = customerID;
        this.name = name;
        this.surname = surname;
        this.balance = balance;
    }

}
