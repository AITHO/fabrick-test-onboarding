package it.aitho.fabrickonboarding.dto.accountbalance;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountBalancePayload {
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate date;
    private double balance;
    private double availableBalance;
    private String currency;
}
