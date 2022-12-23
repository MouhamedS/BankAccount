package com.kata.bankaccount.domain.service.impl;

import com.kata.bankaccount.domain.Account;
import com.kata.bankaccount.domain.Transaction;
import com.kata.bankaccount.domain.repository.AccountRepository;
import com.kata.bankaccount.domain.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    @Transactional
    public void deposit(BigDecimal amount, Long accountId, Long clientId) {
        log.info("Service deposit with amount {} accountId {} and clientId {}", amount, clientId, accountId);
        Account account = accountRepository.getAccountById(accountId);
        if ( account != null && account.getClient().getId().equals(clientId)) {

            account.deposit(amount);

           log.info("Result of the deposit {}",accountRepository.saveAccount(account));
        }
    }

    @Override
    @Transactional
    public boolean withdraw(BigDecimal amount, Long accountId, Long clientId) {
        log.info("Service withdraw with amount {} accountId {} and clientId {}", amount, clientId, accountId);
        Account account = accountRepository.getAccountById(accountId);
        if (account != null && account.getClient().getId().equals(clientId)) {
            boolean result = account.withdraw(amount);

            log.info("Result  of the withdraw {}", accountRepository.saveAccount(account));
            return result;
        }
        return false;
    }

    @Override
    public List<Transaction> transactions(Long accountId, Long clientId) {
        log.info("Service fecth transactions with accountId {} and clientId {}", clientId, accountId);
        Account account = accountRepository.getAccountById(accountId);
        if (account != null && account.getClient().getId().equals(clientId)) {
            return account.getTransactions();
        }
        return null;
    }


}
