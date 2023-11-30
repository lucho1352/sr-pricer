package com.inditex.pricer.service.impl;

import com.inditex.pricer.dto.PriceRequest;
import com.inditex.pricer.dto.PriceResponse;
import com.inditex.pricer.repository.PriceRepository;
import com.inditex.pricer.service.PriceCalculator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class PriceCalculatorService implements PriceCalculator {

    private final PriceRepository priceRepository;

    @Override
    public PriceResponse calculatePrice(PriceRequest price) {
        log.info("Starting calculation of the price");
        return null;
    }
}
