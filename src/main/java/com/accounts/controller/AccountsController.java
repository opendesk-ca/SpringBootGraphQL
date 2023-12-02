package com.accounts.controller;

import com.accounts.domain.BankAccount;
import com.accounts.domain.Client;
import com.accounts.service.BankService;
import com.accounts.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@Slf4j
public class AccountsController {

    @Autowired
    BankService bankService;

    @Autowired
    ClientService clientService;
    @QueryMapping
    List<BankAccount> accounts (){
        log.info("Getting Accounts ");
        return bankService.getAccounts();
    }

    @SchemaMapping (typeName = "BankAccount", field = "client")
    Client getClient (BankAccount account) {
        return clientService.getClientByAccountId(account.getId());
    }
}

/*
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