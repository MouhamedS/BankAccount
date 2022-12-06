package com.kata.bankaccount.domain.service;

import com.kata.bankaccount.domain.Transaction;

import java.math.BigDecimal;

public interface AccountService {

    void deposit(BigDecimal amount, Long accountId, Long clientId);
    boolean withdraw(BigDecimal amount, Long accountId, Long clientId);
}
