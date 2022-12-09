package com.kata.bankaccount.application;


import com.kata.bankaccount.domain.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@Slf4j
@RestController(value = "/api/v1")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;
    @PostMapping(value = "/accounts/{accountId}/clients/{clientId}/deposit/{amount}")
    public void depositToAccount(@PathVariable Long accountId,
                                 @PathVariable Long clientId,
                                 @PathVariable BigDecimal amount) {
        log.info("API /accounts/{accountId}/clients/{clientId}/deposit/{amount} with parameters: " +
                "{} , {}, {}", accountId, clientId, amount);
            this.accountService.deposit(amount, accountId, clientId);
    }

    @PostMapping(value = "/accounts/{accountId}/clients/{clientId}/withdraw/{amount}")
    public void withdrawFromAccount(@PathVariable Long accountId,
                                 @PathVariable Long clientId,
                                 @PathVariable BigDecimal amount) {
        log.info("API /accounts/{accountId}/clients/{clientId}/{amount} with parameters: " +
                "{} , {}, {}", accountId, clientId, amount);
        this.accountService.withdraw(amount, accountId, clientId);
    }
}
