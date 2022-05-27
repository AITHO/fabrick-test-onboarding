package it.aitho.fabrickonboarding.dto;

import lombok.Data;

@Data
public class ValidationErrorsDto {
    private final String fieldName;
    private final String error;
}
