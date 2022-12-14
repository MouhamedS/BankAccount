package com.kata.bankaccount.domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Client {

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
