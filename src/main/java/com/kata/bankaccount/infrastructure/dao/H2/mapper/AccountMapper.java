package com.kata.bankaccount.infrastructure.dao.H2.mapper;

import com.kata.bankaccount.domain.Account;
import com.kata.bankaccount.infrastructure.dao.H2.dao.AccountEntity;
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
