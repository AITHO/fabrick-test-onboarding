package it.aitho.fabrickonboarding.dto;

import it.aitho.fabrickonboarding.enums.FabrickResponseStatus;
import lombok.Data;

import java.util.List;

@Data
public class FabrickGenericResponseDto {
    private FabrickResponseStatus status;
    private List<FabrickErrorDto> errors;

}
