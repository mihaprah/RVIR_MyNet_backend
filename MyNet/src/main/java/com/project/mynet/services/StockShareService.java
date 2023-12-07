package com.project.mynet.services;


import com.project.mynet.dao.StockShareRepository;
import com.project.mynet.exceptions.NotFoundCustomException;
import com.project.mynet.models.StockShare;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
public class StockShareService {

    private StockShareRepository stockShareDao;

    public StockShare getByID(Long id){
        return stockShareDao.findById(id).orElseThrow(() -> new NotFoundCustomException("StockShare with this id does not exist.", 404));
    }

    public Collection<StockShare> getAll(){
        return stockShareDao.findAll();
    }

    public StockShare addNew(StockShare stockShare){
        return stockShareDao.save(stockShare);
    }

    public int updateStockAmount(Long id,int newStockAmount){
        StockShare stockShare = stockShareDao.findById(id).orElseThrow(() -> new NotFoundCustomException("StockShare with this id does not exist.", 404));
        stockShare.setAmount(newStockAmount);
        stockShareDao.save(stockShare);
        return stockShare.getAmount();
    }

}
