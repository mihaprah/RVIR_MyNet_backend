package com.project.mynet.services;

import com.project.mynet.dao.ClientRepository;
import com.project.mynet.exceptions.NotFoundCustomException;
import com.project.mynet.models.Client;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Collection;
import java.util.Objects;

@Service
@AllArgsConstructor
public class RegisterService {
    private ClientRepository clientDao;

    //    Add new Client
    public Client register(Client client) {
        String email = client.getEmail();
        String password = client.getPassword();

        Collection<Client> existingClients = clientDao.findAll();
        for (Client cl : existingClients) {
            if (Objects.equals(cl.getEmail(), email)) {
                throw new NotFoundCustomException("Client with this email already exists", 400);
            }
        }

        if (Objects.equals(client.getName(), "") ||
                Objects.equals(client.getLastname(), "") ||
                Objects.equals(client.getAddress(), "") ||
                Objects.equals(email, "") ||
                Objects.equals(password, "")){
            throw new NotFoundCustomException("All data must be provided.", 400);
        }
        if (password.length() < 8){
            throw new NotFoundCustomException("Password must be at least 8 charters long.", 400);
        }
        if (!Character.isUpperCase(password.charAt(0))){
            throw new NotFoundCustomException("Password must start with a capital letter.", 400);
        }
        String salt = generateSalt();
        String saltedPassword = password + salt;
        String hashedPassword = hashPassword(saltedPassword);

        client.setPassword(hashedPassword);

        return clientDao.save(client);
    }

    private String generateSalt() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] salt = new byte[16];
        secureRandom.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    private String hashPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
}
