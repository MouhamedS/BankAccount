package com.kata.bankaccount.infrastructure.dao.H2.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ACCOUNT_ID")
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
    @ManyToOne
    @JoinColumn(name = "CLIENT_ID")
    private ClientEntity client;

    /**
     * List of all the transactions
     */
    @OneToMany(mappedBy = "client")
    private List<TransactionEntity> transactions;

}
