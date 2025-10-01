package com.accounts.controller;

import com.accounts.domain.CallList;
import com.accounts.domain.Client;
import com.accounts.domain.Contact;
import com.accounts.domain.PageInfo;
import com.accounts.service.StubDataLoader;
import com.accounts.service.StubDbLoader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller
@Slf4j
public class ClientController {

    @Autowired
    private StubDataLoader stubDataLoader;

    @Autowired
    private StubDbLoader stubDbLoader;

    @QueryMapping
    public Client getClientById(@Argument String id) {
        return stubDbLoader.findClientById(id);
    }

    @QueryMapping
    public CallList getCallList() {
        log.info("Getting Call List - 1");
        List<Client> clients = stubDbLoader.getClients();
        PageInfo pageInfo = new PageInfo(1, false, false);
        return new CallList(clients, pageInfo);
    }

    // Batch resolver: given multiple Clients, fetch their contacts in one shot
    @BatchMapping
    public Map<Client, List<Contact>> contacts(List<Client> clients) {
        log.info("Getting contacts for - " + clients.size() + " Clients");
        Map<String, Contact> contactMap = stubDbLoader.getContacts().stream()
                .collect(Collectors.toMap(Contact::getId, c -> c));

        return clients.stream().collect(Collectors.toMap(
                client -> client,
                client -> client.getContactIds().stream()
                        .map(contactMap::get)
                        .filter(Objects::nonNull)
                        .toList()
        ));
    }
}

