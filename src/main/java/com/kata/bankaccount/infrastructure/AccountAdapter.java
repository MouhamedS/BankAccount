package com.kata.bankaccount.infrastructure;

import com.kata.bankaccount.domain.Account;
import com.kata.bankaccount.domain.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountAdapter implements AccountRepository {

    private AccountJpARepository accountJpARepository;
    @Override
    public Account getAccountById(Long accountId) {
        return accountJpARepository
                .findById(accountId)
                .orElseThrow(() -> new RuntimeException(String.format("Account with id %s not found", accountId)));
    }

    @Override
    public Account saveAccount(Account account) {
        return accountJpARepository.save(account);
    }
}
