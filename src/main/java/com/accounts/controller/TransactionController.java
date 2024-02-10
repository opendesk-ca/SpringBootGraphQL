package com.accounts.controller;

import com.accounts.entity.DepositTransaction;
import com.accounts.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@Slf4j
public class TransactionController {

    @Autowired
    TransactionService txnService;

    @QueryMapping
    public List<DepositTransaction> getTransactionById (@Argument("accountId")  Long accountId){
        log.info("Getting Account ");
        return txnService.transactionsByAccountId(accountId);
    }

    @MutationMapping
    public Boolean addTransaction (@Argument("transaction") DepositTransaction transaction) {
            txnService.save(transaction);
            return null;
    }
}
