package com.kata.bankaccount.domain.ports.inputs;

import com.kata.bankaccount.domain.ports.inputs.dtos.TransactionDTO;

import java.util.List;

public interface TransactionHistoryUseCase {

    List<TransactionDTO> getAccountTransactionHistory(String accountReferenceNumber);
}
