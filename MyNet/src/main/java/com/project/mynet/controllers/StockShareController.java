package com.project.mynet.controllers;

import com.project.mynet.models.Client;
import com.project.mynet.models.StockShare;
import com.project.mynet.models.UpdateAmountRequest;
import com.project.mynet.services.StockShareService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/stockshare")
public class StockShareController {

    private StockShareService stockShareService;

    @GetMapping("/{id}")
    public ResponseEntity<StockShare> getByID(@PathVariable("id") Long id){
        StockShare stockShare = stockShareService.getByID(id);
        return ResponseEntity.ok(stockShare);
    }

    @GetMapping
    public ResponseEntity<Collection<StockShare>> getAllForOneClient(@RequestBody Client client) {
        Collection<StockShare> allShares = stockShareService.getAllForOneClient(client);
        return ResponseEntity.ok(allShares);
    }


    @PostMapping("/add")
    public ResponseEntity<StockShare> createCommodityShare(@RequestBody StockShare stockShare){
        StockShare newStockShare = stockShareService.addNew(stockShare);
        return ResponseEntity.ok(newStockShare);

    }

    @PutMapping("/updateAmount/{id}")
    public ResponseEntity<Double> updateStockShare(@PathVariable("id") Long id, @RequestBody UpdateAmountRequest request){
        double newStockShareAmount = stockShareService.updateStockAmount(id, request.getAmount());
        return ResponseEntity.ok(newStockShareAmount);
    }

}
