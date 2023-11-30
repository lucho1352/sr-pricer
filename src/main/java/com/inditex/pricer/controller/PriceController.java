package com.inditex.pricer.controller;


import com.inditex.pricer.dto.PriceRequest;
import com.inditex.pricer.dto.PriceResponse;
import com.inditex.pricer.service.PriceCalculator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Validated
@RestController
@Tag(name = "Prices")
@RequestMapping("/v1/price")
@AllArgsConstructor
public class PriceController {

    private final PriceCalculator priceCalculator;

    @GetMapping
    @Operation(summary = "Gets price by brand id, product id and a date time")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Price found",
                    content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = PriceResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Price not found", content = @Content) })
    public PriceResponse determinePrice(@RequestBody @Valid PriceRequest priceRequest){

        return priceCalculator.calculatePrice(priceRequest);
    }
}
