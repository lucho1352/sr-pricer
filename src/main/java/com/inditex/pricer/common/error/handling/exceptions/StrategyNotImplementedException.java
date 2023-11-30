package com.inditex.pricer.common.error.handling.exceptions;

public class StrategyNotImplementedException extends RuntimeException{
    public StrategyNotImplementedException(String errorMessage){
        super(errorMessage);
    }
}
