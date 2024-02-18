package com.kata.bankaccount.domain.services;

import com.kata.bankaccount.domain.model.Transaction;
import com.kata.bankaccount.domain.ports.inputs.TransactionHistoryUseCase;
import com.kata.bankaccount.domain.ports.inputs.dtos.TransactionDTO;
import com.kata.bankaccount.domain.ports.outputs.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class TranscationHistoryService implements TransactionHistoryUseCase {

    private final TransactionRepository transactionRepository;

    @Override
    public List<TransactionDTO> getAccountTransactionHistory(String accountReferenceNumber) {
        List<Transaction> transactions = transactionRepository.getTransactionsByAccount(accountReferenceNumber);

        return transactions
                .stream()
                .map(Transaction::toDTO)
                .toList();
    }
}
