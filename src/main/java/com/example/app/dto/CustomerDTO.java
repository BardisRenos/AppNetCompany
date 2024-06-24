package com.example.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * The CustomerDTO class
 */
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class CustomerDTO implements Serializable {

    private Integer customerId;
    private String name;
    private String surname;
    private Integer balance;
}
