package it.aitho.fabrickonboarding.dto.moneytransfers;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class Creditor {
    @NotNull
    private String name;
    @NotNull
    private Account account;
    private Address address;
}
