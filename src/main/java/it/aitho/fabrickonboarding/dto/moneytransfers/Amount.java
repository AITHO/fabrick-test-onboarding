package it.aitho.fabrickonboarding.dto.moneytransfers;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class Amount {
    private double debtorAmount;
    private String debtorCurrency;
    private double creditorAmount;
    private String creditorCurrency;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate creditorCurrencyDate;
    private double exchangeRate;
}
