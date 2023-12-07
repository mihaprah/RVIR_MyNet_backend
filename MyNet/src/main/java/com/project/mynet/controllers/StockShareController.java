package com.project.mynet.controllers;

import com.project.mynet.models.CommodityShare;
import com.project.mynet.models.StockShare;
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
    public StockShare getByID(@PathVariable("id") Long id){
        return stockShareService.getByID(id);
    }

    @GetMapping
    public Collection<StockShare> getAll() {
        return stockShareService.getAll();
    }


    @PostMapping("/add")
    public ResponseEntity<StockShare> createCommodityShare(@RequestBody StockShare stockShare){
        StockShare newStockShare = stockShareService.addNew(stockShare);
        return ResponseEntity.ok(newStockShare);

    }

    @PutMapping("/updateAmount/{id}")
    public ResponseEntity<Integer> updateStockShare(@PathVariable("id") Long id, @RequestBody int amount){
        int newStockShareAmount = stockShareService.updateStockAmount(id, amount);
        return ResponseEntity.ok(newStockShareAmount);
    }

}
