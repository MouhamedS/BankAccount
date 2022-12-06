package com.kata.bankaccount.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
public class Transaction {

    private Long id;

    private BigDecimal amount;

    private LocalDateTime date;

    private Account account;

    public Transaction(BigDecimal amount, LocalDateTime date, Account account) {
        this.amount = amount;
        this.date = date;
        this.account = account;
    }
}
