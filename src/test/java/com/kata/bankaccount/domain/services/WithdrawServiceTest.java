package com.kata.bankaccount.domain.services;

import com.kata.bankaccount.domain.model.Account;
import com.kata.bankaccount.domain.model.Transaction;
import com.kata.bankaccount.domain.ports.outputs.AccountRepository;
import com.kata.bankaccount.domain.ports.outputs.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WithdrawServiceTest {
    @Mock
    private AccountRepository accountRepository;

    @Mock
    private TransactionRepository transactionRepository;

    private WithdrawService withdrawService;

    private Optional<Account> optionalAccount;

    private final BigDecimal initialBalance = BigDecimal.valueOf(1500);
    @BeforeEach
    void setUp() {
        withdrawService = new WithdrawService(accountRepository, transactionRepository);
        Account account = new Account("0000336", "00003333", initialBalance, BigDecimal.valueOf(100));
        optionalAccount = Optional.of(account);
    }

    @Test
    void withdraw() {
        BigDecimal amountToWithdraw = BigDecimal.valueOf(200);
        when(accountRepository.getAccountByAccountReferenceNumber(any())).thenReturn(optionalAccount);
        Account updatedAccount = new Account("0000336", "00003333", initialBalance.subtract(amountToWithdraw), BigDecimal.valueOf(100));
        when(accountRepository.saveAccount(any())).thenReturn(updatedAccount);

        withdrawService.withdraw(amountToWithdraw, "0000336", "00003333");

        verify(accountRepository).saveAccount(any(Account.class));
        verify(transactionRepository).saveTransaction(any(Transaction.class));
    }
}