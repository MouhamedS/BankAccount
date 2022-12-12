package com.kata.bankaccount.domain;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Getter
@RequiredArgsConstructor
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
