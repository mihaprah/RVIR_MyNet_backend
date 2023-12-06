package com.project.mynet.services;


import com.project.mynet.dao.ClientRepository;
import com.project.mynet.exceptions.NotFoundCustomException;
import com.project.mynet.models.Client;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;

@Service
@AllArgsConstructor
public class ClientService {
    private ClientRepository clientDao;

    //    Get one Client by ID
    public Client getById(Long id) {
        return clientDao.findById(id).orElseThrow(() -> new NotFoundCustomException("Client with this id does not exist.", 404));
    }

    //    Get all Clients
    public Collection<Client> getAll() {
        return clientDao.findAll();
    }

    //    Update Client
    public Client update(Client client) {
        Client oldClient = clientDao.findById(client.getId()).orElseThrow(() -> new NotFoundCustomException("Client with this id does not exist.", 404));

        oldClient.setName(client.getName());
        oldClient.setLastname(client.getLastname());
        oldClient.setAddress(client.getAddress());
        oldClient.setEmail(client.getEmail());

        return clientDao.save(oldClient);
    }

    //    Update Client Cash balance
    public double updateCashBalance(double newCashBalance, Long id) {
        Client client = clientDao.findById(id).orElseThrow(() -> new NotFoundCustomException("Client with this id does not exist.", 404));
        client.setCashBalance(newCashBalance);
        clientDao.save(client);
        return client.getCashBalance();
    }

    //    Delete Client
    public String delete(Long id) {
        clientDao.findById(id).orElseThrow(() -> new NotFoundCustomException("Client with this id does not exist.", 404));
        clientDao.deleteById(id);
        return "Deleted successfully";
    }
}
