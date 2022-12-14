package com.kata.bankaccount.application.resources;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(description = "Amount of transaction")
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
