package com.kata.bankaccount.infrastructure.config;

import com.kata.bankaccount.infrastructure.dao.H2.JpaRepository.AccountJpARepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackageClasses = AccountJpARepository.class)
public class JpaConfigRepository {
}
