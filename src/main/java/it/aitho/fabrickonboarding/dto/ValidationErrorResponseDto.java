package it.aitho.fabrickonboarding.dto;

import lombok.Data;

import java.util.List;

@Data
public class ValidationErrorResponseDto {
    private List<ValidationErrorsDto> errors;

}
