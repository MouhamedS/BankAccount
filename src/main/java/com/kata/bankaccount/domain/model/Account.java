package com.kata.bankaccount.domain.model;

import com.kata.bankaccount.domain.error.AccountThresholdException;
import com.kata.bankaccount.domain.error.AccountTransactionException;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
public class Account {

    private String accountReferenceNumber;

    private String clientReferenceNumber;

    private BigDecimal balance;

    private BigDecimal overdraftThreshold;

    public Account() {
    }

    public Account(String accountReferenceNumber, String clientReferenceNumber, BigDecimal balance, BigDecimal overdraftThreshold) {
        this.accountReferenceNumber = accountReferenceNumber;
        this.clientReferenceNumber = clientReferenceNumber;
        this.balance = balance;
        this.overdraftThreshold = overdraftThreshold;
    }

    public Account deposit(BigDecimal amount) {
        if (isAmountNegative(amount)) {
            throw new AccountTransactionException("Cannot deposit an amount less than 0");
        }

        balance = balance.add(amount);
        return new Account(this.accountReferenceNumber, this.clientReferenceNumber, balance, overdraftThreshold);
    }

    public Account withdraw(BigDecimal amount) {
        if (isAmountNegative(amount)) {
            throw new AccountTransactionException("Cannot withdraw an amount less than 0");
        }

        boolean isAmountGreaterThanBalancePlusOverdraft = balance.add(overdraftThreshold).compareTo(amount) < 0;
        if (isAmountGreaterThanBalancePlusOverdraft) {
            throw new AccountThresholdException("Account overdraft threshold has been reached");
        }
        balance = balance.subtract(amount);

        return new Account(this.accountReferenceNumber, this.clientReferenceNumber, balance, overdraftThreshold);
    }

    private static boolean isAmountNegative(BigDecimal amount) {
        return amount.compareTo(BigDecimal.ZERO) < 0;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountReferenceNumber='" + accountReferenceNumber + '\'' +
                ", balance=" + balance +
                ", overdraftThreshold=" + overdraftThreshold +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(accountReferenceNumber, account.accountReferenceNumber)
                && Objects.equals(clientReferenceNumber, account.clientReferenceNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountReferenceNumber, clientReferenceNumber);
    }
}
