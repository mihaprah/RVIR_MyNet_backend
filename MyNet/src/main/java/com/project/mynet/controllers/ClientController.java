package com.project.mynet.controllers;


import com.project.mynet.models.Client;
import com.project.mynet.services.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/client")
public class ClientController {
    private ClientService clientService;

    @GetMapping
    public Iterable<Client> getAll(){
        return clientService.getAll();
    }
    @GetMapping("/{client_id}")
    public Client getById(@PathVariable("client_id") String id){
        return clientService.getById(id);
    }


}
