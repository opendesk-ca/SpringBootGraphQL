package com.accounts.domain;

import lombok.Data;

@Data
public class BankAccountInput {
    private ClientInput client;
    private Currency currency;
    private Float balance;
    private String status;
}
