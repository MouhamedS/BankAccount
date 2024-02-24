package com.kata.bankaccount.domain.ports.inputs.dtos;

import com.kata.bankaccount.domain.model.TranscationType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionDTO(String transactionReference,
                             String accountReferenceNumber,
                             BigDecimal amount,
                             LocalDateTime date,
                             BigDecimal balancePreTransaction,
                             BigDecimal balancePostTransaction,
                             TranscationType transcationType) {
}
