package it.aitho.fabrickonboarding.handler;

import it.aitho.fabrickonboarding.dto.ValidationErrorResponseDto;
import it.aitho.fabrickonboarding.dto.ValidationErrorsDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;

@ControllerAdvice
public class ErrorHandlingControllerAdvice {

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ValidationErrorResponseDto onConstraintValidationException(
            ConstraintViolationException e) {
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
        ValidationErrorResponseDto error = initValidationErrorResponseDto();
        for (var fieldError : e.getBindingResult().getFieldErrors()) {
            error.getErrors().add(
                    new ValidationErrorsDto(fieldError.getField(), fieldError.getDefaultMessage()));
        }
        return error;
    }

    private ValidationErrorResponseDto initValidationErrorResponseDto() {
        var error = new ValidationErrorResponseDto();
        error.setErrors(new ArrayList<>());
        return error;
    }

}