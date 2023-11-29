package com.inditex.pricer.repository;

import com.inditex.pricer.entity.Price;
import com.inditex.pricer.entity.PriceId;
import org.springframework.data.repository.CrudRepository;

public interface PriceRepository extends CrudRepository<Price, PriceId> {
    
}
