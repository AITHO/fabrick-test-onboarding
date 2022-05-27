package it.aitho.fabrickonboarding.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AccountBalanceDto extends FabrickGenericResponseDto{
    private AccountBalancePayload payload;
}
