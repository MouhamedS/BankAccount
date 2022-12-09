package com.kata.bankaccount.domain;

import lombok.Data;

import javax.persistence.Id;

@Data
public class Client {

    /**
     * Client id
     */
    private Long Id;

    /**
     * Firstname of the client
     */
    private String firstname;

    /**
     * Lastaname of the client
     */
    private String lastName;
}
