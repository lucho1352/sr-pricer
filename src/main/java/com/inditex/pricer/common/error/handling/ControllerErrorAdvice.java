package com.inditex.pricer.common.error.handling;

import com.inditex.pricer.common.error.handling.dto.GenericError;
import com.inditex.pricer.common.error.handling.exceptions.PriceNotFoundException;
import com.inditex.pricer.common.error.handling.exceptions.StrategyNotImplementedException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.stream.Collectors;

@Slf4j
@Order(1)
@ControllerAdvice
public class ControllerErrorAdvice {

    @ExceptionHandler(value = PriceNotFoundException.class)
    protected ResponseEntity<GenericError> handlePriceNotFoundException(PriceNotFoundException ex){
        log.error("Add corresponding price to the PRICES table");
        return new ResponseEntity<>(GenericError.builder().code(1000).errorMessage(ex.getMessage()).build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = StrategyNotImplementedException.class)
    protected ResponseEntity<GenericError> handleStrategyNotImplementedException(StrategyNotImplementedException ex){
        log.error(ex.getMessage());
        return new ResponseEntity<>(GenericError.builder().code(1001).errorMessage(ex.getMessage()).build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GenericError> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(", "));
        log.error(message);
        return new ResponseEntity<>(GenericError.builder().code(1002).errorMessage(message).build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<GenericError> handleConstraintViolationException(ConstraintViolationException ex) {
        String message = ex.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(", "));
        log.error(message);
        return new ResponseEntity<>(GenericError.builder().code(1003).errorMessage(message).build(), HttpStatus.BAD_REQUEST);
    }

}
