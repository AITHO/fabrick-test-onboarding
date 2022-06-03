package it.aitho.fabrickonboarding.dto.moneytransfers;

import it.aitho.fabrickonboarding.dto.FabrickGenericResponseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MoneyTransfersResponseDto extends FabrickGenericResponseDto {
    private MoneyTransfersPayload payload;
}
