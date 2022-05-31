package it.aitho.fabrickonboarding.dto.accountbalance;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AccountBalancePayload {
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate date;
    private double balance;
    private double availableBalance;
    private String currency;
}
