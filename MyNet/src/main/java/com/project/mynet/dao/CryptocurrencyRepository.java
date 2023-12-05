package com.project.mynet.dao;

import com.project.mynet.models.Cryptocurrency;
import org.springframework.data.repository.CrudRepository;

public interface CryptocurrencyRepository extends CrudRepository<Cryptocurrency, String> {
}
