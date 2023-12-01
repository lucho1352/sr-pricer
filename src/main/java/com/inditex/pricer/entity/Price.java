package com.inditex.pricer.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "PRICES")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Price implements Serializable {

    @EmbeddedId
    private PriceId priceId;

    @Column(name = "START_DATE")
    private LocalDateTime startDate;

    @Column(name = "END_DATE")
    private LocalDateTime endDate;

    @Column(name = "PRIORITY")
    private Integer priority;

    @Column(name = "PRICE", precision = 8, scale = 2)
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "CURRENCY_ID", referencedColumnName = "ID")
    private Currency currency;

}
