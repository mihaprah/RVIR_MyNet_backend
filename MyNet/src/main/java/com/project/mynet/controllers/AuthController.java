package com.project.mynet.controllers;

import com.project.mynet.models.Client;
import com.project.mynet.models.LoginRequest;
import com.project.mynet.services.LoginService;
import com.project.mynet.services.RegisterService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private RegisterService registerService;
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<Client> login(@RequestBody LoginRequest loginRequest) {
        Client client = loginService.login(loginRequest);
        return ResponseEntity.ok(client);
    }

    @PostMapping("/register")
    public ResponseEntity<Client> register(@RequestBody Client client) {
        Client newClient = registerService.register(client);
        return ResponseEntity.ok(newClient);
    }

}
