package com.project.mynet.services;


import com.project.mynet.dao.CommodityShareRepository;
import com.project.mynet.exceptions.NotFoundCustomException;
import com.project.mynet.models.CommodityShare;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
public class CommodityShareService {

    private CommodityShareRepository commodityShareDao;

    public CommodityShare getByID(Long id){
        return commodityShareDao.findById(id).orElseThrow(() -> new NotFoundCustomException("CommodityShare with this id does not exist.", 404));
    }

    public Collection<CommodityShare> getAll(){
        return commodityShareDao.findAll();
    }

    public CommodityShare addNew(CommodityShare commodityShare){
        return commodityShareDao.save(commodityShare);
    }

    public int updateCommodityAmount(Long id, int newCommodityAmount){
        CommodityShare commodityShare = commodityShareDao.findById(id).orElseThrow(() -> new NotFoundCustomException("CommodityShare with this id does not exist.", 404));
        commodityShare.setAmount(newCommodityAmount);
        commodityShareDao.save(commodityShare);
        return commodityShare.getAmount();
    }


}
