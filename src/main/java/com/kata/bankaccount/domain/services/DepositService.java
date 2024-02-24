package com.kata.bankaccount.domain.services;

import com.kata.bankaccount.domain.model.Account;
import com.kata.bankaccount.domain.model.Transaction;
import com.kata.bankaccount.domain.model.TranscationType;
import com.kata.bankaccount.domain.ports.inputs.DepositUseCase;
import com.kata.bankaccount.domain.ports.outputs.AccountRepository;
import com.kata.bankaccount.domain.ports.outputs.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static com.kata.bankaccount.domain.services.utils.ServiceUtils.getAccountAndCheckReference;

@Slf4j
@RequiredArgsConstructor
public class DepositService implements DepositUseCase {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    @Override
    public void deposit(BigDecimal amount, String accountReferenceNumber, String clientReferenceNumber) {
        log.info("Service deposit with amount {} accountId {} and clientId {}",
                amount, clientReferenceNumber, accountReferenceNumber);

        Optional<Account> optionalAccount = accountRepository.getAccountByAccountReferenceNumber(accountReferenceNumber);
        Account account = getAccountAndCheckReference(optionalAccount, accountReferenceNumber, clientReferenceNumber);

        Account updatedAccount = account.deposit(amount);

        updatedAccount = accountRepository.saveAccount(updatedAccount);

        String uuid = UUID.randomUUID().toString();
        Transaction transaction = new Transaction(
                uuid,
                updatedAccount.getAccountReferenceNumber(),
                amount,
                LocalDateTime.now(),
                account.getBalance(),
                updatedAccount.getBalance(),
                TranscationType.DEPOSIT);
        transactionRepository.saveTransaction(transaction);

        log.info("Result of the deposit {}", updatedAccount);
    }
}