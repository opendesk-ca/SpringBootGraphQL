package com.accounts.controller;

import com.accounts.domain.BankAccount;
import com.accounts.domain.BankAccountInput;
import com.accounts.domain.Client;
import com.accounts.domain.ClientInput;
import com.accounts.service.BankService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.*;

@Controller
@Slf4j
public class AccountsController {

    @Autowired
    BankService bankService;

    @QueryMapping
    Set<BankAccount> accounts (){
        log.info("Getting Accounts ");
        return bankService.getAccounts();
    }

    @BatchMapping( field = "client", typeName = "BankAccountType" )
    Map<BankAccount, Client> getClient (List<BankAccount> accounts){
        log.info("Getting client for Accounts : " + accounts.size());

        Map<BankAccount, Client> clentsMap = new HashMap<>();

        accounts.stream().forEach(act->{
            clentsMap.put(act, act.getClient());
        });

        return clentsMap;
    }

    @MutationMapping ()
    BankAccount addAccount (@Argument("account") BankAccountInput accountInput) {
        log.info("Saving Account : " + accountInput);
        BankAccount saved = bankService.save(convertToDomainModel (accountInput));
        return saved;
    }

    private BankAccount convertToDomainModel(BankAccountInput accountInput) {
        Optional<Integer> lastAccountId = bankService.getAccounts()
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
        Optional<Integer> lastClientId = bankService.getAccounts()
                .stream().map(a->a.getClient().getId()).max(Integer::compareTo);
        Integer nextClientId = lastClientId.isPresent() ?  lastClientId.get() + 1 : 1;

        return Client.builder().id(nextClientId)
                .lastName(clientInput.getLastName())
                .middleName(clientInput.getMiddleName())
                .firstName(clientInput.getFirstName())
                .build();
    }
}

/*
mutation {
  addAccount(account: {
    client: {
      firstName: "John",
      middleName: "A.",
      lastName: "Doe"
    },
    currency: USD,
    balance: 1000.50,
    status: "Active"
  }) {
    id
    client {
      id
      firstName
      middleName
      lastName
    }
    currency
    balance
    status
  }
}

query  {
  accounts {
    id
    currency
    balance
    status
    client{
      id
      firstName
      lastName
    }
  }
}
 */