package com.kata.bankaccount.domain;

import lombok.Data;

import javax.persistence.Id;

@Data
public class Client {

    @Id
    private Long Id;

    private String firstname;

    private String lastName;
}
