package com.inditex.pricer.common.error.handling;

import com.inditex.pricer.common.error.handling.dto.GenericError;
import com.inditex.pricer.common.error.handling.exceptions.PriceNotFoundException;
import com.inditex.pricer.common.error.handling.exceptions.StrategyNotImplementedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class ControllerErrorAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = PriceNotFoundException.class)
    protected ResponseEntity<GenericError> handlePriceNotFoundException(PriceNotFoundException ex){
        log.error(ex.getMessage());
        return new ResponseEntity<>(GenericError.builder().code(1000).errorMessage(ex.getMessage()).build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = StrategyNotImplementedException.class)
    protected ResponseEntity<GenericError> handleStrategyNotImplementedException(StrategyNotImplementedException ex){
        log.error(ex.getMessage());
        return new ResponseEntity<>(GenericError.builder().code(1001).errorMessage(ex.getMessage()).build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
