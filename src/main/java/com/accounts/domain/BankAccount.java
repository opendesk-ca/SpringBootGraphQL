package com.accounts.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@Data
@Builder
public class BankAccount {
    private  Integer id;
    private Client client;
    private Currency currency;
    private Float balance;
    private String status;
}
