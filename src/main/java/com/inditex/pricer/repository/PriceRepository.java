package com.inditex.pricer.repository;

import com.inditex.pricer.entity.Price;
import com.inditex.pricer.entity.PriceId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceRepository extends CrudRepository<Price, PriceId> {

    @Query("SELECT p FROM Price p " +
            "WHERE p.priceId.productId = :productId " +
            "AND p.priceId.brand.id = :brandId " +
            "AND :dateTimeApplied BETWEEN p.startDate AND p.endDate")
    List<Price> findPriceByBrandIdProductIdAndDateTimeApplied(
            @Param("productId") Integer productId,
            @Param("brandId") Integer brandId,
            @Param("dateTimeApplied") LocalDateTime dateTimeApplied);

}
