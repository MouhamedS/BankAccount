package com.kata.bankaccount.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Getter
@AllArgsConstructor
@Builder
public class Transaction {

    /***
     * Transaction Id
     */
    //private final Long id;

    /**
     * Amount of the transaction
     */
    private final BigDecimal amount;

    /**
     * Date of the transaction
     */
    private final LocalDateTime date;

    /**
     * AccountId
     */
    private final Client client;

}
