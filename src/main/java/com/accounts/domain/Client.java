package com.accounts.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Client {
    private String id;
    private String name;
    private String industry;
    private String lastInteractionDate;
    private int openDeals;
    private float score;
    private List<RankingFactor> rankingFactors;

    // Instead of Contact objects, keep only IDs
    private List<String> contactIds;
}
