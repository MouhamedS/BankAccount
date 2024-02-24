package com.kata.bankaccount.domain.model;

import com.kata.bankaccount.domain.ports.inputs.dtos.TransactionDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class TransactionTest {
    private static final String ACCOUNT_REFERENCE_NUMBER = "0000336";
    private Transaction transaction;
    private TransactionDTO dto;


    @BeforeEach
    void setUp() {
        transaction = new Transaction("000123", ACCOUNT_REFERENCE_NUMBER,
                BigDecimal.valueOf(100),
                LocalDateTime.of(2014, 1, 23, 11, 24),
                BigDecimal.valueOf(1500), BigDecimal.valueOf(1600), TranscationType.DEPOSIT);
        dto = new TransactionDTO("000123", ACCOUNT_REFERENCE_NUMBER,
                BigDecimal.valueOf(100),
                LocalDateTime.of(2014, 1, 23, 11, 24),
                BigDecimal.valueOf(1500), BigDecimal.valueOf(1600), TranscationType.DEPOSIT);


    }

    @Test
    void given_a_transaction_should_return_a_dto() {
        assertThat(transaction.toDTO()).isEqualTo(dto);
    }
}