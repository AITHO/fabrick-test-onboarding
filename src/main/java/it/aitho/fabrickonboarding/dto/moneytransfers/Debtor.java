package it.aitho.fabrickonboarding.dto.moneytransfers;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class Debtor {
    private String name;
    @NotNull
    private Account account;
}
