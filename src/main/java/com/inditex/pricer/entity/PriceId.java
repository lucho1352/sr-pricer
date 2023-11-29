package com.inditex.pricer.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Embeddable
@NoArgsConstructor
public class PriceId implements Serializable {

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "BRAND_ID", referencedColumnName = "ID")
    private Brand brand;

    @Column(name = "PRODUCT_ID")
    private Long productId;

    @Column(name = "PRICE_LIST")
    private Long priceList;

    //TODO: Here we also should have country, since the price
    //      will be different depending on the country

}
