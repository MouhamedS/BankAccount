package com.kata.bankaccount.domaine.service;

import com.kata.bankaccount.domain.model.Account;
import com.kata.bankaccount.domain.model.Client;
import com.kata.bankaccount.domain.model.Transaction;
import com.kata.bankaccount.domain.error.AccountThresholdException;
import com.kata.bankaccount.domain.ports.outgoing.AccountRepository;
import com.kata.bankaccount.domain.ports.incoming.AccountService;
import com.kata.bankaccount.application.adapters.AccountServiceAdapter;
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
        accountService = new AccountServiceAdapter(accountRepository);
    }

    @ParameterizedTest
    @ValueSource(ints = {200, 300, 500, 700})
    public void depositTest(int number) {
        when(accountRepository.getAccountById(isNotNull())).thenReturn(account);
        accountService.deposit(BigDecimal.valueOf(number), account.getId(), client.id());
        Assertions.assertThat(account.getBalance()).isEqualTo(BigDecimal.valueOf(1500 + number));
        Assertions.assertThat(account.getTransactions().size()).isEqualTo(1);
        Assertions.assertThat(account.getTransactions().get(0).amount()).isEqualTo(BigDecimal.valueOf(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {1000, 300, 500, 700})
    public void withdrawGoodAmount(int number) {
        when(accountRepository.getAccountById(isNotNull())).thenReturn(account);
        accountService.withdraw(BigDecimal.valueOf(number), account.getId(), client.id());
        Assertions.assertThat(account.getBalance()).isEqualTo(BigDecimal.valueOf(1500 - number ));
        Assertions.assertThat(account.getTransactions().size()).isEqualTo(1);
        Assertions.assertThat(account.getTransactions().get(0).amount()).isEqualTo(BigDecimal.valueOf(number).negate());

    }


    @ParameterizedTest
    @ValueSource(ints = {3000, 2000, 5000, 7000})
    public void withdrawWrongAmount(int amount) {
       when(accountRepository.getAccountById(isNotNull())).thenReturn(account);
        Assertions.assertThatThrownBy(() -> accountService.withdraw(BigDecimal.valueOf(amount), account.getId(), client.id()))
                .isInstanceOf(AccountThresholdException.class)
                .hasMessage("Account overdraft threshold has been reached");
        Assertions.assertThat(account.getBalance()).isEqualTo(BigDecimal.valueOf(1500));
        Assertions.assertThat(account.getTransactions().size()).isEqualTo(0);
    }

    @Test
    public void getListTransactions(){
        when(accountRepository.getAccountById(isNotNull())).thenReturn(account);
        accountService.withdraw(BigDecimal.valueOf(100), account.getId(), client.id());
        accountService.withdraw(BigDecimal.valueOf(500), account.getId(), client.id());
        accountService.withdraw(BigDecimal.valueOf(600), account.getId(), client.id());
        accountService.deposit(BigDecimal.valueOf(1000), account.getId(), client.id());
        accountService.deposit(BigDecimal.valueOf(500), account.getId(), client.id());
        Assertions.assertThat(accountService.transactions(account.getClient().id(), account.getId()).size()).isEqualTo(5);
        Assertions.assertThat(account.getBalance()).isEqualTo(BigDecimal.valueOf(1800));
    }
}
