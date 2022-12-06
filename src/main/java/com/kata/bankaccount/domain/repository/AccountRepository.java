package com.kata.bankaccount.domain.repository;

import com.kata.bankaccount.domain.Account;

public interface AccountRepository {

    Account getAccountById(Long accountId);

    Account saveAccount(Account account);
}
