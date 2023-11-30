package com.inditex.pricer.service.impl;

import com.inditex.pricer.common.error.handling.exceptions.PriceNotFound;
import com.inditex.pricer.dto.PriceRequest;
import com.inditex.pricer.dto.PriceResponse;
import com.inditex.pricer.entity.Price;
import com.inditex.pricer.repository.PriceRepository;
import com.inditex.pricer.service.PriceCalculator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class PriceCalculatorService implements PriceCalculator {

    private final PriceRepository priceRepository;

    @Override
    public PriceResponse calculatePrice(PriceRequest price) {

        //Looking up prices from DB
        List<Price> prices = priceRepository
                .findPriceByBrandIdProductIdAndDateTimeApplied(
                        price.getProductId(),
                        price.getBrandId(),
                        price.getDate());

        if (CollectionUtils.isNotEmpty(prices)){
            log.info("There is(are) {} price(s) that it could be applied", prices.size());

            return determinePrice(prices);

        }else{
            log.error("Add corresponding price to the PRICES table");
            throw new PriceNotFound("Price wasn't found for the given criteria");
        }
    }

    private PriceResponse determinePrice(List<Price> prices){

        if (CollectionUtils.isEmpty(prices))
            throw new IllegalArgumentException("List of prices cant be empty or null to determine the price");

        Price priceApplied;
        if (prices.size() == 1) {
            //There is only one price available
            priceApplied = prices.get(0);
        }else{
            //There is more than one price available
            Optional<Price> priceOpt = prices.stream()
                    .max(Comparator.comparingInt(Price::getPriority));

            if(priceOpt.isPresent())
                priceApplied = priceOpt.get();
            else
                throw new PriceNotFound("Price wasn't found For the given criteria");
        }

        return PriceResponse.builder()
                .brandId(priceApplied.getPriceId().getBrand().getId())
                .productId(priceApplied.getPriceId().getProductId())
                .priceList(priceApplied.getPriceId().getPriceList())
                .finalPrice(priceApplied.getPrice())
                .startDateTime(priceApplied.getStartDate())
                .endDateTime(priceApplied.getEndDate())
                .build();
    }
}
