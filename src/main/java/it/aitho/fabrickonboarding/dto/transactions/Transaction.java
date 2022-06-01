package it.aitho.fabrickonboarding.dto.transactions;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class Transaction {
    private String transactionId;
    private String operationId;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate accountingDate;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate valueDate;
    private TransactionType type;
    private double amount;
    private String currency;
    private String description;
}
