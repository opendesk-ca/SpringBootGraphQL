package com.accounts.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "contacts")
@Getter
@Setter
public class ContactEntity {
    @Id
    private String id;
    private String clientId;
    private String firstName;
    private String lastName;
    private String role;
    private String email;
    private String phone;
    private String preferredContact;
}

