package com.kata.bankaccount.infrastructure.dao.h2;

import com.kata.bankaccount.domain.model.Account;
import com.kata.bankaccount.domain.ports.outputs.AccountRepository;
import com.kata.bankaccount.infrastructure.dao.h2.dao.AccountEntity;
import com.kata.bankaccount.infrastructure.dao.h2.jpa_repository.AccountJpaRepository;
import com.kata.bankaccount.infrastructure.dao.h2.mapper.AccountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AccountH2Adapter implements AccountRepository {

    private final AccountJpaRepository accountJpARepository;

    private final AccountMapper accountMapper;

    @Override
    @Cacheable(cacheNames = "account", key = "{#accountReferenceNumber}", unless = "#result == null")
    @Transactional(readOnly = true)
    public Optional<Account> getAccountByAccountReferenceNumber(String accountReferenceNumber) {
        return Optional.ofNullable(accountJpARepository
                .findAccountEntityByAccountReferenceNumber(accountReferenceNumber))
                .map(accountMapper::fromEntity);
    }

    @Override
    @CachePut(cacheNames = "account", key = "{#account.accountReferenceNumber, #account.balance}", unless = "#result == null")
    public Account saveAccount(Account account) {
        AccountEntity entity = accountMapper.toEntity(account);
        // Set Account for transactions
        entity.getTransactions().forEach(transactionEntity -> transactionEntity.setAccount(entity));
        return accountMapper.fromEntity(accountJpARepository.save(entity));
    }
}
