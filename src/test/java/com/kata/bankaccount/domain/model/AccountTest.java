package com.kata.bankaccount.domain.model;

import com.kata.bankaccount.domain.error.AccountThresholdException;
import com.kata.bankaccount.domain.error.AccountTransactionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AccountTest {

    private Account account;

    private final BigDecimal initialBalance = BigDecimal.valueOf(1500);

    @BeforeEach
    void init() {
        account = new Account("0000336", "00003333", initialBalance, BigDecimal.valueOf(100));
    }

    @ParameterizedTest
    @ValueSource(ints = {200, 300, 500, 700})
    void deposit_account_success(long number) {
        account.deposit(BigDecimal.valueOf(number));
        BigDecimal expectedBalance = initialBalance.add(BigDecimal.valueOf(number));
        assertThat(account.getBalance()).isEqualTo(expectedBalance);
    }

    @ParameterizedTest
    @ValueSource(ints = {-200, -300, -500, -700})
    void deposit_account_and_expect_an_exception(long number) {
        BigDecimal amount = BigDecimal.valueOf(number);
        assertThatThrownBy(() -> account.deposit(amount))
                .isInstanceOf(AccountTransactionException.class)
                .hasMessage("Cannot deposit an amount less than 0");
        assertThat(account.getBalance()).isEqualTo(initialBalance);
    }


    @ParameterizedTest
    @ValueSource(ints = {1000, 300, 500, 700})
    void withdraw_amount_success(int number) {
        account.withdraw(BigDecimal.valueOf(number));
        BigDecimal expectedBalance = initialBalance.subtract(BigDecimal.valueOf(number));
        assertThat(account.getBalance()).isEqualTo(expectedBalance);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1000, -300, -500, -700})
    void withdraw_amount_expected_transaction_exception(int number) {
        BigDecimal amount = BigDecimal.valueOf(number);
        assertThatThrownBy(() -> account.withdraw(amount))
                .isInstanceOf(AccountTransactionException.class)
                .hasMessage("Cannot withdraw an amount less than 0");
        assertThat(account.getBalance()).isEqualTo(initialBalance);
    }


    @ParameterizedTest
    @ValueSource(ints = {3000, 2000, 5000, 7000})
    void withdraw_amount_expected_thresold_exception(int number) {
        BigDecimal amount = BigDecimal.valueOf(number);
        assertThatThrownBy(() -> account.withdraw(amount))
                .isInstanceOf(AccountThresholdException.class)
                .hasMessage("Account overdraft threshold has been reached");
        assertThat(account.getBalance()).isEqualTo(initialBalance);
    }
}
