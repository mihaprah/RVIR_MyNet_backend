package com.project.mynet.controllers;

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

    @GetMapping("/all/{client_id}")
    public ResponseEntity<Collection<StockShare>> getAllForOneClient(@PathVariable Long client_id) {
        Collection<StockShare> allShares = stockShareService.getAllForOneClient(client_id);
        return ResponseEntity.ok(allShares);
    }


    @PostMapping("/add")
    public ResponseEntity<StockShare> createStockShare(@RequestBody StockShare stockShare){
        StockShare newStockShare = stockShareService.addNew(stockShare);
        return ResponseEntity.ok(newStockShare);

    }

    @PutMapping("/updateAmount/{id}")
    public ResponseEntity<Double> updateStockShare(@PathVariable("id") Long id, @RequestBody UpdateAmountRequest request){
        double newStockShareAmount = stockShareService.updateStockAmount(id, request.getAmount());
        return ResponseEntity.ok(newStockShareAmount);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteShare(@PathVariable("id")Long id) {
        String message = stockShareService.deleteShare(id);
        return ResponseEntity.ok(message);
    }

}
