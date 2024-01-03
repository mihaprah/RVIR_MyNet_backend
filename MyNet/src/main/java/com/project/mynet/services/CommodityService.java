package com.project.mynet.services;

import com.project.mynet.dao.CommodityRepository;
import com.project.mynet.exceptions.NotFoundCustomException;
import com.project.mynet.models.Commodity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;

@Service
@AllArgsConstructor
public class CommodityService {

    private CommodityRepository commodityDao;

    public Commodity getOne(Long id) {
        return commodityDao.findById(id).orElseThrow(() -> new NotFoundCustomException("Commodity with this id does not exist.", 404));
    }

    public Collection<Commodity> getAll() {
        return commodityDao.findAll();
    }

    public Commodity getByCode(String code) {
        if (code.isEmpty()){
            throw new NotFoundCustomException("Code must be provided.", 400);
        }
        Commodity commodity = commodityDao.findByCode(code);
        if (commodity == null){
            throw new NotFoundCustomException("Commodity with this code does not exist.", 400);
        }
        return commodity;
    }

    public Commodity addNew(Commodity commodity) {
        String newCode = commodity.getCode();
        Collection<Commodity> existingCommodities = commodityDao.findAll();
        for (Commodity com : existingCommodities) {
            if (Objects.equals(com.getCode(), newCode)) {
                throw new NotFoundCustomException("Commodity with this code already exists.", 400);
            }
        }
        return commodityDao.save(commodity);
    }

    public Commodity update(Commodity commodity) {
        Commodity oldCommodity = commodityDao.findById(commodity.getId()).orElseThrow(() -> new NotFoundCustomException("Commodity with this id does not exist.", 404));

        oldCommodity.setName(commodity.getName());
        oldCommodity.setCode(commodity.getCode());

        return commodityDao.save(oldCommodity);
    }

    public String delete(Long id) {
        commodityDao.findById(id).orElseThrow(() -> new NotFoundCustomException("Commodity with this id does not exist.", 404));
        commodityDao.deleteById(id);
        return "Deleted successfully";
    }
}
