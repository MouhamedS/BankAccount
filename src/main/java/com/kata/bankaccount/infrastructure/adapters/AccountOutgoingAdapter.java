package com.kata.bankaccount.infrastructure.adapters;

import com.kata.bankaccount.domain.model.Account;
import com.kata.bankaccount.domain.ports.outgoing.AccountOutguoingPort;
import com.kata.bankaccount.infrastructure.dao.H2.JpaRepository.AccountJpARepository;
import com.kata.bankaccount.infrastructure.dao.H2.dao.AccountEntity;
import com.kata.bankaccount.infrastructure.dao.H2.mapper.AccountMapper;
import com.kata.bankaccount.infrastructure.errors.DatabaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountOutgoingAdapter implements AccountOutguoingPort {

    private final AccountJpARepository accountJpARepository;

    private final AccountMapper accountMapper;

    @Override
    public Account getAccountById(Long accountId) {
        return accountJpARepository
                .findById(accountId)
                .map(accountMapper::fromEntity)
                .orElseThrow(() -> new DatabaseException(HttpStatus.BAD_REQUEST, String.format("Account with id %s is not found", accountId)));
    }

    @Override
    @Cacheable(cacheNames = "account", key = "{#account.id, #account.balance}", unless = "#result == null")
    public Account saveAccount(Account account) {
        AccountEntity entity = accountMapper.toEntity(account);
        // Set Account for transactions
        entity.getTransactions().forEach(transactionEntity -> transactionEntity.setAccount(entity));
        return accountMapper.fromEntity(accountJpARepository.save(entity));
    }
}
