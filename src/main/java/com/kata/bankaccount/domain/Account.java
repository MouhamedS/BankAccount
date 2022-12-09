package com.kata.bankaccount.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class Account {

    /**
     * Account Id
     */
    private Long Id;

    /**
     * Balance aof the account
     */
    private BigDecimal balance;

    /**
     * Threshold to limit the withdraw when the balance is negative
     */
    private BigDecimal overdraftThreshold;

    /**
     * Owner of the account
     */
    private Long clientId;

    /**
     * List of all the transactions
     */
    private List<Transaction> transactions = new ArrayList<>();

    public void deposit(BigDecimal amount, Long accountId) {
        balance.add(amount);
        Transaction transaction = new Transaction(amount, LocalDateTime.now(), accountId);
        this.transactions.add(transaction);
    }

    public boolean withdraw(BigDecimal amount, Long accountId) {
        if(balance.add(overdraftThreshold).compareTo(amount) < 0) {
            return false;
        }
        balance.subtract(amount);

        Transaction transaction = new Transaction(amount.negate(), LocalDateTime.now(), accountId);

        this.transactions.add(transaction);

        return true;
    }


}
