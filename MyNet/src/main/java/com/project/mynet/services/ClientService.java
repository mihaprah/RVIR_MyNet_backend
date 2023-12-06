package com.project.mynet.services;


import com.project.mynet.dao.ClientRepository;
import com.project.mynet.models.Client;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClientService {
    private ClientRepository clientDao;

    public Iterable<Client> getAll(){
        return clientDao.findAll();
    }
    public Client getById(Long id){
        return clientDao.findById(id).orElseThrow(() -> new IllegalCallerException("Client does not exist"));
    }

}
