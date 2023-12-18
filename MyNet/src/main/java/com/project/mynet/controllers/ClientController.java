package com.project.mynet.controllers;


import com.project.mynet.models.Client;
import com.project.mynet.models.LoginRequest;
import com.project.mynet.services.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/client")
public class ClientController {
    private ClientService clientService;

    @GetMapping
    public Collection<Client> getAll() {
        return clientService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getById(@PathVariable("id") Long id) {
        Client client = clientService.getById(id);
        return ResponseEntity.ok(client);
    }

    @PutMapping("/update")
    public ResponseEntity<Client> updateClient(@RequestBody Client client) {
        Client updatedClient = clientService.update(client);
        return ResponseEntity.ok(updatedClient);
    }

    @PutMapping("/updateCash/{id}")
    public ResponseEntity<Double> updateClient(@PathVariable("id") Long id, @RequestBody double amount) {
        double newCashBalance = clientService.updateCashBalance(amount, id);
        return ResponseEntity.ok(newCashBalance);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable("id") Long id) {
        String message = clientService.delete(id);
        return ResponseEntity.ok(message);
    }


}
