package com.inditex.pricer.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Builder
@ToString
public class PriceRequest {

    @NotNull(message = "productId can't be null")
    @Positive(message = "productId must be greater than 0")
    private Integer productId;

    @NotNull(message = "brandId can't be null")
    @Positive(message = "brandId must be greater than 0")
    private Integer brandId;

    @NotNull(message = "date can't be null")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime date;

}
