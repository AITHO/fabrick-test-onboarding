package it.aitho.fabrickonboarding.dto.transactions;

import it.aitho.fabrickonboarding.dto.FabrickGenericResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetTransactionsResponseDto extends FabrickGenericResponseDto {
    private GetTransactionsPayload payload;
}
