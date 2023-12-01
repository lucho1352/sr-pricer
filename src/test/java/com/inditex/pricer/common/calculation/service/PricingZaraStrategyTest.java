package com.inditex.pricer.common.calculation.service;


import com.inditex.pricer.entity.Brand;
import com.inditex.pricer.entity.Currency;
import com.inditex.pricer.entity.Price;
import com.inditex.pricer.entity.PriceId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class PricingZaraStrategyTest {

    @InjectMocks
    private PricingZaraStrategy pricingZaraStrategy;

    @Test
    void calculatePrice_withEmptyList(){
        //given
        List<Price> prices = Collections.EMPTY_LIST;

        //then
        assertThrows(IllegalArgumentException.class, () ->
                //when
                pricingZaraStrategy.calculatePrice(prices));

    }

    @Test
    void calculatePrice_with1Price(){
        //given
        List<Price> prices = getPrices(1,1);

        //when
        Price finalPrice = pricingZaraStrategy.calculatePrice(prices);

        //then
        assertNotNull(finalPrice);
        assertEquals(1, finalPrice.getPriceId().getBrand().getId());
        assertEquals(35455, finalPrice.getPriceId().getProductId());
        assertEquals(new BigDecimal(1), finalPrice.getPrice());

    }

    @Test
    void calculatePrice_with2Prices(){
        //given
        Integer items = 2;
        Integer brandId = 1;
        List<Price> prices = getPrices(brandId,items);

        //when
        Price finalPrice = pricingZaraStrategy.calculatePrice(prices);

        //then
        assertNotNull(finalPrice);
        assertEquals(brandId, finalPrice.getPriceId().getBrand().getId());
        assertEquals(35455, finalPrice.getPriceId().getProductId());
        assertEquals(new BigDecimal(items), finalPrice.getPrice());

    }

    private List<Price> getPrices(Integer brandId, Integer items){

        List<Price> prices = new ArrayList<>();

        for (int i = 0 ; i < items; i++){
            BigDecimal finalPrice = new BigDecimal(i+1);
            Price price = getPrice(brandId, i, finalPrice);
            prices.add(price);
        }

        return prices;
    }

    private Price getPrice(Integer brandId, Integer priority, BigDecimal price){

        return Price.builder()
                .priceId(PriceId.builder()
                        .brand(Brand.builder()
                                .id(brandId)
                                .name("Zara")
                                .build())
                        .priceList(priority)
                        .productId(35455)
                        .build())
                .price(price)
                .currency(Currency.builder()
                        .id(1)
                        .shortDescription("EUR")
                        .build())
                .priority(priority)
                .startDate(LocalDateTime.of(2020,6,15,16,0,0))
                .endDate(LocalDateTime.of(2020,12,31,23,59,59))
                .build();
    }

}
