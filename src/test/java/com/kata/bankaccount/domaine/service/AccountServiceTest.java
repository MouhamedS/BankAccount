package com.kata.bankaccount.domaine.service;

import com.kata.bankaccount.application.adapters.AccountServiceAdapter;
import com.kata.bankaccount.domain.error.AccountThresholdException;
import com.kata.bankaccount.domain.model.Account;
import com.kata.bankaccount.domain.model.Client;
import com.kata.bankaccount.domain.model.Transaction;
import com.kata.bankaccount.domain.ports.incoming.AccountService;
import com.kata.bankaccount.domain.ports.outgoing.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    private AccountService accountService;

    private Account account;

    @Mock
    private Client client;

    private List<Transaction> transactions;

    private final BigDecimal initialBalance = new BigDecimal("1500");

    private final BigDecimal overdraftThreshold = new BigDecimal("100");

    @BeforeEach
    void init() {
        client = new Client(1L, "Toto", "Titi");
        transactions = new ArrayList<>();
        account = new Account(1L, initialBalance, overdraftThreshold, client, transactions);
        accountService = new AccountServiceAdapter(accountRepository);
    }

    @ParameterizedTest
    @ValueSource(ints = {200, 300, 500, 700})
    void depositTest(int number) {
        when(accountRepository.getAccountById(isNotNull())).thenReturn(account);

        accountService.deposit(BigDecimal.valueOf(number), account.getId(), client.id());

        BigDecimal balance = account.getBalance();
        List<Transaction> accountTransactions = account.getTransactions();
        BigDecimal expectedBanlance = initialBalance.add(BigDecimal.valueOf(number));

        assertThat(balance).isEqualTo(expectedBanlance);;
        assertThat(accountTransactions).hasSize(1);
        assertThat(accountTransactions.get(0).amount()).isEqualTo(BigDecimal.valueOf(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {1000, 300, 500, 700})
    void withdrawGoodAmount(int number) {
        when(accountRepository.getAccountById(isNotNull())).thenReturn(account);

        accountService.withdraw(BigDecimal.valueOf(number), account.getId(), client.id());

        BigDecimal actualBalance = account.getBalance();
        BigDecimal expectedBalance = initialBalance.subtract(BigDecimal.valueOf(number));

        assertThat(actualBalance).isEqualTo(expectedBalance);

        List<Transaction> transactionList = account.getTransactions();

        assertThat(transactionList).hasSize(1);
        assertThat(transactionList.get(0).amount()).isEqualTo(BigDecimal.valueOf(number).negate());
    }


    @ParameterizedTest
    @ValueSource(ints = {3000, 2000, 5000, 7000})
    void withdrawWrongAmount(int amount) {
        when(accountRepository.getAccountById(isNotNull())).thenReturn(account);

        BigDecimal bigDecimalAmount = BigDecimal.valueOf(amount);
        Long accountId = account.getId();
        Long clientId = client.id();
        assertThatThrownBy(() -> accountService.withdraw(bigDecimalAmount, accountId, clientId))
                .isInstanceOf(AccountThresholdException.class)
                .hasMessage("Account overdraft threshold has been reached");

        assertThat(account.getBalance()).isEqualTo(BigDecimal.valueOf(1500));
        assertThat(account.getTransactions()).isEmpty();
    }

    @Test
    void getListTransactions() {
        when(accountRepository.getAccountById(isNotNull())).thenReturn(account);

        accountService.withdraw(BigDecimal.valueOf(100), account.getId(), client.id());
        accountService.withdraw(BigDecimal.valueOf(500), account.getId(), client.id());
        accountService.withdraw(BigDecimal.valueOf(600), account.getId(), client.id());
        accountService.deposit(BigDecimal.valueOf(1000), account.getId(), client.id());
        accountService.deposit(BigDecimal.valueOf(500), account.getId(), client.id());

        List<Transaction> transactionList = accountService.transactions(account.getClient().id(), account.getId());
        BigDecimal balance = account.getBalance();

        assertThat(transactionList).hasSize(5);
        assertThat(balance).isEqualTo(BigDecimal.valueOf(1800));
    }
}
