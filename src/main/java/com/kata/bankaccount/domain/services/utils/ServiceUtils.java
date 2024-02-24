package com.kata.bankaccount.domain.services.utils;

import com.kata.bankaccount.domain.error.AccountException;
import com.kata.bankaccount.domain.model.Account;

import java.util.Optional;

public final class ServiceUtils {

    private ServiceUtils() {
    }

    public static Account getAccountAndCheckReference(Optional<Account> optionalAccount, String accountReferenceNumber
            , String clientReferenceNumber) {
        Account account = optionalAccount
                .orElseThrow(() -> new AccountException("Account with reference number " + accountReferenceNumber + " not found"));

        if (clientReferenceNumber == null
                || clientReferenceNumber.isBlank()
                || !clientReferenceNumber.equals(account.getClientReferenceNumber())) {
            throw new AccountException("Client with reference number " + clientReferenceNumber + " is wrong");
        }

        return account;
    }
}
