package com.project.mynet.dao;

import com.project.mynet.models.Stock;
import org.springframework.data.repository.CrudRepository;

public interface StockRepository extends CrudRepository<Stock, Long> {
}
