package com.kata.bankaccount.infrastructure.dao.h2.dao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CLIENT")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientEntity {

    /**
     * Client id
     */
    @Id
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