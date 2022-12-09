package com.kata.bankaccount.domain.service;

import com.kata.bankaccount.domain.Transaction;

import java.math.BigDecimal;

public interface AccountService {

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
     * @param amount
     * @param accountId
     * @param clientId
     * @return
     */
    boolean withdraw(BigDecimal amount, Long accountId, Long clientId);
}
