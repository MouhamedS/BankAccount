package com.kata.bankaccount.application.resources;

import lombok.Data;

@Data
public class ClientResource {
    /**
     * Client id
     */
    private Long id;

    /**
     * Firstname of the client
     */
    private  String firstname;

    /**
     * Lastaname of the client
     */
    private String lastName;
}
