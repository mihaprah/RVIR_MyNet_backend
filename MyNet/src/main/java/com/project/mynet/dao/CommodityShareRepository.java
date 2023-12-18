package com.project.mynet.dao;

import com.project.mynet.models.CommodityShare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface CommodityShareRepository extends JpaRepository<CommodityShare, Long> {

    @Query("select c from CommodityShare c where c.client.id = ?1")
    Collection<CommodityShare> findCommoditySharesByClient_Id(Long id);
}
