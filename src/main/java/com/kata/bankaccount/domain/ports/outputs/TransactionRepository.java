package com.kata.bankaccount.domain.ports.outputs;

import com.kata.bankaccount.domain.model.Transaction;

import java.util.List;

public interface TransactionRepository {

    void saveTransaction(Transaction transaction);
    List<Transaction> getTransactionsByAccount(String accountReferenceNumber);
}
