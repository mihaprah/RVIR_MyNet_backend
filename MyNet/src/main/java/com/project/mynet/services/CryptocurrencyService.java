package com.project.mynet.services;

import com.project.mynet.dao.CryptocurrencyRepository;
import com.project.mynet.exceptions.NotFoundCustomException;
import com.project.mynet.models.Cryptocurrency;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;

@Service
@AllArgsConstructor
public class CryptocurrencyService {

    private CryptocurrencyRepository cryptocurrencyDao;

    public Cryptocurrency getOne(Long id) {
        return cryptocurrencyDao.findById(id).orElseThrow(() -> new NotFoundCustomException("Cryptocurrency with this id does not exist.", 404));
    }

    public Collection<Cryptocurrency> getAll() {
        return cryptocurrencyDao.findAll();
    }

    public Cryptocurrency getByCode(String code) {
        if (code.isEmpty()){
            throw new NotFoundCustomException("Code must be provided.", 400);
        }
        Cryptocurrency cryptocurrency = cryptocurrencyDao.findByCode(code);
        if (cryptocurrency == null){
            throw new NotFoundCustomException("Cryptocurrency with this code does not exist.", 400);
        }
        return cryptocurrency;
    }

    public Cryptocurrency addNew(Cryptocurrency cryptocurrency) {
        String newCode = cryptocurrency.getCode();
        Collection<Cryptocurrency> existingCryptocurrencies = cryptocurrencyDao.findAll();
        for (Cryptocurrency cry : existingCryptocurrencies) {
            if (Objects.equals(cry.getCode(), newCode)) {
                throw new NotFoundCustomException("Cryptocurrency with this code already exists.", 400);
            }
        }
        return cryptocurrencyDao.save(cryptocurrency);
    }

    public Cryptocurrency update(Cryptocurrency cryptocurrency) {
        Cryptocurrency oldCryptocurrency = cryptocurrencyDao.findById(cryptocurrency.getId()).orElseThrow(() -> new NotFoundCustomException("Cryptocurrency with this id does not exist.", 404));

        oldCryptocurrency.setName(cryptocurrency.getName());
        oldCryptocurrency.setCode(cryptocurrency.getCode());

        return cryptocurrencyDao.save(oldCryptocurrency);
    }

    public String delete(Long id) {
        cryptocurrencyDao.findById(id).orElseThrow(() -> new NotFoundCustomException("Cryptocurrency with this id does not exist.", 404));
        cryptocurrencyDao.deleteById(id);
        return "Deleted successfully";
    }
}
