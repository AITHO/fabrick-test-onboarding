package it.aitho.fabrickonboarding.dto.accountbalance;

import it.aitho.fabrickonboarding.dto.FabrickGenericResponseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AccountBalanceResponseDto extends FabrickGenericResponseDto{
    private AccountBalancePayload payload;
}
