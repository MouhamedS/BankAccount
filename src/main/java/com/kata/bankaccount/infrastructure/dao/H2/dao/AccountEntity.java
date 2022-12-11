package com.kata.bankaccount.infrastructure.dao.H2.dao;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


@Data
@Entity
@Table(name = "ACCOUNT")
public class AccountEntity {

    /**
     * Account Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ARTICLE_ID")
    private Long Id;

    /**
     * Balance aof the account
     */
    @Column(name = "BALANCE")
    private BigDecimal balance;

    /**
     * Threshold to limit the withdraw when the balance is negative
     */
    @Column(name = "OVERDRAFT_THRESHOLD")
    private BigDecimal overdraftThreshold;

    /**
     * Owner of the account
     */
    @Column(name = "CLIENT")
    private ClientEntity client;

    /**
     * List of all the transactions
     */
    @OneToMany
    private List<TransactionEntity> transactions;

}
