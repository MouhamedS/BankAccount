package com.kata.bankaccount.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
public class Transaction {

    private Long id;

    private BigDecimal amount;

    private LocalDateTime date;

    private Long accountId;

    public Transaction(BigDecimal amount, LocalDateTime date, Long accountId) {
        this.amount = amount;
        this.date = date;
        this.accountId = accountId;
    }
}
