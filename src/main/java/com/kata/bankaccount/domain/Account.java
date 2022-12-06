package com.kata.bankaccount.domain;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class Account {

    private Long Id;

    private BigDecimal balance;

    private Long clientId;

    private List<Transaction> transactions = new ArrayList<>();

    public void deposit(BigDecimal amount) {
        balance.add(amount);
        Transaction transaction = new Transaction(amount, LocalDateTime.now(), this);
        this.transactions.add(transaction);
    }

    public boolean withdraw(BigDecimal amount) {
        if(balance.compareTo(amount) < 0) {
            return false;
        }
        balance.subtract(amount);

        Transaction transaction = new Transaction(amount.negate(), LocalDateTime.now(), this);

        this.transactions.add(transaction);

        return false;
    }


}
