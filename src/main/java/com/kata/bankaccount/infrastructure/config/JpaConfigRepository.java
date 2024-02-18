package com.kata.bankaccount.infrastructure.config;

import com.kata.bankaccount.infrastructure.dao.h2.jpa_repository.AccountJpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackageClasses = AccountJpaRepository.class)
public class JpaConfigRepository {
}
