package com.project.mynet.dao;

import com.project.mynet.models.Cryptocurrency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CryptocurrencyRepository extends JpaRepository<Cryptocurrency, Long> {
}
