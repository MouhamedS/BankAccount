package com.kata.bankaccount.domain.ports.outputs;

import com.kata.bankaccount.domain.model.Account;

import java.util.Optional;

public interface AccountRepository {

    Optional<Account> getAccountByAccountReferenceNumber(String accountReferenceNumber);

    Account saveAccount(Account account);
}
