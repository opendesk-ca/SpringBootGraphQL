package com.accounts.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PageInfo {
    private int totalCount;
    private boolean hasNextPage;
    private boolean hasPreviousPage;
}