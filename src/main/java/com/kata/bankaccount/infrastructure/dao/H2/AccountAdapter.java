package com.kata.bankaccount.infrastructure.dao.H2;

import com.kata.bankaccount.domain.model.Account;
import com.kata.bankaccount.domain.ports.outgoing.AccountRepository;
import com.kata.bankaccount.infrastructure.dao.H2.dao.AccountEntity;
import com.kata.bankaccount.infrastructure.dao.H2.mapper.AccountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountAdapter implements AccountRepository {

    private final AccountJpARepository accountJpARepository;

    private final AccountMapper accountMapper;

    @Override
    public Account getAccountById(Long accountId) {
        return accountJpARepository
                .findById(accountId)
                .map(accountMapper::fromEntity)
                .orElseThrow(() -> new RuntimeException(String.format("Account with id %s not found", accountId)));
    }

    @Override
    public Account saveAccount(Account account) {
        AccountEntity entity = accountMapper.toEntity(account);
        // Set Account for transactions
        entity.getTransactions().forEach(transactionEntity -> transactionEntity.setAccount(entity));
        return accountMapper.fromEntity(accountJpARepository.save(entity));
    }
}
