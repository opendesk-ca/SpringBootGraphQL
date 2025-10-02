package com.accounts.service;

import com.accounts.domain.Client;
import com.accounts.domain.Contact;
import com.accounts.domain.RankingFactor;
import com.accounts.domain.RecencyFactor;
import com.accounts.entity.ClientEntity;
import com.accounts.entity.ContactEntity;
import com.accounts.repo.ClientRepository;
import com.accounts.repo.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
@Slf4j
public class StubDbLoader {

    private final ClientRepository clientRepo;
    private final ContactRepository contactRepo;
    RankingFactor recency = new RecencyFactor("Recency", 0.7f, 15);

    public StubDbLoader(ClientRepository clientRepo, ContactRepository contactRepo) {
        this.clientRepo = clientRepo;
        this.contactRepo = contactRepo;
    }

    @Cacheable("clientById")  // caches by id
    public Client findClientById(String id) {
        log.info("Fetching Client {} from DB", id); // proves cache
        ClientEntity clientEntity = clientRepo.findById(id).orElseThrow();
        List<ContactEntity> contacts = contactRepo.findAll()
                .stream()
                .filter(c -> c.getClientId().equals(id))
                .toList();

        List<String> contactIds = contacts.stream().map(c -> c.getId()).toList();

        Client client = Client.builder().id(clientEntity.getId())
                .name(clientEntity.getName())
                .industry(clientEntity.getIndustry())
                .lastInteractionDate(clientEntity.getLastInteraction().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .rankingFactors(List.of(recency))
                .score(70f + (ThreadLocalRandom.current().nextInt(50, 101) % 20))
                .contactIds(contactIds)
                .build();

        log.info("Client for the Id : " + id);
        return client;
    }

    @Cacheable("clients")  // caches whole list
    public List<Client> getClients() {
        log.info("Fetching all clients from DB"); // proves cache
        List<ClientEntity> clients = clientRepo.findAll();
        List<ContactEntity> contacts = contactRepo.findAll();

        log.info("clients : " + clients.size() + " with " + contacts.size() + " contacts");

        List<Client> clientList = clients.stream().map(c->Client.builder().id(c.getId())
                .name(c.getName())
                .industry(c.getIndustry())
                .lastInteractionDate(c.getLastInteraction().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .rankingFactors(List.of(recency))
                .score(70f + (ThreadLocalRandom.current().nextInt(50, 101) % 20))
                .contactIds(contacts.stream().filter(co->co.getClientId().equals(c.getId())).map(co->co.getId()).toList())
                .build()).toList();

        log.info("clientList : " + clientList);
        return clientList;
    }

    public List<Contact> getContacts() {
        log.info("Fetching contacts from DB");
        List<ContactEntity> contacts = contactRepo.findAll();
        List<Contact> contactList = contacts.stream().map(
                c->Contact.builder().id(c.getId()).
                        firstName(c.getFirstName())
                        .lastName(c.getLastName())
                        .email(c.getEmail())
                        .phone(c.getPhone())
                        .preferredContactMethod(c.getPreferredContact())
                        .role(c.getRole())
                        .build()).toList();

        log.info("contactList : " + contactList);
        return contactList;
    }
}
