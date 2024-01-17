package com.kata.bankaccount.application.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;

public interface AccountController {

    @Operation(summary = "Deposit on bank account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deposit done"),
            @ApiResponse(responseCode = "400", description = "Invalid Transaction", content = {
                    @Content
            }),
            @ApiResponse(responseCode = "402", description = "Account or Client not Found", content = {
                    @Content
            }),
            @ApiResponse(responseCode = "500", description = "Internal error", content = @Content)

    })
    void depositToAccount(@Parameter(description = "Account identification number", required = true, example = "1145")Long accountId,
                          @Parameter(description = "Client identification number", required = true, example = "1145")Long clientId,
                          @Parameter(description = "Amount to deposit into the account", required = true, example = "1500")BigDecimal amount);
}
