package com.kata.bankaccount.application.controllers;

import com.kata.bankaccount.domain.ports.inputs.dtos.TransactionDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.List;

@Tag(name = "BankAccount API", description = "API to do some operations on bank account")
@RequestMapping("/api/v1/accounts")
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
    @PostMapping(value = "/{accountReferenceNumber}/clients/{clientReferenceNumber}/deposit/{amount}")
    void depositToAccount(@Parameter(description = "Account identification number", required = true, example = "1145") @PathVariable String accountReferenceNumber,
                          @Parameter(description = "Client identification number", required = true, example = "1145") @PathVariable String clientReferenceNumber,
                          @Parameter(description = "Amount to deposit into the account", required = true, example = "1500") @PathVariable BigDecimal amount);

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
    @PostMapping(value = "/{accountReferenceNumber}/clients/{clientReferenceNumber}/withdraw/{amount}")
    void withdrawFromAccount(@Parameter(description = "Account identification number", required = true, example = "1145") @PathVariable String accountReferenceNumber,
                             @Parameter(description = "Client identification number", required = true, example = "1145") @PathVariable String clientReferenceNumber,
                             @Parameter(description = "Amount to withdraw from the account", required = true, example = "1500") @PathVariable BigDecimal amount);

    @Operation(summary = "Get list of the transactions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deposit done", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = TransactionDTO.class)))
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
    @GetMapping(value = "/{accountReferenceNumber}/clients/{clientReferenceNumber}")
    List<TransactionDTO> getAccountHistory(@Parameter(description = "Account identification number", required = true, example = "1145") @PathVariable String accountReferenceNumber);
}
