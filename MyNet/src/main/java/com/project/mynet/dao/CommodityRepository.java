package com.project.mynet.dao;

import com.project.mynet.models.Commodity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CommodityRepository extends JpaRepository<Commodity, Long> {

    @Query("select c from Commodity c where c.code = ?1")
    Commodity findByCode(String code);
}
