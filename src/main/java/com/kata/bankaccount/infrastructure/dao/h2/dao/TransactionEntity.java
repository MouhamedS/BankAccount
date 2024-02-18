package com.kata.bankaccount.infrastructure.dao.h2.dao;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TRANSACTION_ID")
    private Long id;

    @NaturalId
    @Column(name = "TRANSACTION_REFERENCE_NUMBER", nullable = false, unique = true)
    private String transactionReference;

    @NaturalId
    @Column(name = "ACCOUNT_REFERENCE_NUMBER", nullable = false, unique = true)
    private String accountReferenceNumber;

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

    @Column(name = "BALANCE_PRE_TRANSACTION")
    private BigDecimal balancePreTransaction;

    @Column(name = "BALANCE_POST_TRANSACTION")
    private BigDecimal balancePostTransaction;

    /**
     * AccountId
     */

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "ACCOUNT_ID")
    private AccountEntity account;

}
