package com.project.mynet.services;


import com.project.mynet.dao.ClientRepository;
import com.project.mynet.dao.CommodityShareRepository;
import com.project.mynet.exceptions.NotFoundCustomException;
import com.project.mynet.models.Client;
import com.project.mynet.models.CommodityShare;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
public class CommodityShareService {

    private CommodityShareRepository commodityShareDao;
    private ClientRepository clientDao;

    public CommodityShare getByID(Long id){
        return commodityShareDao.findById(id).orElseThrow(() -> new NotFoundCustomException("CommodityShare with this id does not exist.", 404));
    }

    public Collection<CommodityShare> getAllForOneClient(Client client){
        clientDao.findById(client.getId()).orElseThrow(() -> new NotFoundCustomException("Client does not exist.", 404));
        return commodityShareDao.findCommoditySharesByClient_Id(client.getId());
    }

    public CommodityShare addNew(CommodityShare commodityShare){
        return commodityShareDao.save(commodityShare);
    }

    public double updateCommodityAmount(Long id, double newCommodityAmount){
        CommodityShare commodityShare = commodityShareDao.findById(id).orElseThrow(() -> new NotFoundCustomException("CommodityShare with this id does not exist.", 404));
        commodityShare.setAmount(newCommodityAmount);
        commodityShareDao.save(commodityShare);
        return commodityShare.getAmount();
    }


}
