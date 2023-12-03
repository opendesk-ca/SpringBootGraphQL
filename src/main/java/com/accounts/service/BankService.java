package com.accounts.service;

import com.accounts.domain.BankAccount;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class BankService {
    private static List<BankAccount> bankAccounts = Collections.emptyList();

    public List<BankAccount> getAccounts() {
        return bankAccounts;
    }
}
