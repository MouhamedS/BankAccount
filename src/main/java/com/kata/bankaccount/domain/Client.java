package com.kata.bankaccount.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
public class Client {

    @Id
    private Long Id;

    private String firstname;

    private String lastName;
}
