package com.kata.bankaccount.domain;

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

    /**
     * AccountId
     */
    private Client client;

}
