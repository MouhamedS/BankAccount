package com.kata.bankaccount.domain.model;

import com.kata.bankaccount.domain.error.AccountThresholdException;
import com.kata.bankaccount.domain.error.AccountTransactionException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    /**
     * Account Id
     */
    private Long id;

    /**
     * Balance aof the account
     */
    private BigDecimal balance;

    /**
     * Threshold to limit the withdrawal when the balance is negative
     */
    private BigDecimal overdraftThreshold;

    /**
     * Owner of the account
     */
    private Client client;

    /**
     * List of all the transactions
     */
    private List<Transaction> transactions = new ArrayList<>();

    public void deposit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new AccountTransactionException("Cannot deposit an amount less than 0");
        }
        balance = balance.add(amount);
        Transaction transaction = new Transaction(null, amount, LocalDateTime.now(), balance);
        this.transactions.add(transaction);
    }

    public boolean withdraw(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new AccountTransactionException("Cannot withdraw an amount less than 0");
        }
        if (balance.add(overdraftThreshold).compareTo(amount) < 0) {
            throw new AccountThresholdException("Account overdraft threshold has been reached");
        }
        balance = balance.subtract(amount);

        Transaction transaction = new Transaction(null, amount.negate(), LocalDateTime.now(), balance);

        this.transactions.add(transaction);

        return true;
    }
}
