package com.kata.bankaccount.application;


import com.kata.bankaccount.application.mapper.TransactionResourceMapper;
import com.kata.bankaccount.application.resources.TransactionResource;
import com.kata.bankaccount.domain.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@RestController(value = "/api/v1/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @Autowired
    private TransactionResourceMapper transactionResourceMapper;

    @PostMapping(value = "/{accountId}/clients/{clientId}/deposit/{amount}")
    public void depositToAccount(@PathVariable Long accountId,
                                 @PathVariable Long clientId,
                                 @PathVariable BigDecimal amount) {
        log.info("API /accounts/{accountId}/clients/{clientId}/deposit/{amount} with parameters: " +
                "{} , {}, {}", accountId, clientId, amount);
        this.accountService.deposit(amount, accountId, clientId);
    }

    @PostMapping(value = "/{accountId}/clients/{clientId}/withdraw/{amount}")
    public void withdrawFromAccount(@PathVariable Long accountId,
                                    @PathVariable Long clientId,
                                    @PathVariable BigDecimal amount) {
        log.info("API /accounts/{accountId}/clients/{clientId}/{amount} with parameters: " +
                "{} , {}, {}", accountId, clientId, amount);
        this.accountService.withdraw(amount, accountId, clientId);
    }

    @GetMapping(value = "/{accountId}/clients/{clientId}")
    public List<TransactionResource> getAccountHistory(@PathVariable Long accountId,
                                                       @PathVariable Long clientId) {

        return this.transactionResourceMapper.toResources(this.accountService.transactions(accountId, clientId));
    }
}