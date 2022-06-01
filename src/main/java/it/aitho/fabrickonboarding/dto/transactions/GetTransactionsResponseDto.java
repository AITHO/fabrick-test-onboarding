package it.aitho.fabrickonboarding.dto.transactions;

import lombok.Data;

import java.util.List;

@Data
public class GetTransactionsResponseDto {
    private List<Transaction> list;

}
