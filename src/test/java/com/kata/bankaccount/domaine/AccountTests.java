package com.kata.bankaccount.domaine;

import com.kata.bankaccount.domain.error.AccountThresholdException;
import com.kata.bankaccount.domain.model.Account;
import com.kata.bankaccount.domain.model.Client;
import com.kata.bankaccount.domain.model.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
class AccountTests {

    private Account account;

    private Client client;

    private List<Transaction> transactions;

    @BeforeEach
    void init() {
        client = new Client(1L, "Toto", "Titi");
        transactions = new ArrayList<>();
        account = new Account(1L, BigDecimal.valueOf(1500), BigDecimal.valueOf(100), client, transactions);
    }

    @ParameterizedTest
    @ValueSource(ints = {200, 300, 500, 700})
    void depositAccountTest(int number) {
        account.deposit(BigDecimal.valueOf(number));
        assertThat(account.getBalance()).isEqualTo(BigDecimal.valueOf(1500 + number));
        assertThat(account.getTransactions()).hasSize(1);
        assertThat(account.getTransactions().get(0).amount()).isEqualTo(BigDecimal.valueOf(number));
    }


    @ParameterizedTest
    @ValueSource(ints = {1000, 300, 500, 700})
    void withdrawGoodAmount(int number) {
        assertThat(account.withdraw(BigDecimal.valueOf(number))).isTrue();
        assertThat(account.getBalance()).isEqualTo(BigDecimal.valueOf(1500 - number));
        assertThat(account.getTransactions()).hasSize(1);
        assertThat(account.getTransactions().get(0).amount()).isEqualTo(BigDecimal.valueOf(number).negate());

    }


    @ParameterizedTest
    @ValueSource(ints = {3000, 2000, 5000, 7000})
    void withdrawWrongAmount(int number) {
        BigDecimal amount = BigDecimal.valueOf(number);
        assertThatThrownBy(() -> account.withdraw(amount))
                .isInstanceOf(AccountThresholdException.class)
                .hasMessage("Account overdraft threshold has been reached");
        assertThat(account.getBalance()).isEqualTo(BigDecimal.valueOf(1500));
        assertThat(account.getTransactions()).isEmpty();
    }
}
