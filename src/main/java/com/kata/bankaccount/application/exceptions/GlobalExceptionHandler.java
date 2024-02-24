package com.kata.bankaccount.application.exceptions;

import com.kata.bankaccount.domain.error.AccountException;
import com.kata.bankaccount.domain.error.AccountThresholdException;
import com.kata.bankaccount.domain.error.AccountTransactionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({AccountThresholdException.class, AccountTransactionException.class, AccountException.class})
    public ProblemDetail handleThresholdViolation(RuntimeException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String message = e.getMessage();
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, message);
        problemDetail.setTitle(status.getReasonPhrase());
        return problemDetail;
    }
}
