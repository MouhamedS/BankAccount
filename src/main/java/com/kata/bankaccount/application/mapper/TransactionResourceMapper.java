package com.kata.bankaccount.application.mapper;

import com.kata.bankaccount.application.resources.ClientResource;
import com.kata.bankaccount.application.resources.TransactionResource;
import com.kata.bankaccount.domain.model.Client;
import com.kata.bankaccount.domain.model.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TransactionResourceMapper {

    TransactionResource toResource(Transaction transaction);

    ClientResource toResource(Client client);

    List<TransactionResource> toResources(List<Transaction>transactions);
}
