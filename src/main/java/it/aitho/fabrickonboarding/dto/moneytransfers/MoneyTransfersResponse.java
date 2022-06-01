package it.aitho.fabrickonboarding.dto.moneytransfers;

import it.aitho.fabrickonboarding.dto.FabrickGenericResponseDto;
import lombok.Data;

@Data
public class MoneyTransfersResponse extends FabrickGenericResponseDto {
    private MoneyTransfersPayloadDto payload;
}
