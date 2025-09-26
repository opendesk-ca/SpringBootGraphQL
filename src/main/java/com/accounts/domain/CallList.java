package com.accounts.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CallList {
    private List<Client> clients;
    private PageInfo pageInfo;
}