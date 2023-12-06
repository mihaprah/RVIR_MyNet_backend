package com.project.mynet.controllers;

import com.project.mynet.models.Stock;
import com.project.mynet.services.StockService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/stock")
public class StockController {

    private StockService stockService;

    @GetMapping
    public Collection<Stock> getAll(){
        return stockService.getAll();
    }

    @GetMapping("/{id}")
    public Stock getById(@PathVariable("id") Long id) {
        return stockService.getOne(id);
    }

    @PostMapping("/add")
    public ResponseEntity<Stock> addStock(@RequestBody Stock stock){
        Stock newStock = stockService.addNew(stock);
        return ResponseEntity.ok(newStock);
    }

    @PutMapping("/update")
    public ResponseEntity<Stock> updateStock(@RequestBody Stock stock){
        Stock updatedStock = stockService.update(stock);
        return ResponseEntity.ok(updatedStock);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStock(@PathVariable("id") Long id) {
        String message = stockService.delete(id);
        return ResponseEntity.ok(message);
    }
}
