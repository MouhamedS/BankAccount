package com.kata.bankaccount.domain.model;

import com.kata.bankaccount.domain.ports.inputs.dtos.TransactionDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Transaction(String transactionReference,
                          String accountReferenceNumber,
                          BigDecimal amount,
                          LocalDateTime date,
                          BigDecimal balancePreTransaction,
                          BigDecimal balancePostTransaction,
                          TranscationType transcationType) {
    public TransactionDTO toDTO() {
        return new TransactionDTO(transactionReference, accountReferenceNumber, amount, date, balancePreTransaction, balancePostTransaction, transcationType);
    }
}
