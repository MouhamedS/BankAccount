package com.kata.bankaccount.infrastructure.dao.H2.JpaRepository;

import com.kata.bankaccount.infrastructure.dao.H2.dao.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountJpARepository extends JpaRepository<AccountEntity, Long> {

}
