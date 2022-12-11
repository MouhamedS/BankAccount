package com.kata.bankaccount.infrastructure.config;

import com.kata.bankaccount.domain.repository.AccountRepository;
import com.kata.bankaccount.infrastructure.dao.H2.AccountJpARepository;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.Entity;

@EnableJpaRepositories(basePackageClasses = AccountJpARepository.class)
public class JpaConfigRepository {
}
