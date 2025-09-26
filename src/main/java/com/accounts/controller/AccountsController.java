package com.accounts.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class AccountsController {
    /*@Autowired
    BankService bankService;

    @QueryMapping
    List<BankAccount> accounts (){
        log.info("Getting Accounts ");
        return bankService.getAccounts();
    }

    @SchemaMapping (typeName = "BankAccount", field = "client")
    Client getClient (BankAccount account) {
        log.info("Getting client for " + account.getId());
        return bankService.getClientByAccountId(account.getId());
    }*/
}
