package com.accounts.service;

import com.accounts.domain.Client;
import com.accounts.domain.Contact;
import com.accounts.domain.RankingFactor;
import com.accounts.domain.RecencyFactor;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StubDataLoader {

    private final List<Client> clients = new ArrayList<>();
    private final List<Contact> contacts = new ArrayList<>();

    @PostConstruct
    public void init() {
        // --- Create 20 contacts ---
        contacts.add(new Contact("c1", "Alice", "Smith", "Manager", "alice@example.com", "1234567890", "EMAIL"));
        contacts.add(new Contact("c2", "Bob", "Johnson", "Director", "bob@example.com", "0987654321", "PHONE"));
        contacts.add(new Contact("c3", "Carol", "Williams", "Analyst", "carol@example.com", "5551234567", "EMAIL"));
        contacts.add(new Contact("c4", "David", "Brown", "VP Sales", "david@example.com", "5559876543", "PHONE"));
        contacts.add(new Contact("c5", "Emma", "Davis", "Consultant", "emma@example.com", "5556789123", "EMAIL"));
        contacts.add(new Contact("c6", "Frank", "Miller", "Advisor", "frank@example.com", "5552468101", "PHONE"));
        contacts.add(new Contact("c7", "Grace", "Wilson", "Executive", "grace@example.com", "5551357913", "EMAIL"));
        contacts.add(new Contact("c8", "Henry", "Moore", "Lead Engineer", "henry@example.com", "5558642097", "PHONE"));
        contacts.add(new Contact("c9", "Ivy", "Taylor", "Coordinator", "ivy@example.com", "5557539514", "EMAIL"));
        contacts.add(new Contact("c10", "Jack", "Anderson", "Director", "jack@example.com", "5559513578", "PHONE"));
        contacts.add(new Contact("c11", "Kelly", "Thomas", "Specialist", "kelly@example.com", "5556541230", "EMAIL"));
        contacts.add(new Contact("c12", "Leo", "Jackson", "Manager", "leo@example.com", "5558523697", "PHONE"));
        contacts.add(new Contact("c13", "Mia", "White", "Partner", "mia@example.com", "5551472583", "EMAIL"));
        contacts.add(new Contact("c14", "Noah", "Harris", "Director", "noah@example.com", "5553698521", "PHONE"));
        contacts.add(new Contact("c15", "Olivia", "Martin", "Engineer", "olivia@example.com", "5552581479", "EMAIL"));
        contacts.add(new Contact("c16", "Paul", "Thompson", "Supervisor", "paul@example.com", "5557896541", "PHONE"));
        contacts.add(new Contact("c17", "Quinn", "Garcia", "Consultant", "quinn@example.com", "5553216549", "EMAIL"));
        contacts.add(new Contact("c18", "Ruby", "Martinez", "Assistant", "ruby@example.com", "5554567890", "PHONE"));
        contacts.add(new Contact("c19", "Sam", "Robinson", "Engineer", "sam@example.com", "5559638527", "EMAIL"));
        contacts.add(new Contact("c20", "Tina", "Clark", "Lead Analyst", "tina@example.com", "5557418529", "PHONE"));


        RankingFactor recency = new RecencyFactor("Recency", 0.7f, 15);

        // --- Create 25 clients ---
        for (int i = 1; i <= 25; i++) {
            List<String> contactIds = List.of(
                    "c" + ((i % 20) + 1),
                    "c" + (((i + 5) % 20) + 1)
            );

            clients.add(new Client(
                    "client-" + i,
                    "Company " + i,
                    i % 2 == 0 ? "Finance" : "Technology",
                    "2025-09-" + String.format("%02d", (i % 30) + 1),
                    10 + i,
                    70f + (i % 20),
                    List.of(recency),
                    contactIds
            ));
        }
    }

    public List<Client> getClients() {
        return clients;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public Contact findContactById(String id) {
        return contacts.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Client findClientById(String id) {
        return clients.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}


/*
@Component
public class StubDataLoader {

    private final List<Client> clients = new ArrayList<>();
    private final List<Contact> contacts = new ArrayList<>();

    @PostConstruct
    public void init() {
        // --- Create 20 contacts ---
        contacts.add(new Contact("c1", "Alice", "Smith", "Manager", "alice@example.com", "1234567890", "EMAIL"));
        contacts.add(new Contact("c2", "Bob", "Johnson", "Director", "bob@example.com", "0987654321", "PHONE"));
        contacts.add(new Contact("c3", "Carol", "Williams", "Analyst", "carol@example.com", "5551234567", "EMAIL"));
        contacts.add(new Contact("c4", "David", "Brown", "VP Sales", "david@example.com", "5559876543", "PHONE"));
        contacts.add(new Contact("c5", "Emma", "Davis", "Consultant", "emma@example.com", "5556789123", "EMAIL"));
        contacts.add(new Contact("c6", "Frank", "Miller", "Advisor", "frank@example.com", "5552468101", "PHONE"));
        contacts.add(new Contact("c7", "Grace", "Wilson", "Executive", "grace@example.com", "5551357913", "EMAIL"));
        contacts.add(new Contact("c8", "Henry", "Moore", "Lead Engineer", "henry@example.com", "5558642097", "PHONE"));
        contacts.add(new Contact("c9", "Ivy", "Taylor", "Coordinator", "ivy@example.com", "5557539514", "EMAIL"));
        contacts.add(new Contact("c10", "Jack", "Anderson", "Director", "jack@example.com", "5559513578", "PHONE"));
        contacts.add(new Contact("c11", "Kelly", "Thomas", "Specialist", "kelly@example.com", "5556541230", "EMAIL"));
        contacts.add(new Contact("c12", "Leo", "Jackson", "Manager", "leo@example.com", "5558523697", "PHONE"));
        contacts.add(new Contact("c13", "Mia", "White", "Partner", "mia@example.com", "5551472583", "EMAIL"));
        contacts.add(new Contact("c14", "Noah", "Harris", "Director", "noah@example.com", "5553698521", "PHONE"));
        contacts.add(new Contact("c15", "Olivia", "Martin", "Engineer", "olivia@example.com", "5552581479", "EMAIL"));
        contacts.add(new Contact("c16", "Paul", "Thompson", "Supervisor", "paul@example.com", "5557896541", "PHONE"));
        contacts.add(new Contact("c17", "Quinn", "Garcia", "Consultant", "quinn@example.com", "5553216549", "EMAIL"));
        contacts.add(new Contact("c18", "Ruby", "Martinez", "Assistant", "ruby@example.com", "5554567890", "PHONE"));
        contacts.add(new Contact("c19", "Sam", "Robinson", "Engineer", "sam@example.com", "5559638527", "EMAIL"));
        contacts.add(new Contact("c20", "Tina", "Clark", "Lead Analyst", "tina@example.com", "5557418529", "PHONE"));


        RankingFactor recency = new RecencyFactor("Recency", 0.7f, 15);

        // --- Create 25 clients ---
        for (int i = 1; i <= 25; i++) {
            List<String> contactIds = List.of(
                "c" + ((i % 20) + 1),
                "c" + (((i + 5) % 20) + 1)
            );

            clients.add(new Client(
                    "client-" + i,
                    "Company " + i,
                    i % 2 == 0 ? "Finance" : "Technology",
                    "2025-09-" + String.format("%02d", (i % 30) + 1),
                    10 + i,
                    70f + (i % 20),
                    List.of(recency),
                    contactIds
            ));
        }
    }

    public List<Client> getClients() {
        return clients;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public Contact findContactById(String id) {
        return contacts.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Client findClientById(String id) {
        return clients.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}

 */