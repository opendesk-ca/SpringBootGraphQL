package com.accounts.service;

import com.accounts.domain.BankAccount;
import com.accounts.domain.Currency;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class BankService {

    private static List<BankAccount> bankAccounts = Arrays.asList(
            new BankAccount("A100", "C100", Currency.USD, 106.00f, "A"),
            new BankAccount("A101", "C200", Currency.CAD, 250.00f, "A"),
            new BankAccount("A102", "C300", Currency.CAD, 333.00f, "I"),
            new BankAccount("A103", "C400", Currency.EUR, 4000.00f, "A")
    );
    public List<BankAccount> getAccounts() {
        return bankAccounts;
    }
}
