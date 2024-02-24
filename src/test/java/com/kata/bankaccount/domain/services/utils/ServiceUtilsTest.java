package com.kata.bankaccount.domain.services.utils;

import com.kata.bankaccount.domain.error.AccountException;
import com.kata.bankaccount.domain.model.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ServiceUtilsTest {

    private Optional<Account> optionalAccount;

    private final BigDecimal initialBalance = BigDecimal.valueOf(1500);

    @BeforeEach
    void init() {
        Account account = new Account("0000336", "00003333", initialBalance, BigDecimal.valueOf(100));
        optionalAccount = Optional.of(account);
    }

    @Test
    void getAccountAndCheckReference_success() {
        Account account = ServiceUtils.getAccountAndCheckReference(optionalAccount, "0000336", "00003333");

        assertThat(account)
                .isNotNull()
                .isEqualTo(optionalAccount.orElse(new Account()));
    }

    @Test
    void getAccountAndCheckReference_expected_not_found_account() {
        optionalAccount = Optional.empty();
        assertThatThrownBy(() -> ServiceUtils.getAccountAndCheckReference(optionalAccount, "0000336", "00003333"))
                .isInstanceOf(AccountException.class)
                .hasMessage("Account with reference number 0000336 not found");
    }

    @Test
    void getAccountAndCheckReference_expected_wrong_client_number() {
        assertThatThrownBy(() -> ServiceUtils.getAccountAndCheckReference(optionalAccount, "0000336", "00003339"))
                .isInstanceOf(AccountException.class)
                .hasMessage("Client with reference number 00003339 is wrong");
    }
}