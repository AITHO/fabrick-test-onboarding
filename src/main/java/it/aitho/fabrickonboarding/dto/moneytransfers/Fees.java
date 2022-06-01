package it.aitho.fabrickonboarding.dto.moneytransfers;

import lombok.Data;

@Data
public class Fees {
    private String feeCode;
    private String description;
    private double amount;
    private String currency;

}
