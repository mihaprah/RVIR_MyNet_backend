package com.project.mynet.services;

import com.project.mynet.dao.ClientRepository;
import com.project.mynet.exceptions.NotFoundCustomException;
import com.project.mynet.models.Client;
import com.project.mynet.models.LoginRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginService {

    private final ClientRepository clientDao;
    private final PasswordEncoder passwordEncoder;

    public Client login(LoginRequest loginRequest) {
        Client client = clientDao.findClientByEmail(loginRequest.getEmail());

        if (client != null && passwordEncoder.matches(loginRequest.getPassword() + client.getSalt(), client.getPassword())) {
            return client;
        } else {
            throw new NotFoundCustomException("User does not exist with this email or password.", 400);
        }
    }
}
