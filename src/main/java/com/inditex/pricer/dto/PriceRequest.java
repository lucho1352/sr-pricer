package com.inditex.pricer.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@Builder
@ToString
public class PriceRequest {

    private Integer productId;
    private Integer brandId;
    private LocalDateTime date;

}
