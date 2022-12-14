package com.kata.bankaccount.domaine;

import com.kata.bankaccount.domain.Account;
import com.kata.bankaccount.domain.Client;
import com.kata.bankaccount.domain.Transaction;
import com.kata.bankaccount.domain.error.AccountThresholdException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class AccountTests {

    private Account account;

    private Client client;

    private List<Transaction> transactions;

    @BeforeEach
    public void init() {
        client = new Client(1L, "Toto", "Titi");
        transactions = new ArrayList<>();
        account = new Account(1L, BigDecimal.valueOf(1500), BigDecimal.valueOf(100), client, transactions);
    }

    @ParameterizedTest
    @ValueSource(ints = {200, 300, 500, 700})
    public void depositAccountTest(int number) {
        account.deposit(BigDecimal.valueOf(number));
        Assertions.assertThat(account.getBalance()).isEqualTo(BigDecimal.valueOf(1500 + number));
        Assertions.assertThat(account.getTransactions().size()).isEqualTo(1);
        Assertions.assertThat(account.getTransactions().get(0).getAmount()).isEqualTo(BigDecimal.valueOf(number));
    }


    @ParameterizedTest
    @ValueSource(ints = {1000, 300, 500, 700})
    public void withdrawGoodAmount(int number) {
        Assertions.assertThat(account.withdraw(BigDecimal.valueOf(number))).isTrue();
        Assertions.assertThat(account.getBalance()).isEqualTo(BigDecimal.valueOf(1500 - number ));
        Assertions.assertThat(account.getTransactions().size()).isEqualTo(1);
        Assertions.assertThat(account.getTransactions().get(0).getAmount()).isEqualTo(BigDecimal.valueOf(number).negate());

    }


    @ParameterizedTest
    @ValueSource(ints = {3000, 2000, 5000, 7000})
    public void withdrawWrongAmount(int number) {
        Assertions.assertThatThrownBy(() -> account.withdraw(BigDecimal.valueOf(number)))
                .isInstanceOf(AccountThresholdException.class)
                .hasMessage("Account overdraft threshold has been reached");
        Assertions.assertThat(account.getBalance()).isEqualTo(BigDecimal.valueOf(1500));
        Assertions.assertThat(account.getTransactions().size()).isEqualTo(0);
    }
}
