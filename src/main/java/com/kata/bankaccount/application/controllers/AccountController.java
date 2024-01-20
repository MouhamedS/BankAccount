package com.kata.bankaccount.application.controllers;

import com.kata.bankaccount.application.resources.TransactionResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;

import java.math.BigDecimal;
import java.util.List;

public interface AccountController {

    @Operation(summary = "Deposit on bank account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deposit done"),
            @ApiResponse(responseCode = "400", description = "Invalid Transaction", content = {
                    @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE
                            , schema = @Schema(implementation = ProblemDetail.class))
            }),
            @ApiResponse(responseCode = "402", description = "Account or Client not Found", content = {
                    @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE
                            , schema = @Schema(implementation = ProblemDetail.class))
            }),
            @ApiResponse(responseCode = "500", description = "Internal error", content = {
                    @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE
                            , schema = @Schema(implementation = ProblemDetail.class))
            })

    })
    void depositToAccount(@Parameter(description = "Account identification number", required = true, example = "1145") Long accountId,
                          @Parameter(description = "Client identification number", required = true, example = "1145") Long clientId,
                          @Parameter(description = "Amount to deposit into the account", required = true, example = "1500") BigDecimal amount);

    @Operation(summary = "Withdraw from bank account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Withdraw done"),
            @ApiResponse(responseCode = "400", description = "Invalid Transaction", content = {
                    @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE
                            , schema = @Schema(implementation = ProblemDetail.class))
            }),
            @ApiResponse(responseCode = "402", description = "Account or Client not Found", content = {
                    @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE
                            , schema = @Schema(implementation = ProblemDetail.class))
            }),
            @ApiResponse(responseCode = "500", description = "Internal error", content = {
                    @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE
                            , schema = @Schema(implementation = ProblemDetail.class))
            })

    })
    void withdrawFromAccount(@Parameter(description = "Account identification number", required = true, example = "1145") Long accountId,
                             @Parameter(description = "Client identification number", required = true, example = "1145") Long clientId,
                             @Parameter(description = "Amount to withdraw from the account", required = true, example = "1500") BigDecimal amount);

    @Operation(summary = "Get list of the transactions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deposit done", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = TransactionResource.class)))
            }),
            @ApiResponse(responseCode = "400", description = "Invalid Transaction", content = {
                    @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE
                            , schema = @Schema(implementation = ProblemDetail.class))
            }),
            @ApiResponse(responseCode = "402", description = "Account or Client not Found", content = {
                    @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE
                            , schema = @Schema(implementation = ProblemDetail.class))
            }),
            @ApiResponse(responseCode = "500", description = "Internal error", content = {
                    @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE
                            , schema = @Schema(implementation = ProblemDetail.class))
            })

    })
    List<TransactionResource> getAccountHistory(@Parameter(description = "Account identification number", required = true, example = "1145") Long accountId,
                                                @Parameter(description = "Client identification number", required = true, example = "1145") Long clientId);
}
