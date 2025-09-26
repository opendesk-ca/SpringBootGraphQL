package com.accounts.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RecencyFactor implements RankingFactor {
    private String factorName;
    private float contributionToScore;
    private int daysSinceLastInteraction;
}