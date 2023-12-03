package com.accounts.controller;

import com.accounts.domain.BankAccount;
import com.accounts.domain.Client;
import com.accounts.service.BankService;
import com.accounts.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @BatchMapping( field = "client")
    Map<BankAccount, Client> getClient (List<BankAccount> accounts){
        log.info("Getting client for Accounts : " + accounts.size());

        Map<BankAccount, Client> clentsMap = new HashMap<>();

        accounts.stream().forEach(act->{
            Client aClient = clientService.getClientByAccountId(act.getId());
            clentsMap.put(act, aClient);
        });

        return clentsMap;
    }
}
