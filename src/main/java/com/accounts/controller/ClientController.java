package com.accounts.controller;

import com.accounts.domain.CallList;
import com.accounts.domain.Client;
import com.accounts.domain.Contact;
import com.accounts.domain.PageInfo;
import com.accounts.service.StubDataLoader;
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
public class ClientController {

    @Autowired
    private StubDataLoader stubDataLoader;

    @QueryMapping
    public Client getClientById(@Argument String id) {
        return stubDataLoader.findClientById(id);
    }

    @QueryMapping
    public CallList getCallList() {
        List<Client> clients = stubDataLoader.getClients();
        PageInfo pageInfo = new PageInfo(1, false, false);
        return new CallList(clients, pageInfo);
    }

    // Batch resolver: given multiple Clients, fetch their contacts in one shot
    @BatchMapping
    public Map<Client, List<Contact>> contacts(List<Client> clients) {
        Map<String, Contact> contactMap = stubDataLoader.getContacts().stream()
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

