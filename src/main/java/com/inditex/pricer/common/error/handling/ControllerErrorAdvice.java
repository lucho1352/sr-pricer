package com.inditex.pricer.common.error.handling;

import com.inditex.pricer.common.error.handling.dto.GenericError;
import com.inditex.pricer.common.error.handling.exceptions.PriceNotFound;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class ControllerErrorAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = PriceNotFound.class)
    protected ResponseEntity<GenericError> handlePriceNotFound(PriceNotFound ex, WebRequest request){
        log.error(ex.getMessage());
        return new ResponseEntity<>(GenericError.builder().code(1000).errorMessage(ex.getMessage()).build(), HttpStatus.NOT_FOUND);
    }

}
