package com.inditex.pricer.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class PriceResponse {

    private Integer brandId;
    private Integer productId;
    private Integer priceList;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private BigDecimal finalPrice;

}
