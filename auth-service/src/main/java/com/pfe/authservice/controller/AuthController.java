package com.pfe.authservice.controller;

import com.pfe.authservice.controller.RegisterRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        // TODO : Appeler ton service pour créer l'utilisateur
        System.out.println("Register request: " + request);
        return "Utilisateur créé !";  // simple test
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        // TODO : Authentification et génération JWT si tu veux
        return "Login OK !";
    }
}
