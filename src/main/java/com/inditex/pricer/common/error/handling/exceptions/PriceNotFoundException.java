package com.inditex.pricer.common.error.handling.exceptions;

public class PriceNotFoundException extends RuntimeException{

    public PriceNotFoundException(String errorMessage){
        super(errorMessage);
    }
}
