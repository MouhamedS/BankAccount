package com.kata.bankaccount.application.exceptions;

import com.kata.bankaccount.domain.error.AccountThresholdException;
import com.kata.bankaccount.domain.error.AccountTransactionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DefaultControllerException.class)
    public ProblemDetail handleDefaultError(DefaultControllerException e) {
        HttpStatus status = e.getDefaultErrorMessage().getStatus();
        String message = e.getDefaultErrorMessage().getMessage();
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, message);
        problemDetail.setTitle(status.getReasonPhrase());
        return problemDetail;
    }

    @ExceptionHandler({AccountThresholdException.class, AccountTransactionException.class})
    public ProblemDetail handleThresholdViolation(RuntimeException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String message = e.getMessage();
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, message);
        problemDetail.setTitle(status.getReasonPhrase());
        return problemDetail;
    }

    @ExceptionHandler(RuntimeException.class)
    public ProblemDetail handleRuntime(AccountThresholdException e) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        String message = e.getMessage();

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, message);
        problemDetail.setTitle(status.getReasonPhrase());

        return problemDetail;
    }
}
