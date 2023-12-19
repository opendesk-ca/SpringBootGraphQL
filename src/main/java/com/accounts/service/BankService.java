package com.accounts.service;

import com.accounts.entity.BankAccount;
import com.accounts.domain.Client;
import com.accounts.repo.BankAccountRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BankService {

    private static List<Client> clients = Arrays.asList(
            new Client(100L,  "John", "T.", "Doe"),
            new Client(101L, "Emma", "B.", "Smith"),
            new Client(102L, "James", "R.", "Brown"),
            new Client(103L, "Olivia", "S.", "Johnson"),
            new Client(104L, "William", "K.", "Jones")
    );
    @Autowired
    BankAccountRepo repo;

    public void save(BankAccount account) {
        repo.save(account);
    }

    public List<BankAccount> getAccounts() {
        return repo.findAll();
    }

    private List<Client> getClients () {
        return clients;
    }

    public Map<BankAccount, Client> getBankAccountClientMap(List<BankAccount> bankAccounts) {
        // Collect all client IDs from the list of bank accounts
        Set<Long> clientIds = bankAccounts.stream()
                .map(BankAccount::getClientId)
                .collect(Collectors.toSet());

        // Fetch client for all collected IDs
        List<Client> clients = getClients ().stream()
                .filter(client -> clientIds.contains(client.getId()))
                .collect(Collectors.toList());

        // Map each bank account to its corresponding client
        return clients.stream()
                .collect(Collectors.toMap(
                        client -> bankAccounts.stream()
                                .filter(bankAccount -> bankAccount.getClientId().equals(client.getId()))
                                .findFirst()
                                .orElse(null),
                        client -> client
                ));
    }
}