package com.project.mynet.dao;

import com.project.mynet.models.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StockRepository extends JpaRepository<Stock, Long> {

    @Query("select s from Stock s where s.code = ?1")
    Stock findByCode(String code);
}
