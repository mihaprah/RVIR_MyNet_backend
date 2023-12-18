package com.project.mynet.controllers;

import com.project.mynet.models.CryptocurrencyShare;
import com.project.mynet.models.StockShare;
import com.project.mynet.services.CryptocurrencyShareService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/cryptocurrencyshare")
public class CryptocurrencyShareController {
    private CryptocurrencyShareService cryptocurrencyShareService;

    @GetMapping("/{id}")
    public ResponseEntity<CryptocurrencyShare> getByID(@PathVariable("id") Long id){
        CryptocurrencyShare cryptocurrencyShare = cryptocurrencyShareService.getByID(id);
        return ResponseEntity.ok(cryptocurrencyShare);
    }

    @GetMapping
    public Collection<CryptocurrencyShare> getAll() {
        return cryptocurrencyShareService.getAll();
    }


    @PostMapping("/add")
    public ResponseEntity<CryptocurrencyShare> createCryptocurrencyShare(@RequestBody CryptocurrencyShare cryptocurrencyShare){
        CryptocurrencyShare newCryptocurrencyShare = cryptocurrencyShareService.addNew(cryptocurrencyShare);
        return ResponseEntity.ok(newCryptocurrencyShare);

    }
    @PutMapping("/updateAmount/{id}")
    public ResponseEntity<Integer> updateNewCryptocurrencyShare(@PathVariable("id") Long id, @RequestBody int amount){
        int newCryptocurrencyShareAmount = cryptocurrencyShareService.updateCryptoAmount(id, amount);
        return ResponseEntity.ok(newCryptocurrencyShareAmount);
    }


}
