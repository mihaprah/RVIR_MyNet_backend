package com.project.mynet.controllers;

import com.project.mynet.models.Commodity;
import com.project.mynet.models.Cryptocurrency;
import com.project.mynet.services.CryptocurrencyService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/cryptocurrency")
public class CryptocurrencyController {

    private CryptocurrencyService cryptocurrencyService;

    @GetMapping
    public Collection<Cryptocurrency> getAll(){
        return cryptocurrencyService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cryptocurrency> getById(@PathVariable("id") Long id) {
        Cryptocurrency cryptocurrency = cryptocurrencyService.getOne(id);
        return ResponseEntity.ok(cryptocurrency);
    }

    @PostMapping("/add")
    public ResponseEntity<Cryptocurrency> addCryptocurrency(@RequestBody Cryptocurrency cryptocurrency){
        Cryptocurrency newCryptocurrency = cryptocurrencyService.addNew(cryptocurrency);
        return ResponseEntity.ok(newCryptocurrency);
    }

    @PutMapping("/update")
    public ResponseEntity<Cryptocurrency> updateCryptocurrency(@RequestBody Cryptocurrency cryptocurrency){
        Cryptocurrency updatedCryptocurrency = cryptocurrencyService.update(cryptocurrency);
        return ResponseEntity.ok(updatedCryptocurrency);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCryptocurrency(@PathVariable("id") Long id) {
        String message = cryptocurrencyService.delete(id);
        return ResponseEntity.ok(message);
    }




}
