package com.accounts.entity;

import com.accounts.domain.DepositTransactionType;
import com.accounts.domain.TransactionStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Transaction")
public class DepositTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private	Long	                transactionId	;

    @Column
    private	Long	                accountId	;

    @Column
    private	LocalDateTime	        postedTimestamp	;

    @Column
    private	LocalDateTime	        transactionTimestamp	;

    @Column
    private	String	                description	;

    @Column
    private TransactionStatus       status	;

    @Column
    private	Float	                amount	;

    @Column
    private DepositTransactionType  transactionTyp	;

    @Column
    private	String	                payee	;

    @Column
    private	Integer	                checkNumber	;
}
