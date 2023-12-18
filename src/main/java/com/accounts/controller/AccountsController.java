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
import java.util.stream.Collectors;

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

        clentsMap = accounts.stream()
                .collect(Collectors.toMap(
                        account -> account, // Key Mapper
                        account -> account.getClient() // Value Mapper
                ));

        return clentsMap;
    }


    @MutationMapping ()
    BankAccount addAccount (@Argument("account") BankAccountInput accountInput) {
        log.info("Saving Account : " + accountInput);
        BankAccount saved = bankService.save(accountInput);
        return saved;
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