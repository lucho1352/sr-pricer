package com.inditex.pricer.repository;

import com.inditex.pricer.entity.Price;
import com.inditex.pricer.entity.PriceId;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PriceRepository extends CrudRepository<Price, PriceId> {

    Optional<List<Price>> findByPriceIdProductId(Integer productId);

}
