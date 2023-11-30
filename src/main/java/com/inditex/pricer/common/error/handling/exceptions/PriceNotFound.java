package com.inditex.pricer.common.error.handling.exceptions;

public class PriceNotFound extends RuntimeException{

    public PriceNotFound(String errorMessage){
        super(errorMessage);
    }
}
