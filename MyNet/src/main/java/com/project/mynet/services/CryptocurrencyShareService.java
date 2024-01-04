package com.project.mynet.services;


import com.project.mynet.dao.ClientRepository;
import com.project.mynet.dao.CryptocurrencyShareRepository;
import com.project.mynet.exceptions.NotFoundCustomException;
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

    public Collection<CryptocurrencyShare> getAllForOneClient(Long client_id){
        clientDao.findById(client_id).orElseThrow(() -> new NotFoundCustomException("Client does not exist.", 400));
        return cryptocurrencyShareDao.findCryptocurrencySharesByClient_Id(client_id);
    }

    public CryptocurrencyShare addNew(CryptocurrencyShare cryptocurrencyShare){
        Collection<CryptocurrencyShare> existingShares = getAllForOneClient(cryptocurrencyShare.getClient().getId());
        for (CryptocurrencyShare share : existingShares){
            if (Objects.equals(share.getCryptocurrency().getCode(), cryptocurrencyShare.getCryptocurrency().getCode())){
                double oldAmount = share.getAmount();
                double newAmount = oldAmount + cryptocurrencyShare.getAmount();
                updateCryptoAmount(share.getId(), newAmount);
                return share;
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

    public String deleteShare(Long id) {
        cryptocurrencyShareDao.findById(id).orElseThrow(() -> new NotFoundCustomException("Share with this ID does not exist.", 400));
        cryptocurrencyShareDao.deleteById(id);
        return "Deleted successfully.";
    }
}
