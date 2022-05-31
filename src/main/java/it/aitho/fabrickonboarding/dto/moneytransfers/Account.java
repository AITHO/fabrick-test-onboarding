package it.aitho.fabrickonboarding.dto.moneytransfers;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class Account {
    @NotNull
    private String accountCode;
    private String bicCode;
}
