package com.project.mynet.controllers;

import com.project.mynet.models.CryptocurrencyShare;
import com.project.mynet.models.UpdateAmountRequest;
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

    @GetMapping("/all/{client_id}")
    public ResponseEntity<Collection<CryptocurrencyShare>> getAllForOneClient(@PathVariable Long client_id) {
        Collection<CryptocurrencyShare> allShares = cryptocurrencyShareService.getAllForOneClient(client_id);
        return ResponseEntity.ok(allShares);
    }


    @PostMapping("/add")
    public ResponseEntity<CryptocurrencyShare> createCryptocurrencyShare(@RequestBody CryptocurrencyShare cryptocurrencyShare){
        CryptocurrencyShare newCryptocurrencyShare = cryptocurrencyShareService.addNew(cryptocurrencyShare);
        return ResponseEntity.ok(newCryptocurrencyShare);

    }
    @PutMapping("/updateAmount/{id}")
    public ResponseEntity<Double> updateNewCryptocurrencyShare(@PathVariable("id") Long id, @RequestBody UpdateAmountRequest request){
        double newCryptocurrencyShareAmount = cryptocurrencyShareService.updateCryptoAmount(id, request.getAmount());
        return ResponseEntity.ok(newCryptocurrencyShareAmount);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteShare(@PathVariable("id")Long id) {
        String message = cryptocurrencyShareService.deleteShare(id);
        return ResponseEntity.ok(message);
    }


}
