package it.aitho.fabrickonboarding.dto.moneytransfers;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class NaturalPersonBeneficiary {
    @NotNull
    private String fiscalCode1;
    private String fiscalCode2;
    private String fiscalCode3;
    private String fiscalCode4;
    private String fiscalCode5;
}
