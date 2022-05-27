package it.aitho.fabrickonboarding.dto;

import lombok.Data;

@Data
public class FabrickErrorDto {
    private String code;
    private String description;
    private String params;

}
