package it.aitho.fabrickonboarding.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.aitho.fabrickonboarding.dto.FabrickGenericResponseDto;
import it.aitho.fabrickonboarding.dto.ValidationErrorResponseDto;
import it.aitho.fabrickonboarding.dto.ValidationErrorsDto;
import it.aitho.fabrickonboarding.enums.FabrickResponseStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;

@ControllerAdvice
@Slf4j
public class ErrorHandlingControllerAdvice {

    @Autowired
    private MappingJackson2HttpMessageConverter springMvcJacksonConverter;

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ValidationErrorResponseDto onConstraintValidationException(
            ConstraintViolationException e) {
        log.error("ConstraintViolationException", e);
        ValidationErrorResponseDto error = initValidationErrorResponseDto();
        for (var violation : e.getConstraintViolations()) {
            error.getErrors().add(
                    new ValidationErrorsDto(violation.getPropertyPath().toString(), violation.getMessage()));
        }
        return error;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ValidationErrorResponseDto onMethodArgumentNotValidException(
            MethodArgumentNotValidException e) {
        log.error("MethodArgumentNotValidException", e);
        ValidationErrorResponseDto error = initValidationErrorResponseDto();
        for (var fieldError : e.getBindingResult().getFieldErrors()) {
            error.getErrors().add(
                    new ValidationErrorsDto(fieldError.getField(), fieldError.getDefaultMessage()));
        }
        return error;
    }

    @ExceptionHandler({HttpServerErrorException.class, HttpClientErrorException.class})
    ResponseEntity<FabrickGenericResponseDto> onServerErrorException(
            HttpStatusCodeException ex) {
        log.error("HttpStatusCodeException", ex);
        ObjectMapper objectMapper = springMvcJacksonConverter.getObjectMapper();
        FabrickGenericResponseDto errorResponse = new FabrickGenericResponseDto();
        try {
            errorResponse = objectMapper.readValue(ex.getResponseBodyAsString(), FabrickGenericResponseDto.class);
        } catch (JsonProcessingException e) {
            errorResponse.setStatus(FabrickResponseStatus.KO);
        }

        return ResponseEntity.status(ex.getStatusCode()).body(errorResponse);
    }

    private ValidationErrorResponseDto initValidationErrorResponseDto() {
        var error = new ValidationErrorResponseDto();
        error.setErrors(new ArrayList<>());
        return error;
    }

}