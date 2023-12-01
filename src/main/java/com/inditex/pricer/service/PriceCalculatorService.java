package com.inditex.pricer.service;

import com.inditex.pricer.common.calculation.service.PricingStrategy;
import com.inditex.pricer.common.error.handling.exceptions.PriceNotFoundException;
import com.inditex.pricer.common.error.handling.exceptions.StrategyNotImplementedException;
import com.inditex.pricer.dto.PriceRequest;
import com.inditex.pricer.dto.PriceResponse;
import com.inditex.pricer.entity.Price;
import com.inditex.pricer.repository.PriceRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class PriceCalculatorService implements PriceCalculator {

    private final PriceRepository priceRepository;
    private final PricingStrategy pricingZaraStrategy;

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
            return applyStrategy(price.getBrandId(), prices);

        }else{
            throw new PriceNotFoundException("Price wasn't found for the given criteria");
        }
    }

    /**
     * This method will route the list of prices to the given strategy base on brand Id
     * @param brandId Id of the brand
     * @param prices List of prices
     * @return Price selected base on a brand's logic calculation
     */
    private PriceResponse applyStrategy(Integer brandId, List<Price> prices){

        Price priceApplied;
        switch(brandId){
            case 1:
                priceApplied = pricingZaraStrategy.calculatePrice(prices);
                break;
            default:
                throw new StrategyNotImplementedException("Strategy for brandId=(" +brandId+ ") hasn't been implemented yet");
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
