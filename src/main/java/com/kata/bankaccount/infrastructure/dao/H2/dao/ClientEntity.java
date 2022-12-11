package com.kata.bankaccount.infrastructure.dao.H2.dao;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "CLIENT")
@Data
@Builder
@NoArgsConstructor
public class ClientEntity {

    /**
     * Client id
     */
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ARTICLE_ID")
    private Long Id;

    /**
     * Firstname of the client
     */
    @Column(name = "FIRSTNAME")
    private String firstname;

    /**
     * Lastaname of the client
     */
    @Column(name = "LASTNAME")
    private String lastName;
}
