package com.project.mynet.services;

import com.project.mynet.dao.StockRepository;
import com.project.mynet.exceptions.NotFoundCustomException;
import com.project.mynet.models.Stock;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;

@Service
@AllArgsConstructor
public class StockService {

    private StockRepository stockDao;

    public Stock getOne(Long id) {
        return stockDao.findById(id).orElseThrow(() -> new NotFoundCustomException("Stock with this id does not exist.", 404));
    }

    public Collection<Stock> getAll() {
        return stockDao.findAll();
    }

    public Stock getByCode(String code) {
        if (code.isEmpty()){
            throw new NotFoundCustomException("Code must be provided.", 400);
        }
        Stock stock = stockDao.findByCode(code);
        if (stock == null){
            throw new NotFoundCustomException("Cryptocurrency with this code does not exist.", 400);
        }
        return stock;
    }

    public Stock addNew(Stock stock) {
        String newCode = stock.getCode();
        Collection<Stock> existingStocks = stockDao.findAll();
        for (Stock st : existingStocks) {
            if (Objects.equals(st.getCode(), newCode)) {
                throw new NotFoundCustomException("Stock with this code already exists.", 400);
            }
        }
        return stockDao.save(stock);
    }

    public Stock update(Stock stock) {
        Stock oldStock = stockDao.findById(stock.getId()).orElseThrow(() -> new NotFoundCustomException("Stock with this id does not exist.", 404));

        oldStock.setName(stock.getName());
        oldStock.setCode(stock.getCode());

        return stockDao.save(oldStock);
    }

    public String delete(Long id) {
        stockDao.findById(id).orElseThrow(() -> new NotFoundCustomException("Stock with this id does not exist.", 404));
        stockDao.deleteById(id);
        return "Deleted successfully";
    }
}
