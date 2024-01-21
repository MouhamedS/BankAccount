package com.kata.bankaccount.application.controllers;

import com.kata.bankaccount.application.mapper.TransactionResourceMapper;
import com.kata.bankaccount.application.resources.TransactionResource;
import com.kata.bankaccount.domain.ports.incoming.AccountIncomingPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
@RestController
@Slf4j
@RequiredArgsConstructor
public class AccountControllerImpl implements AccountController {

    private final AccountIncomingPort accountIncomingPort;

    private final TransactionResourceMapper transactionResourceMapper;

    public void depositToAccount(Long accountId,
                                 Long clientId,
                                 BigDecimal amount) {
        log.info("API call POST /accounts/{}/clients/{}/deposit/{} with parameters", accountId, clientId, amount);
        this.accountIncomingPort.deposit(amount, accountId, clientId);
    }


    public void withdrawFromAccount(Long accountId,
                                    Long clientId,
                                    BigDecimal amount) {
        log.info("API call POST /accounts/{}/clients/{}/{} with parameters", accountId, clientId, amount);
        this.accountIncomingPort.withdraw(amount, accountId, clientId);
    }

    public List<TransactionResource> getAccountHistory(Long accountId,
                                                       Long clientId) {
        log.info("API call GET /accounts/{}/clients/{} with parameters", accountId, clientId);
        return this.transactionResourceMapper.toResources(this.accountIncomingPort.transactions(accountId, clientId));
    }
}