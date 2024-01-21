package com.kata.bankaccount.domain.ports.incoming;

import com.kata.bankaccount.domain.model.Transaction;

import java.math.BigDecimal;
import java.util.List;

public interface AccountIncomingPort {

    /**
     * Method to deposit a certain on one account
     * with the specified accountId and the clientId
     * @param amount  Amount to deposit
     * @param accountId Account to deposit
     * @param clientId The clientId on the account
     */
    void deposit(BigDecimal amount, Long accountId, Long clientId);

    /**
     * Method to withdraw a certain amount of the
     * @param amount Amount to withdraw
     * @param accountId AccountID  to withdraw
     * @param clientId  ClientId
     */
    void withdraw(BigDecimal amount, Long accountId, Long clientId);

    /**
     * Get List of account's transactions
     * @param accountId AccountId of the
     * @param clientId Client
     * @return List of the transactions on an account for a client
     */
    List<Transaction> transactions(Long accountId, Long clientId);
}
