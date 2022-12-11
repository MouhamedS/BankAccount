package com.kata.bankaccount.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Getter
@AllArgsConstructor
@Builder
public class Account {

    /**
     * Account Id
     */
    private final Long Id;

    /**
     * Balance aof the account
     */
    private final BigDecimal balance;

    /**
     * Threshold to limit the withdraw when the balance is negative
     */
    private final BigDecimal overdraftThreshold;

    /**
     * Owner of the account
     */
    private final Client client;

    /**
     * List of all the transactions
     */
    private final List<Transaction> transactions = new ArrayList<>();

    public void deposit(BigDecimal amount) {
        balance.add(amount);
        Transaction transaction = new Transaction(amount, LocalDateTime.now(), client);
        this.transactions.add(transaction);
    }

    public boolean withdraw(BigDecimal amount, Long accountId) {
        if(balance.add(overdraftThreshold).compareTo(amount) < 0) {
            return false;
        }
        balance.subtract(amount);

        Transaction transaction = new Transaction(amount.negate(), LocalDateTime.now(), client);

        this.transactions.add(transaction);

        return true;
    }


}
