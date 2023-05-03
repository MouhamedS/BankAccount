package com.kata.bankaccount.domain.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transaction {

    /***
     * Transaction Id
     */
    private  Long id;

    /**
     * Amount of the transaction
     */
    private BigDecimal amount;

    /**
     * Date of the transaction
     */
    private LocalDateTime date;

    private BigDecimal balancePostTransaction;
}
