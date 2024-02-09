package com.accounts.repo;

import com.accounts.entity.DepositTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepositTransactionRepo extends JpaRepository<DepositTransaction, Long> {
    List<DepositTransaction> findByAccountId(Long accountId);
}
