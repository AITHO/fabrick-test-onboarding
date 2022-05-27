package it.aitho.fabrickonboarding.dto;

import lombok.Data;

@Data
public class AccountBalancePayload {
    private String date;
    private double balance;
    private double availableBalance;
    private String currency;
}
