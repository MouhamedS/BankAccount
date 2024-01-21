package com.kata.bankaccount.domain.error;

public class AccountThresholdException extends RuntimeException {

    public AccountThresholdException(String message) {
        super(message);
    }
}
