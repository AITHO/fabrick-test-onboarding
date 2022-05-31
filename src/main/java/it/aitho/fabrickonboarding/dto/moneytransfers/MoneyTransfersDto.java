package it.aitho.fabrickonboarding.dto.moneytransfers;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import it.aitho.fabrickonboarding.enums.FeeTypes;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class MoneyTransfersDto {
    @NotNull
    private Creditor creditor;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate executionDate;
    private String uri;
    @NotBlank
    private String description;
    @NotNull
    private Double amount;
    @NotBlank
    private String currency;
    private boolean isUrgent;
    private boolean isInstant;
    @JsonSetter(nulls = Nulls.SKIP)
    private FeeTypes feeType = FeeTypes.SHA;
    private String feeAccountId;
    private TaxRelief taxRelief;

}
