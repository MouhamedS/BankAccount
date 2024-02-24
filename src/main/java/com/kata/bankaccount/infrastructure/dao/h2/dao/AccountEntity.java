package com.kata.bankaccount.infrastructure.dao.h2.dao;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import java.math.BigDecimal;
import java.util.List;


@Data
@Entity
@Table(name = "ACCOUNT")
@NoArgsConstructor
@AllArgsConstructor
public class AccountEntity {

    /**
     * Account Id
     */
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACCOUNT_ID")
    private Long id;

    @NaturalId
    @Column(name = "ACCOUNT_REFERENCE_NUMBER", nullable = false, unique = true)
    private String accountReferenceNumber;

    @NaturalId
    @Column(name = "CLIENT_REFERENCE_NUMBER", nullable = false, unique = true)
    private String clientReferenceNumber;

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
    @ManyToOne
    @JoinColumn(name = "CLIENT_ID")
    private ClientEntity client;

    /**
     * List of all the transactions
     */
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<TransactionEntity> transactions;

}
