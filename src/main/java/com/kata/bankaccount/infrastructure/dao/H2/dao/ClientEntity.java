package com.kata.bankaccount.infrastructure.dao.H2.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "CLIENT")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientEntity {

    /**
     * Client id
     */
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CLIENT_ID")
    private Long id;

    /**
     * Firstname of the client
     */
    @Column(name = "FIRSTNAME")
    private String firstname;

    /**
     * Lastname of the client
     */
    @Column(name = "LASTNAME")
    private String lastName;
}