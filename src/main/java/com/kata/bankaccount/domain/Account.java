package com.kata.bankaccount.domain;

import com.kata.bankaccount.domain.error.AccountThresholdException;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Getter
@NoArgsConstructor
@AllArgsConstructor
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
    private  BigDecimal overdraftThreshold;

    /**
     * Owner of the account
     */
    private  Client client;

    /**
     * List of all the transactions
     */
    private  List<Transaction> transactions = new ArrayList<>();

    public void deposit(BigDecimal amount) {
        balance = balance.add(amount);
        Transaction transaction = new Transaction(amount, LocalDateTime.now(), client);
        this.transactions.add(transaction);
    }

    public boolean withdraw(BigDecimal amount) {
        if(balance.add(overdraftThreshold).compareTo(amount) < 0) {
            throw  new AccountThresholdException("Account overdraft threshold has been reached");
        }
        balance = balance.subtract(amount);

        Transaction transaction = new Transaction(amount.negate(), LocalDateTime.now(), client);

        this.transactions.add(transaction);

        return true;
    }


}
