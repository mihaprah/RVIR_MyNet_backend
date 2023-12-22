package com.project.mynet.dao;

import com.project.mynet.models.CommodityShare;
import com.project.mynet.models.CryptocurrencyShare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface CryptocurrencyShareRepository extends JpaRepository<CryptocurrencyShare, Long> {

    @Query("select c from CryptocurrencyShare c where c.client.id = ?1")
    Collection<CryptocurrencyShare> findCryptocurrencySharesByClient_Id(Long id);
}
