package com.kata.bankaccount.application.resources;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TransactionResource {

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
    private ClientResource client;

}
