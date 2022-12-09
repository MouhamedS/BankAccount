package com.kata.bankaccount.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
public class Transaction {

    /***
     * Transaction Id
     */
    private Long id;

    /**
     * Amount of the transaction
     */
    private BigDecimal amount;

    /**
     * Date of the transaction
     */
    private LocalDateTime date;

    /**
     * AccountId
     */
    private Long accountId;

    public Transaction(BigDecimal amount, LocalDateTime date, Long accountId) {
        this.amount = amount;
        this.date = date;
        this.accountId = accountId;
    }
}
