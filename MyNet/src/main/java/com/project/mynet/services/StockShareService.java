package com.project.mynet.services;


import com.project.mynet.dao.ClientRepository;
import com.project.mynet.dao.StockShareRepository;
import com.project.mynet.exceptions.NotFoundCustomException;
import com.project.mynet.models.Client;
import com.project.mynet.models.StockShare;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
public class StockShareService {

    private StockShareRepository stockShareDao;
    private ClientRepository clientDao;

    public StockShare getByID(Long id){
        return stockShareDao.findById(id).orElseThrow(() -> new NotFoundCustomException("StockShare with this id does not exist.", 404));
    }

    public Collection<StockShare> getAllForOneClient(Client client){
        clientDao.findById(client.getId()).orElseThrow(() -> new NotFoundCustomException("Client does not exist.", 404));
        return stockShareDao.findStockSharesByClient_Id(client.getId());
    }

    public StockShare addNew(StockShare stockShare){
        return stockShareDao.save(stockShare);
    }

    public double updateStockAmount(Long id, double newStockAmount){
        StockShare stockShare = stockShareDao.findById(id).orElseThrow(() -> new NotFoundCustomException("StockShare with this id does not exist.", 404));
        stockShare.setAmount(newStockAmount);
        stockShareDao.save(stockShare);
        return stockShare.getAmount();
    }

}
