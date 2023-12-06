package com.project.mynet.dao;

import com.project.mynet.models.CryptocurrencyShare;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CryptocurrencyShareRepository extends JpaRepository<CryptocurrencyShare, Long> {
}
