package com.kata.bankaccount.infrastructure.errors;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class DatabaseException extends RuntimeException{

    private final HttpStatus status;

    public DatabaseException(HttpStatus status, String message){
        super(message);
        this.status = status;
    }


}
