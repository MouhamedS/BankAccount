package com.kata.bankaccount.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Transaction(Long id, BigDecimal amount, LocalDateTime date, BigDecimal balancePostTransaction) {
}
