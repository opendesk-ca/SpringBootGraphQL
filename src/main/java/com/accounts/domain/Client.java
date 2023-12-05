package com.accounts.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class Client {
    private Integer id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String country;
}
