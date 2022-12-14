package com.kata.bankaccount.domaine.service;

import com.kata.bankaccount.domain.Account;
import com.kata.bankaccount.domain.Client;
import com.kata.bankaccount.domain.Transaction;
import com.kata.bankaccount.domain.error.AccountThresholdException;
import com.kata.bankaccount.domain.repository.AccountRepository;
import com.kata.bankaccount.domain.service.AccountService;
import com.kata.bankaccount.domain.service.impl.AccountServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    private AccountService accountService;

    private Account account;

    @Mock
    private Client client;

    private List<Transaction> transactions;

    @BeforeEach
    public void init() {
        client = new Client(1L, "Toto", "Titi");
        transactions = new ArrayList<>();
        account = new Account(1L, BigDecimal.valueOf(1500), BigDecimal.valueOf(100), client, transactions);
        accountService = new AccountServiceImpl(accountRepository);
    }

    @ParameterizedTest
    @ValueSource(ints = {200, 300, 500, 700})
    public void depositTest(int number) {
        when(accountRepository.getAccountById(isNotNull())).thenReturn(account);
        accountService.deposit(BigDecimal.valueOf(number), account.getId(), client.getId());
        Assertions.assertThat(account.getBalance()).isEqualTo(BigDecimal.valueOf(1500 + number));
        Assertions.assertThat(account.getTransactions().size()).isEqualTo(1);
        Assertions.assertThat(account.getTransactions().get(0).getAmount()).isEqualTo(BigDecimal.valueOf(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {1000, 300, 500, 700})
    public void withdrawGoodAmount(int number) {
        when(accountRepository.getAccountById(isNotNull())).thenReturn(account);
        accountService.withdraw(BigDecimal.valueOf(number), account.getId(), client.getId());
        Assertions.assertThat(account.getBalance()).isEqualTo(BigDecimal.valueOf(1500 - number ));
        Assertions.assertThat(account.getTransactions().size()).isEqualTo(1);
        Assertions.assertThat(account.getTransactions().get(0).getAmount()).isEqualTo(BigDecimal.valueOf(number).negate());

    }


    @ParameterizedTest
    @ValueSource(ints = {3000, 2000, 5000, 7000})
    public void withdrawWrongAmount(int amount) {
       when(accountRepository.getAccountById(isNotNull())).thenReturn(account);
        Assertions.assertThatThrownBy(() -> accountService.withdraw(BigDecimal.valueOf(amount), account.getId(), client.getId()))
                .isInstanceOf(AccountThresholdException.class)
                .hasMessage("Account overdraft threshold has been reached");
        Assertions.assertThat(account.getBalance()).isEqualTo(BigDecimal.valueOf(1500));
        Assertions.assertThat(account.getTransactions().size()).isEqualTo(0);
    }

    @Test
    public void getListTransactions(){
        when(accountRepository.getAccountById(isNotNull())).thenReturn(account);
        accountService.withdraw(BigDecimal.valueOf(100), account.getId(), client.getId());
        accountService.withdraw(BigDecimal.valueOf(500), account.getId(), client.getId());
        accountService.withdraw(BigDecimal.valueOf(600), account.getId(), client.getId());
        accountService.deposit(BigDecimal.valueOf(1000), account.getId(), client.getId());
        accountService.deposit(BigDecimal.valueOf(500), account.getId(), client.getId());
        Assertions.assertThat(accountService.transactions(account.getClient().getId(), account.getId()).size()).isEqualTo(5);
        Assertions.assertThat(account.getBalance()).isEqualTo(BigDecimal.valueOf(1800));
    }
}
