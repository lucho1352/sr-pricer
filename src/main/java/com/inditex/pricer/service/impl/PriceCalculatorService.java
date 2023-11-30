package com.inditex.pricer.service.impl;

import com.inditex.pricer.dto.PriceRequest;
import com.inditex.pricer.dto.PriceResponse;
import com.inditex.pricer.entity.Price;
import com.inditex.pricer.repository.PriceRepository;
import com.inditex.pricer.service.PriceCalculator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class PriceCalculatorService implements PriceCalculator {

    private final PriceRepository priceRepository;

    @Override
    public PriceResponse calculatePrice(PriceRequest price) {
        log.info("Starting calculation of the price");

        Optional<List<Price>> pricesOpt = priceRepository.findByPriceIdProductId(price.getProductId());

        if (pricesOpt.isPresent()){
            log.info("there are prices");
        }else{
            throw new IllegalArgumentException("Id not found in the request");
        }

        return null;
    }
}
