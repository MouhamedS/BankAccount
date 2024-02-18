package com.kata.bankaccount.infrastructure.dao.h2.jpa_repository;

import com.kata.bankaccount.infrastructure.dao.h2.dao.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionJpaRepository extends JpaRepository<TransactionEntity, Long> {

    List<TransactionEntity> findAllByAccountReferenceNumber(String accountReferenceNumber);
}
