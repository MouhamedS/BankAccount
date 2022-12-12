package com.kata.bankaccount.application.exceptions;

import com.kata.bankaccount.domain.error.AccountThresholdException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalException extends ResponseEntityExceptionHandler {

    @ExceptionHandler( DefaultControllerException.class)
    public ResponseEntity< DefaultErrorMessage> handleDefaultError(DefaultControllerException e) {

        log.info(e.getDefaultErrorMessage().getMessage());
        return ResponseEntity.status(e.getDefaultErrorMessage().getStatus()).body(e.getDefaultErrorMessage());
    }

    @ExceptionHandler( AccountThresholdException.class)
    public ResponseEntity<String> handleThresholdViolation(AccountThresholdException e) {

        log.info(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

}
