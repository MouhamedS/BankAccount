package com.kata.bankaccount.application.controllers;

import com.kata.bankaccount.domain.ports.inputs.DepositUseCase;
import com.kata.bankaccount.domain.ports.inputs.TransactionHistoryUseCase;
import com.kata.bankaccount.domain.ports.inputs.WithdrawUseCase;
import com.kata.bankaccount.domain.ports.inputs.dtos.TransactionDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class AccountControllerImpl implements AccountController {

    private final DepositUseCase depositUseCase;

    private final WithdrawUseCase withdrawUseCase;

    private final TransactionHistoryUseCase transactionHistoryUseCase;


    public void depositToAccount(String accountReferenceNumber,
                                 String clientReferenceNumber,
                                 BigDecimal amount) {
        log.info("API call POST /accounts/{}/clients/{}/deposit/{} with parameters",
                accountReferenceNumber,
                clientReferenceNumber,
                amount);
        this.depositUseCase.deposit(amount, accountReferenceNumber, accountReferenceNumber);
    }


    public void withdrawFromAccount(String accountReferenceNumber,
                                    String clientReferenceNumber,
                                    BigDecimal amount) {
        log.info("API call POST /accounts/{}/clients/{}/{} with parameters",
                accountReferenceNumber,
                clientReferenceNumber,
                amount);
        this.withdrawUseCase.withdraw(amount, accountReferenceNumber, clientReferenceNumber);
    }

    public List<TransactionDTO> getAccountHistory(String accountReferenceNumber) {
        log.info("API call GET /accounts/{}/ with parameters", accountReferenceNumber);

        return transactionHistoryUseCase.getAccountTransactionHistory(accountReferenceNumber);
    }
}