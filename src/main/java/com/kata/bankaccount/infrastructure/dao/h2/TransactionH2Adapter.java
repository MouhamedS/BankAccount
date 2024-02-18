package com.kata.bankaccount.infrastructure.dao.h2;

import com.kata.bankaccount.domain.model.Transaction;
import com.kata.bankaccount.domain.ports.outputs.TransactionRepository;
import com.kata.bankaccount.infrastructure.dao.h2.dao.TransactionEntity;
import com.kata.bankaccount.infrastructure.dao.h2.jpa_repository.TransactionJpaRepository;
import com.kata.bankaccount.infrastructure.dao.h2.mapper.TransactionMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class TransactionH2Adapter implements TransactionRepository {

    private final TransactionJpaRepository jpaRepository;

    private final TransactionMapper mapper;

    @Override
    public void saveTransaction(Transaction transaction) {
        TransactionEntity entity = mapper.toEntity(transaction);
        TransactionEntity updatedTransaction = jpaRepository.save(entity);
        log.info("Saved transaction with id {} and transaction reference {}",
                updatedTransaction.getId(),
                updatedTransaction.getTransactionReference());
    }

    @Override
    public List<Transaction> getTransactionsByAccount(String accountReferenceNumber) {
        return mapper.fromEntities(jpaRepository.findAllByAccountReferenceNumber(accountReferenceNumber));
    }
}
