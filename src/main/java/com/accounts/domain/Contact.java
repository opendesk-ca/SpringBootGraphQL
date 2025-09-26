package com.accounts.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Contact {
    private String id;
    private String firstName;
    private String lastName;
    private String role;
    private String email;
    private String phone;
    private String preferredContactMethod;
}
