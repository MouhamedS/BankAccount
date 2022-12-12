package com.kata.bankaccount.application.resources;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
    private Long accountId;

}
