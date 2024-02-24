package com.kata.bankaccount.infrastructure.dao.h2.jpa_repository;

import com.kata.bankaccount.infrastructure.dao.h2.dao.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountJpaRepository extends JpaRepository<AccountEntity, Long> {

    AccountEntity findAccountEntityByAccountReferenceNumber(String accountReferenceNumber);

}
