package com.inditex.pricer.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Embeddable
@NoArgsConstructor
public class PriceId implements Serializable {

    @ManyToOne
    @JoinColumn(name = "BRAND_ID", referencedColumnName = "ID")
    private Brand brand;

    @Column(name = "PRODUCT_ID")
    private Integer productId;

    @Column(name = "PRICE_LIST")
    private Integer priceList;

    //TODO: Here we also should have country, since the price
    //      will be different depending on the country

}
