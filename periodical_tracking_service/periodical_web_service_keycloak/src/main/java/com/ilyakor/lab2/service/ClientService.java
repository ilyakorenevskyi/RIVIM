package com.ilyakor.lab2.service;

import com.ilyakor.lab2.entity.Client;
import com.ilyakor.lab2.repository.ClientRepo;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.representations.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    private ClientRepo clientRepo;

    public Client getUser(String Authorization) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            if (authentication.getPrincipal() instanceof KeycloakPrincipal) {
                KeycloakPrincipal<KeycloakSecurityContext> kp =
                        (KeycloakPrincipal<KeycloakSecurityContext>) authentication.getPrincipal();
                AccessToken accessToken = kp.getKeycloakSecurityContext().getToken();
                String username = accessToken.getPreferredUsername();

                return findByUsername(username);
            }
        }

        return null;
    }

    private Client findByUsername(String username) {
        return clientRepo.findClientByUsername(username);
    }
}
