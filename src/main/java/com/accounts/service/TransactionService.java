package com.accounts.service;

import com.accounts.entity.DepositTransaction;
import com.accounts.repo.DepositTransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    DepositTransactionRepo transactionRepository;

    @Autowired
    DepositService bankService;

    public void save(DepositTransaction transaction) {
        transactionRepository.save(transaction);

       /* processTransaction (transaction.getAccountId(), TxnType.valueOf(transaction.getTransactionType()), transaction.getAmount());*/
    }

    public List<DepositTransaction>  transactionsByAccountId(Long accountId) {
            return transactionRepository.findByAccountId(accountId);
    }


   /* public void processTransaction(Long accountId, TxnType txnType, float amount) throws CustomGraphQLError {
        String result = switch (txnType) {
            case DEPOSIT -> handleDeposit(accountId, amount);
            case WITHDRAWAL -> handleWithdrawal(accountId, amount);
            case TRANSFER -> handleTransfer(accountId, amount);
            default -> handleUnknownTransaction();
        };
        System.out.println(result);
    }*/

    /*rivate String handleDeposit(Long accountId, float amount) {

        BankAccount account = bankService.accountById(accountId);
        account.setBalance(account.getBalance() +  amount);
        bankService.modify(account);

        return "Processing deposit of $" + amount + " to accountId ";
    }*/

    /*private String handleWithdrawal(Long accountId, float amount) {
        BankAccount account = bankService.accountById(accountId);
        account.setBalance(account.getBalance() - amount);
        bankService.modify(account);

        return "Processing withdrawal of $"  + " to accountId ";
    }*/

    /*private String handleTransfer(Long accountId, float amount) throws CustomGraphQLError {
        throw new CustomGraphQLError("handleTransfer functionality is not available now");
    }*/

    /*private String handleUnknownTransaction() throws CustomGraphQLError {
        throw new CustomGraphQLError("handleUnknownTransaction functionality is not available now");
    }*/
}
