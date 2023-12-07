package com.project.mynet.services;


import com.project.mynet.dao.CryptocurrencyShareRepository;
import com.project.mynet.exceptions.NotFoundCustomException;
import com.project.mynet.models.CryptocurrencyShare;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
public class CryptocurrencyShareService {

    private CryptocurrencyShareRepository cryptocurrencyShareDao;

    public CryptocurrencyShare getByID(Long id){
        return cryptocurrencyShareDao.findById(id).orElseThrow(() -> new NotFoundCustomException("CryptocurrencyShare with this id does not exist.", 404));
    }

    public Collection<CryptocurrencyShare> getAll(){
        return cryptocurrencyShareDao.findAll();
    }

    public CryptocurrencyShare addNew(CryptocurrencyShare cryptocurrencyShare){
        return cryptocurrencyShareDao.save(cryptocurrencyShare);
    }

    public int updateCryptoAmount(Long id, int newCryptoAmount){
        CryptocurrencyShare cryptocurrencyShare = cryptocurrencyShareDao.findById(id).orElseThrow(() -> new NotFoundCustomException("CryptocurrencyShare with this id does not exist.", 404));
        cryptocurrencyShare.setAmount(newCryptoAmount);
        cryptocurrencyShareDao.save(cryptocurrencyShare);
        return cryptocurrencyShare.getAmount();
    }


}
