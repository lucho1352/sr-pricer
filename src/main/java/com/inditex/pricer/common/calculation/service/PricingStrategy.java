package com.inditex.pricer.common.calculation.service;

import com.inditex.pricer.entity.Price;

import java.util.List;

public interface PricingStrategy {

    /**
     * Calculates the price of a product base on the brand logic
     * @param prices list of prices for which we want to determine the price
     * @return Price
     */
    Price calculatePrice(List<Price> prices);

}
