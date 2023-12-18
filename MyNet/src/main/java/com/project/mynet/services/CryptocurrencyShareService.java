package com.project.mynet.services;


import com.project.mynet.dao.ClientRepository;
import com.project.mynet.dao.CryptocurrencyShareRepository;
import com.project.mynet.exceptions.NotFoundCustomException;
import com.project.mynet.models.Client;
import com.project.mynet.models.CryptocurrencyShare;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;

@Service
@AllArgsConstructor
public class CryptocurrencyShareService {

    private CryptocurrencyShareRepository cryptocurrencyShareDao;
    private ClientRepository clientDao;

    public CryptocurrencyShare getByID(Long id){
        return cryptocurrencyShareDao.findById(id).orElseThrow(() -> new NotFoundCustomException("CryptocurrencyShare with this id does not exist.", 404));
    }

    public Collection<CryptocurrencyShare> getAllForOneClient(Client client){
        clientDao.findById(client.getId()).orElseThrow(() -> new NotFoundCustomException("Client does not exist.", 400));
        return cryptocurrencyShareDao.findCryptocurrencySharesByClient_Id(client.getId());
    }

    public CryptocurrencyShare addNew(CryptocurrencyShare cryptocurrencyShare){
        Collection<CryptocurrencyShare> existingShares = getAllForOneClient(cryptocurrencyShare.getClient());
        for (CryptocurrencyShare share : existingShares){
            if (Objects.equals(share.getCryptocurrency().getCode(), cryptocurrencyShare.getCryptocurrency().getCode())){
                throw new NotFoundCustomException("CryptocurrencyShare for this client already exist. Update the amount.", 400);
            }
        }
        return cryptocurrencyShareDao.save(cryptocurrencyShare);
    }

    public double updateCryptoAmount(Long id, double newCryptoAmount){
        CryptocurrencyShare cryptocurrencyShare = cryptocurrencyShareDao.findById(id).orElseThrow(() -> new NotFoundCustomException("CryptocurrencyShare with this id does not exist.", 404));
        cryptocurrencyShare.setAmount(newCryptoAmount);
        cryptocurrencyShareDao.save(cryptocurrencyShare);
        return cryptocurrencyShare.getAmount();
    }


}
