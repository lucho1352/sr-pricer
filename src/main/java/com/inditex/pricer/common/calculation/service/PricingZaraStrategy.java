package com.inditex.pricer.common.calculation.service;

import com.inditex.pricer.common.error.handling.exceptions.PriceNotFoundException;
import com.inditex.pricer.entity.Price;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PricingZaraStrategy implements PricingStrategy{

    @Override
    public Price calculatePrice(List<Price> prices) {

        if (CollectionUtils.isEmpty(prices))
            throw new IllegalArgumentException("List of prices cant be empty or null to determine the price");

        Price priceApplied;
        if (prices.size() == 1) {
            //There is only one price available
            return prices.get(0);
        }else{
            //There is more than one price available
            Optional<Price> priceOpt = prices.stream()
                    .max(Comparator.comparingInt(Price::getPriority));

            if(priceOpt.isPresent())
                return priceOpt.get();
            else
                throw new PriceNotFoundException("Price wasn't found For the given criteria");
        }
    }

}
