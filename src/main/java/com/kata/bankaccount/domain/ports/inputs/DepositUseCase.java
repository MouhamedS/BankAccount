package com.kata.bankaccount.domain.ports.inputs;

import java.math.BigDecimal;

public interface DepositUseCase {

    /**
     * Method to deposit a certain on one account
     * with the specified accountId and the cl ientId
     * @param amount  Amount to deposit
     * @param accountReferenceNumber account reference
     * @param clientReferenceNumber  client reference
     */
    void deposit(BigDecimal amount, String accountReferenceNumber, String clientReferenceNumber);
}
