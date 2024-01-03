package com.project.mynet.dao;

import com.project.mynet.models.Cryptocurrency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CryptocurrencyRepository extends JpaRepository<Cryptocurrency, Long> {
    @Query("select c from Cryptocurrency c where c.code = ?1")
    Cryptocurrency findByCode(String code);
}
