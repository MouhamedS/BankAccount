package com.kata.bankaccount.domain.error;

public class AccountException extends RuntimeException {

    public AccountException(String message) {
        super(message);
    }
}
