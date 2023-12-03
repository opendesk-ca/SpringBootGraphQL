package com.accounts.service;

import com.accounts.domain.BankAccount;
import com.accounts.domain.Client;
import com.accounts.domain.Currency;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class ClientService {

    private List <Client> clients = Collections.emptyList();

    public Client getClientByAccountId (String accountId) {
        log.info("Getting getClientByAccountId : " + accountId);
        return clients.stream().filter(c->c.getAccountId().equals(accountId)).findFirst().orElse(null);
    }
}
