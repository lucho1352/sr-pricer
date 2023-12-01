package com.inditex.pricer.service;

import com.inditex.pricer.common.calculation.service.PricingStrategy;
import com.inditex.pricer.common.error.handling.exceptions.PriceNotFoundException;
import com.inditex.pricer.common.error.handling.exceptions.StrategyNotImplementedException;
import com.inditex.pricer.dto.PriceRequest;
import com.inditex.pricer.dto.PriceResponse;
import com.inditex.pricer.entity.Brand;
import com.inditex.pricer.entity.Currency;
import com.inditex.pricer.entity.Price;
import com.inditex.pricer.entity.PriceId;
import com.inditex.pricer.repository.PriceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PriceCalculatorServiceTest {

    @InjectMocks
    private PriceCalculatorService priceCalculatorService;
    @Mock
    private PriceRepository priceRepository;
    @Mock
    private PricingStrategy pricingZaraStrategy;

    @Test
    void calculatePriceTest_priceNotFound(){
        //given
        PriceRequest priceRequest = getPriceRequest(1);
        when(priceRepository.findPriceByBrandIdProductIdAndDateTimeApplied(any(),any(),any()))
                .thenReturn(Collections.EMPTY_LIST);

        //then
        assertThrows(PriceNotFoundException.class, () ->
                //when
                priceCalculatorService.calculatePrice(priceRequest));

        //then
        verify(priceRepository, times(1))
                .findPriceByBrandIdProductIdAndDateTimeApplied(any(),any(),any());
        verify(pricingZaraStrategy, times(0))
                .calculatePrice(anyList());

    }

    @Test
    void calculatePriceTest_strategyNotImplemeted(){
        //given
        PriceRequest priceRequest = getPriceRequest(2);
        when(priceRepository.findPriceByBrandIdProductIdAndDateTimeApplied(any(),any(),any()))
                .thenReturn(getPrices(2));

        //then
        assertThrows(StrategyNotImplementedException.class, () ->
                //when
                priceCalculatorService.calculatePrice(priceRequest));

        //then
        verify(priceRepository, times(1))
                .findPriceByBrandIdProductIdAndDateTimeApplied(any(),any(),any());
        verify(pricingZaraStrategy, times(0))
                .calculatePrice(anyList());

    }

    @Test
    void calculatePriceTest_ok(){
        //given
        PriceRequest priceRequest = getPriceRequest(1);
        when(priceRepository.findPriceByBrandIdProductIdAndDateTimeApplied(any(),any(),any()))
                .thenReturn(getPrices(1));
        when(pricingZaraStrategy.calculatePrice(anyList())).thenReturn(getPrice(2));

        //when
        PriceResponse priceCalculated = priceCalculatorService.calculatePrice(priceRequest);

        //then
        assertNotNull(priceCalculated);
        assertNotNull(priceCalculated.getFinalPrice());
        verify(priceRepository, times(1))
                .findPriceByBrandIdProductIdAndDateTimeApplied(any(),any(),any());
        verify(pricingZaraStrategy, times(1))
                .calculatePrice(anyList());

    }

    private PriceRequest getPriceRequest(Integer brandId){

        return PriceRequest.builder()
                .brandId(brandId)
                .productId(35455)
                .date(LocalDateTime.of(2020,6,16,21,0,0))
                .build();
    }

    private List<Price> getPrices(Integer brandId){

        return List.of(getPrice(brandId));
    }

    private Price getPrice(Integer brandId){

        return Price.builder()
                .priceId(PriceId.builder()
                        .brand(Brand.builder()
                                .id(brandId)
                                .name("Zara")
                                .build())
                        .priceList(1)
                        .productId(35455)
                        .build())
                .price(new BigDecimal("35.50"))
                .currency(Currency.builder()
                        .id(1)
                        .shortDescription("EUR")
                        .build())
                .priority(0)
                .startDate(LocalDateTime.of(2020,6,15,16,0,0))
                .endDate(LocalDateTime.of(2020,12,31,23,59,59))
                .build();
    }

}
