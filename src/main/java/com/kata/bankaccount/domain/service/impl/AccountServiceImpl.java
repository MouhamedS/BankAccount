package com.kata.bankaccount.domain.service.impl;

import com.kata.bankaccount.domain.Account;
import com.kata.bankaccount.domain.repository.AccountRepository;
import com.kata.bankaccount.domain.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    @Transactional
    public void deposit(BigDecimal amount, Long accountId, Long clientId) {
        Account account = accountRepository.getAccountById(accountId);
        if (account.getClientId().equals(clientId)) {

            account.deposit(amount, clientId);

            accountRepository.saveAccount(account);
        }
    }

    @Override
    @Transactional
    public boolean withdraw(BigDecimal amount, Long accountId, Long clientId) {
        Account account = accountRepository.getAccountById(accountId);
        if (account.getClientId().equals(clientId)) {
            Boolean result = account.withdraw(amount, accountId);

            accountRepository.saveAccount(account);
            return result ;
        }
        return false;
    }
}
