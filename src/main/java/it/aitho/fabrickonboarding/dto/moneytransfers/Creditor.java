package it.aitho.fabrickonboarding.dto.moneytransfers;

import lombok.Data;

@Data
public class Creditor {
    private String name;
    private Account account;
    private Address address;
}
