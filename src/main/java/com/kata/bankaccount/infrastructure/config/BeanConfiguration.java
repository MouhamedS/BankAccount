package com.kata.bankaccount.infrastructure.config;

import com.kata.bankaccount.domain.ports.inputs.DepositUseCase;
import com.kata.bankaccount.domain.ports.inputs.TransactionHistoryUseCase;
import com.kata.bankaccount.domain.ports.inputs.WithdrawUseCase;
import com.kata.bankaccount.domain.ports.outputs.AccountRepository;
import com.kata.bankaccount.domain.ports.outputs.TransactionRepository;
import com.kata.bankaccount.domain.services.DepositService;
import com.kata.bankaccount.domain.services.TranscationHistoryService;
import com.kata.bankaccount.domain.services.WithdrawService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public DepositUseCase depositUseCase(final AccountRepository accountRepository,
                                         final TransactionRepository transactionRepository) {
        return new DepositService(accountRepository, transactionRepository);
    }

    @Bean
    public WithdrawUseCase withdrawUseCase(final AccountRepository accountRepository,
                                           final TransactionRepository transactionRepository) {
        return new WithdrawService(accountRepository, transactionRepository);
    }

    @Bean
    public TransactionHistoryUseCase transactionHistoryUseCase(final TransactionRepository transactionRepository) {
        return new TranscationHistoryService(transactionRepository);
    }
}
