package com.project.mynet.dao;

import com.project.mynet.models.StockShare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface StockShareRepository extends JpaRepository<StockShare, Long> {

    @Query("select s from StockShare s where s.client.id = ?1")
    Collection<StockShare> findStockSharesByClient_Id(Long id);
}
