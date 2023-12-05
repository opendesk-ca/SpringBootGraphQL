package com.accounts.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BankAccountInput {
    private ClientInput client;
    private Currency currency;
    private Float balance;
    private String status;
    private Float transferLimit;
    private LocalDateTime accountCreateDate;
}
