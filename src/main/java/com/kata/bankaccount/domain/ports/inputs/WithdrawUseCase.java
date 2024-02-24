package com.kata.bankaccount.domain.ports.inputs;

import java.math.BigDecimal;

public interface WithdrawUseCase {

    /**
     * Method to withdraw a certain amount of the account
     * @param amount Amount to withdraw
     * @param accountReferenceNumber account reference
     * @param clientReferenceNumber  client reference
     */
    void withdraw(BigDecimal amount, String accountReferenceNumber, String clientReferenceNumber);
}
