package com.kata.bankaccount.domain.repository;

import com.kata.bankaccount.domain.Account;

import java.util.Optional;

public interface AccountRepository {

    Account getAccountById(Long accountId);

    Account saveAccount(Account account);
}
