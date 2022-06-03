package it.aitho.fabrickonboarding.dto.moneytransfers;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.aitho.fabrickonboarding.enums.DirectionEnum;
import it.aitho.fabrickonboarding.enums.FeeTypes;
import it.aitho.fabrickonboarding.enums.MoneyTransferStatusEnum;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Data
public class MoneyTransfersPayload {
    private String moneyTransferId;
    private MoneyTransferStatusEnum status;
    private DirectionEnum direction;
    private Creditor creditor;
    private Debtor debtor;
    private String cro;
    private String trn;
    private String uri;
    private String description;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSSXXXX", timezone = "UTC")
    private Instant createdDatetime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSSXXXX", timezone = "UTC")
    private Instant accountedDatetime;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate debtorValueDate;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate creditorValueDate;
    private Amount amount;
    @Accessors(fluent = true)
    @JsonProperty(value="isUrgent")
    private boolean isUrgent;
    @Accessors(fluent = true)
    @JsonProperty(value="isInstant")
    private boolean isInstant;
    private FeeTypes feeType;
    private String feeAccountID;
    private List<Fees> fees;
    @Accessors(fluent = true)
    @JsonProperty(value="hasTaxRelief")
    private boolean hasTaxRelief;

}
