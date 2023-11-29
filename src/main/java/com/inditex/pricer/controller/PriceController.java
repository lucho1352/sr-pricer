package com.inditex.pricer.controller;


import com.inditex.pricer.dto.PriceRequest;
import com.inditex.pricer.dto.PriceResponse;
import com.inditex.pricer.service.PriceCalculator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1/price")
@AllArgsConstructor
public class PriceController {

    private final PriceCalculator priceCalculator;

    @GetMapping
    public PriceResponse determinePrice(@RequestBody PriceRequest priceRequest){
        return priceCalculator.calculatePrice(priceRequest);
    }
}
