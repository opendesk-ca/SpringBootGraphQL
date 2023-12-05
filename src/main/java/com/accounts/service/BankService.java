package com.accounts.service;

import com.accounts.domain.BankAccount;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class BankService {

    private static SortedSet<BankAccount> bankAccounts = new TreeSet<>(Comparator.comparing(BankAccount::getId));

    public SortedSet<BankAccount> getAccounts() {
        return bankAccounts;
    }

    public BankAccount save(BankAccount account) {
        bankAccounts.add(account);
        return account;
    }

    public Optional<BankAccount> getAccounts(Integer accountId) {
        return  bankAccounts.stream().filter(a->a.getId().equals(accountId)).findFirst();
    }
}