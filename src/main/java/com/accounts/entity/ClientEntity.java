package com.accounts.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "clients")
@Getter
@Setter
public class ClientEntity {
    @Id
    private String id;
    private String name;
    private String industry;
    private Float score;
    private LocalDate lastInteraction;
}
