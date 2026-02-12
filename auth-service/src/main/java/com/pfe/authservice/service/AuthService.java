package com.pfe.authservice.service;

import com.pfe.authservice.model.AppUser;
import com.pfe.authservice.repository.UserRepository;
import com.pfe.authservice.controller.RegisterRequest;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.Collections;

@Service
@Transactional
public class AuthService {

    private final UserRepository userRepository;
    private final Keycloak keycloak;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;

        // Connexion admin Keycloak
        this.keycloak = KeycloakBuilder.builder()
                .serverUrl("http://keycloak:8080")
                .realm("master")
                .username("admin")       // admin Keycloak
                .password("admin")       // mot de passe admin
                .clientId("admin-cli")
                .build();
    }

    public void register(RegisterRequest request) throws Exception {
        // 1️⃣ Vérifie si l'utilisateur existe déjà dans DB
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new Exception("Email already exists");
        }

        // 2️⃣ Crée l'utilisateur dans Keycloak
        UserRepresentation user = new UserRepresentation();
        user.setUsername(request.getEmail());
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEnabled(true);

        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue(request.getPassword());
        credential.setTemporary(false);

        user.setCredentials(Collections.singletonList(credential));

        keycloak.realm("PFE-Realm").users().create(user);

        // 3️⃣ Sauvegarde dans la base PostgreSQL
        AppUser appUser = new AppUser(request.getFirstName(), request.getLastName(), request.getEmail());
        userRepository.save(appUser);
    }
}
