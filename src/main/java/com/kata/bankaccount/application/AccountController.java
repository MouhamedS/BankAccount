package com.kata.bankaccount.application;


import com.kata.bankaccount.application.mapper.TransactionResourceMapper;
import com.kata.bankaccount.application.resources.TransactionResource;
import com.kata.bankaccount.domain.ports.incoming.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@Tag(name ="BankAccount API", description = "API to do some operations on bank account")
@Slf4j
@RestController(value = "/api/v1/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @Autowired
    private TransactionResourceMapper transactionResourceMapper;

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
    @PostMapping(value = "/{accountId}/clients/{clientId}/deposit/{amount}")
    public void depositToAccount(@Parameter(description = "Account identification number",required = true, example = "1145") @PathVariable Long accountId,
                                 @Parameter(description = "Client identification number",required = true, example = "1145") @PathVariable Long clientId,
                                 @Parameter(description = "Amount to deposit into the account",required = true, example = "1500")@PathVariable BigDecimal amount) {
        log.info("API /accounts/{accountId}/clients/{clientId}/deposit/{amount} with parameters: " +
                "{} , {}, {}", accountId, clientId, amount);
        this.accountService.deposit(amount, accountId, clientId);
    }

    @Operation(summary = "Withdraw from bank account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Withdraw done"),
            @ApiResponse(responseCode = "400", description = "Invalid Transaction", content = {
                    @Content
            }),
            @ApiResponse(responseCode = "402", description = "Account or Client not Found", content = {
                    @Content
            }),
            @ApiResponse(responseCode = "500", description = "Internal erro", content = @Content)

    })
    @PostMapping(value = "/{accountId}/clients/{clientId}/withdraw/{amount}")
    public void withdrawFromAccount(@Parameter(description = "Account identification number",required = true, example = "1145") @PathVariable Long accountId,
                                    @Parameter(description = "Client identification number",required = true, example = "1145") @PathVariable Long clientId,
                                    @Parameter(description = "Amount to withdraw from the account",required = true, example = "1500")@PathVariable BigDecimal amount) {
        log.info("API /accounts/{accountId}/clients/{clientId}/{amount} with parameters: " +
                "{} , {}, {}", accountId, clientId, amount);
        this.accountService.withdraw(amount, accountId, clientId);
    }

    @Operation(summary = "Get list of the transactions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deposit done", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = TransactionResource.class)))
            }),
            @ApiResponse(responseCode = "402", description = "Account or Client not Found", content = {
                    @Content
            }),
            @ApiResponse(responseCode = "500", description = "Internal error", content = @Content)

    })
    @GetMapping(value = "/{accountId}/clients/{clientId}")
    public List<TransactionResource> getAccountHistory(@Parameter(description = "Account identification number",required = true, example = "1145") @PathVariable Long accountId,
                                                       @Parameter(description = "Client identification number",required = true, example = "1145") @PathVariable Long clientId) {

        return this.transactionResourceMapper.toResources(this.accountService.transactions(accountId, clientId));
    }
}