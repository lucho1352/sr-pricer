package com.inditex.pricer.service.impl;

import com.inditex.pricer.dto.PriceRequest;
import com.inditex.pricer.dto.PriceResponse;
import com.inditex.pricer.service.PriceCalculator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PriceCalculatorService implements PriceCalculator {

    @Override
    public PriceResponse calculatePrice(PriceRequest price) {
        log.info("Starting calculation of the price");
        return null;
    }
}
