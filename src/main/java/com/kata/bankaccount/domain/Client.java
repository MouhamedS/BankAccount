package com.kata.bankaccount.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class Client {

    /**
     * Client id
     */
    private final Long id;

    /**
     * Firstname of the client
     */
    private final String firstname;

    /**
     * Lastaname of the client
     */
    private final String lastName;
}
