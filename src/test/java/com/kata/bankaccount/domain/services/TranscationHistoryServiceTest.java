package com.kata.bankaccount.domain.services;

import com.kata.bankaccount.domain.model.Transaction;
import com.kata.bankaccount.domain.model.TranscationType;
import com.kata.bankaccount.domain.ports.inputs.dtos.TransactionDTO;
import com.kata.bankaccount.domain.ports.outputs.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TranscationHistoryServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    private TranscationHistoryService service;

    private Transaction transaction;

    private static final String ACCOUNT_REFERENCE_NUMBER = "0000336";

    @BeforeEach
    void setUp() {
        service = new TranscationHistoryService(transactionRepository);
        transaction = new Transaction("000123", ACCOUNT_REFERENCE_NUMBER,
                BigDecimal.valueOf(100),
                LocalDateTime.of(2014, 1, 23, 11, 24),
                BigDecimal.valueOf(1500), BigDecimal.valueOf(1600), TranscationType.DEPOSIT);

    }

    @Test
    void getAccountTransactionHistory() {
        when(transactionRepository.getTransactionsByAccount(any())).thenReturn(List.of(transaction));

        List<TransactionDTO> transactionDTOS = service.getAccountTransactionHistory(ACCOUNT_REFERENCE_NUMBER);

        assertThat(transactionDTOS).isEqualTo(List.of(transaction.toDTO()));
        verify(transactionRepository).getTransactionsByAccount(any());
    }
}