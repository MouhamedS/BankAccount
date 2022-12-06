package com.kata.bankaccount.infrastructure;

import com.kata.bankaccount.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountJpARepository extends JpaRepository<Account, Long> {

}
