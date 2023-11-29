package com.inditex.pricer.service;

import com.inditex.pricer.dto.PriceRequest;
import com.inditex.pricer.dto.PriceResponse;

public interface PriceCalculator {

    /**
     * Calculates a price base on price object
     * @param price object that holds all attributes required to calculate the price
     * @return PriceResponse with finalPrice applied for the given price
     */
    PriceResponse calculatePrice(PriceRequest price);

}
