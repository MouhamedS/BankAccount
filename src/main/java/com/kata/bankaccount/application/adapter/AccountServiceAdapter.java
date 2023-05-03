package com.kata.bankaccount.application.adapter;

import com.kata.bankaccount.domain.model.Account;
import com.kata.bankaccount.domain.model.Transaction;
import com.kata.bankaccount.domain.ports.outgoing.AccountRepository;
import com.kata.bankaccount.domain.ports.incoming.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceAdapter implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    @Transactional
    public void deposit(BigDecimal amount, Long accountId, Long clientId) {
        log.info("Service deposit with amount {} accountId {} and clientId {}", amount, clientId, accountId);
        Account account = accountRepository.getAccountById(accountId);
        if ( account != null && account.getClient().id().equals(clientId)) {

            account.deposit(amount);

           log.info("Result of the deposit {}",accountRepository.saveAccount(account));
        }
    }

    @Override
    @Transactional
    public boolean withdraw(BigDecimal amount, Long accountId, Long clientId) {
        log.info("Service withdraw with amount {} accountId {} and clientId {}", amount, clientId, accountId);
        Account account = accountRepository.getAccountById(accountId);
        if (account != null && account.getClient().id().equals(clientId)) {
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
        if (account != null && account.getClient().id().equals(clientId)) {
            return account.getTransactions();
        }
        return new ArrayList<>();
    }


}
