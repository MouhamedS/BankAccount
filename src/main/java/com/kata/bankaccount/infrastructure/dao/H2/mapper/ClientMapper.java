package com.kata.bankaccount.infrastructure.dao.H2.mapper;

import com.kata.bankaccount.domain.Client;
import com.kata.bankaccount.infrastructure.dao.H2.dao.ClientEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedSourcePolicy = ReportingPolicy.WARN,
        unmappedTargetPolicy = ReportingPolicy.WARN)
public interface ClientMapper {

    Client fromEntity(ClientEntity clientEntity) ;

    ClientEntity toEntity(Client  client) ;
}