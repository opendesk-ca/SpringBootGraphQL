package com.accounts.controller;

import com.accounts.entity.DepositAccount;
import com.accounts.service.DepositService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@Slf4j
public class AccountsController {

    @Autowired
    DepositService depositService;

    @QueryMapping
    public List<DepositAccount> accounts (){
        log.info("Getting Accounts ");
        return depositService.getAccounts();
    }

    @QueryMapping
    public DepositAccount accountById (@Argument("accountId")  Long accountId){
        log.info("Getting Account ");
        return depositService.accountById(accountId);
    }

/*    @BatchMapping( field = "client", typeName = "BankAccount" )
    public Map<DepositAccount, Client> clients(List<DepositAccount> depositAccounts) {
        log.info("Getting client for Accounts : " + depositAccounts);
        return depositService.getAccountClientMap(depositAccounts);
    }*/

  /*  @BatchMapping( field = "branch", typeName = "BankAccount" )
    public Map<DepositAccount, Branch> branches(List<DepositAccount> depositAccounts) {
        log.info("Getting branch for Accounts : " + depositAccounts);
        return depositService.getAccountBranchMap(depositAccounts);
    }*/

    @MutationMapping
    public Boolean addAccount (@Argument("account") DepositAccount account)  {
        log.info("Saving Account : " + account);
        depositService.save(account);
        return true;
    }

    @MutationMapping
    public DepositAccount editAccount (@Argument("account") DepositAccount account) {
        log.info("Editing Account : " + account);
        return depositService.modify(account);
    }

    @MutationMapping
    public Boolean deleteAccount (@Argument("id") Long accountId) {
        log.info("Deleting Account : " + accountId);
        return depositService.delete(accountId);
    }
}