package com.project.mynet.dao;

import com.project.mynet.models.StockShare;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockShareRepository extends JpaRepository<StockShare, Long> {
}
