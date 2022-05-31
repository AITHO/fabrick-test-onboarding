package it.aitho.fabrickonboarding.dto.accountbalance;

import it.aitho.fabrickonboarding.dto.FabrickGenericResponseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AccountBalanceDto extends FabrickGenericResponseDto{
    private AccountBalancePayload payload;
}
