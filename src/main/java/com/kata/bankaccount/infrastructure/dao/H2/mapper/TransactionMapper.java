package com.kata.bankaccount.infrastructure.dao.H2.mapper;


import com.kata.bankaccount.domain.Transaction;
import com.kata.bankaccount.infrastructure.dao.H2.dao.TransactionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.WARN,
        unmappedSourcePolicy = ReportingPolicy.WARN)
public interface TransactionMapper {

    Transaction fromEntity(TransactionEntity transactionEntity);

    TransactionEntity toEntity(Transaction transaction);
}
