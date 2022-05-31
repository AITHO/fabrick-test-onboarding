package it.aitho.fabrickonboarding.dto.moneytransfers;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
public class TaxRelief {
    private String taxReliefId;
    private boolean isCondoUpgrade;
    private String creditorFiscalCode;
    private BeneficiaryType beneficiaryType;
    private NaturalPersonBeneficiary naturalPersonBeneficiary;
    private LegalPersonBeneficiary legalPersonBeneficiary;
}
