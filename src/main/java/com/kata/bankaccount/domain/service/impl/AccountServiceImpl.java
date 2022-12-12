package com.kata.bankaccount.domain.service.impl;

import com.kata.bankaccount.domain.Account;
import com.kata.bankaccount.domain.Transaction;
import com.kata.bankaccount.domain.repository.AccountRepository;
import com.kata.bankaccount.domain.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    @Transactional
    public void deposit(BigDecimal amount, Long accountId, Long clientId) {
        Account account = accountRepository.getAccountById(accountId);
        if (account.getClient().getId().equals(clientId)) {

            account.deposit(amount);

            accountRepository.saveAccount(account);
        }
    }

    @Override
    @Transactional
    public boolean withdraw(BigDecimal amount, Long accountId, Long clientId) {
        Account account = accountRepository.getAccountById(accountId);
        if (account.getClient().getId().equals(clientId)) {
            Boolean result = account.withdraw(amount, accountId);

            accountRepository.saveAccount(account);
            return result;
        }
        return false;
    }

    @Override
    public List<Transaction> transactions(Long accountId, Long clientId) {
        Account account = accountRepository.getAccountById(accountId);
        if (account.getClient().getId().equals(clientId)) {
            return account.getTransactions();
        }
        return null;
    }


}
