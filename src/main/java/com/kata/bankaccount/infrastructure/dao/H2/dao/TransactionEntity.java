package com.kata.bankaccount.infrastructure.dao.H2.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "TRANSACTION")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionEntity {

    /***
     * Transaction Id
     */
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TRANSACTION_ID")
    private Long id;

    /**
     * Amount of the transaction
     */
    @Column(name = "AMOUNT")
    private BigDecimal amount;

    /**
     * Date of the transaction
     */
    @Column(name = "DATE")
    private LocalDateTime date;

    @Column(name = "BALANCE_POST_TRANSACTION")
    private BigDecimal balancePostTransaction;

    /**
     * AccountId
     */
    @ManyToOne
    @JoinColumn(name = "CLIENT_ID")
    private ClientEntity client;

    @ManyToOne
    @JoinColumn(name = "ACCOUNT_ID")
    private AccountEntity account;

}
