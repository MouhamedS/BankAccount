package com.kata.bankaccount.application.adapters;

import com.kata.bankaccount.domain.model.Account;
import com.kata.bankaccount.domain.model.Transaction;
import com.kata.bankaccount.domain.ports.incoming.AccountIncomingPort;
import com.kata.bankaccount.domain.ports.outgoing.AccountOutguoingPort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountIncomingAdapter implements AccountIncomingPort {

    private final AccountOutguoingPort accountOutguoingPort;

    @Override
    @Transactional
    public void deposit(BigDecimal amount, Long accountId, Long clientId) {
        log.info("Service deposit with amount {} accountId {} and clientId {}", amount, clientId, accountId);
        Account account = accountOutguoingPort.getAccountById(accountId);
        if (account != null && account.getClient().id().equals(clientId)) {

            account.deposit(amount);

            log.info("Result of the deposit {}", accountOutguoingPort.saveAccount(account));
        }
    }

    @Override
    @Transactional
    public void withdraw(BigDecimal amount, Long accountId, Long clientId) {
        log.info("Service withdraw with amount {} accountId {} and clientId {}", amount, clientId, accountId);
        Account account = accountOutguoingPort.getAccountById(accountId);
        if (account != null && account.getClient().id().equals(clientId)) {
            account.withdraw(amount);
            log.info("Result  of the withdraw {}", accountOutguoingPort.saveAccount(account));
        }
    }

    @Override
    public List<Transaction> transactions(Long accountId, Long clientId) {
        log.info("Service fetch transactions with accountId {} and clientId {}", clientId, accountId);
        Account account = accountOutguoingPort.getAccountById(accountId);
        if (account != null && account.getClient().id().equals(clientId)) {
            return account.getTransactions();
        }
        return new ArrayList<>();
    }


}
