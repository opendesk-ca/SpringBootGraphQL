package com.accounts.service;

import com.accounts.domain.BankAccount;
import com.accounts.domain.BankAccountInput;
import com.accounts.domain.Client;
import com.accounts.domain.ClientInput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Optional;
import java.util.SortedSet;
import java.util.TreeSet;

@Service
@Slf4j
public class BankService {

    private static SortedSet<BankAccount> bankAccounts = new TreeSet<>(Comparator.comparing(BankAccount::getId));
    public SortedSet<BankAccount> getAccounts() {
        return bankAccounts;
    }

    public BankAccount save(BankAccountInput accountInput) {
        BankAccount account = convertToDomainModel (accountInput);
        bankAccounts.add(account);
        return account;
    }

    private BankAccount convertToDomainModel(BankAccountInput accountInput) {
        Optional<Integer> lastAccountId = getAccounts()
                .stream().map(a->a.getId()).max(Integer::compareTo);
        Integer nextAccountId = lastAccountId.isPresent() ? lastAccountId.get() + 1 : 1;

        Client client = convertClientInputToClient (accountInput.getClient());

        BankAccount bankAccount = BankAccount.builder()
                .id(nextAccountId)
                .balance(accountInput.getBalance())
                .currency(accountInput.getCurrency())
                .status(accountInput.getStatus())
                .client(client).build();

        return bankAccount;
    }

    private Client convertClientInputToClient(ClientInput clientInput) {
        Optional<Integer> lastClientId = getAccounts()
                .stream().map(a->a.getClient().getId()).max(Integer::compareTo);
        Integer nextClientId = lastClientId.isPresent() ?  lastClientId.get() + 1 : 1;

        return Client.builder().id(nextClientId)
                .lastName(clientInput.getLastName())
                .middleName(clientInput.getMiddleName())
                .firstName(clientInput.getFirstName())
                .build();
    }
}