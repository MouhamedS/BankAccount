package com.kata.bankaccount.infrastructure.dao.h2.mapper;

import com.kata.bankaccount.domain.model.Account;
import com.kata.bankaccount.infrastructure.dao.h2.dao.AccountEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedSourcePolicy = ReportingPolicy.WARN,
        unmappedTargetPolicy = ReportingPolicy.WARN)
public interface AccountMapper {

    Account fromEntity(AccountEntity  accountEntity);

    AccountEntity toEntity(Account account);
}
