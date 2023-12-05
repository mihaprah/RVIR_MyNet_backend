package com.project.mynet.dao;

import com.project.mynet.models.Commodity;
import org.springframework.data.repository.CrudRepository;

public interface CommodityRepository extends CrudRepository<Commodity, String> {
}
