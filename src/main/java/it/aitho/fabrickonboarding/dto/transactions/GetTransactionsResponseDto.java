package it.aitho.fabrickonboarding.dto.transactions;

import it.aitho.fabrickonboarding.dto.FabrickGenericResponseDto;
import lombok.Data;

@Data
public class GetTransactionsResponseDto extends FabrickGenericResponseDto {
    private GetTransactionsPayload payload;
}
