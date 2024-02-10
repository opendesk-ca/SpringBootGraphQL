package com.accounts.service;

import com.accounts.entity.DepositAccount;
import com.accounts.repo.DepositAccountRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DepositService {
    @Autowired
    DepositAccountRepo repo;

    public void save(DepositAccount account) {
        repo.save(account);
    }

    public DepositAccount modify(DepositAccount account) {
        repo.save(account);
        return account;
    }

    public List<DepositAccount> getAccounts() {
        return repo.findAll();
    }

    public DepositAccount accountById(Long accountId) {
        return repo.findById(accountId).get();
    }

    public Boolean delete(Long accountId) {
        if (repo.findById(accountId).isPresent()){
            repo.delete(repo.findById(accountId).get());
            return true;
        }
        return false;
    }
}