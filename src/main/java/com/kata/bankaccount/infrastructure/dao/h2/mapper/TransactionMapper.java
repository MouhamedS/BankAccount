package com.kata.bankaccount.infrastructure.dao.h2.mapper;


import com.kata.bankaccount.domain.model.Transaction;
import com.kata.bankaccount.infrastructure.dao.h2.dao.TransactionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.WARN,
        unmappedSourcePolicy = ReportingPolicy.WARN)
public interface TransactionMapper {

    Transaction fromEntity(TransactionEntity transactionEntity);

    List<Transaction> fromEntities(List<TransactionEntity> transactionEntities);

    TransactionEntity toEntity(Transaction transaction);
}
