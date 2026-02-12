package com.example.authservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            // ❌ Désactiver CSRF pour API REST
            .csrf(csrf -> csrf.disable())

            // ❌ Pas de session (microservice stateless)
            .sessionManagement(session -> 
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )

            // 🔐 Configuration des autorisations
            .authorizeHttpRequests(auth -> auth
                // ✅ Endpoints publics
                .requestMatchers("/auth/register").permitAll()
                .requestMatchers("/auth/login").permitAll()
                .requestMatchers("/actuator/**").permitAll()
                .requestMatchers("/error").permitAll()

                // 🔒 Tout le reste sécurisé
                .anyRequest().authenticated()
            )

            // 🔑 Utiliser JWT de Keycloak (pas oauth2Login !)
            .oauth2ResourceServer(oauth2 -> 
                oauth2.jwt(Customizer.withDefaults())
            );

        return http.build();
    }
}
