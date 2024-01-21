package com.kata.bankaccount.domain.ports.outgoing;

import com.kata.bankaccount.domain.model.Account;

public interface AccountOutguoingPort {

    Account getAccountById(Long accountId);

    Account saveAccount(Account account);
}
