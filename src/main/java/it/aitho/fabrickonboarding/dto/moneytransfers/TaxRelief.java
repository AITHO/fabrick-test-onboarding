package it.aitho.fabrickonboarding.dto.moneytransfers;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Data
public class TaxRelief {
    private String taxReliefId;
    @Accessors(fluent = true)
    @JsonProperty(value="isCondoUpgrade")
    private boolean isCondoUpgrade;
    @NotNull
    private String creditorFiscalCode;
    @NotNull
    private BeneficiaryType beneficiaryType;
    private NaturalPersonBeneficiary naturalPersonBeneficiary;
    private LegalPersonBeneficiary legalPersonBeneficiary;
}
