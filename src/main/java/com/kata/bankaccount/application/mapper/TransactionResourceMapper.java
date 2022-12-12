package com.kata.bankaccount.application.mapper;

import com.kata.bankaccount.application.resources.TransactionResource;
import com.kata.bankaccount.domain.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TransactionResourceMapper {

    TransactionResource toResource(Transaction transaction);

    List<TransactionResource> toResources(List<Transaction>transactions);
}
