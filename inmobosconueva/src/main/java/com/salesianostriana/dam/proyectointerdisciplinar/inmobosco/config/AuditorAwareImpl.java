package com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.config;

import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.model.Usuario;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;
import java.util.UUID;

public class AuditorAwareImpl implements AuditorAware<String> {


    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return Optional.ofNullable(authentication)
                .map(auth -> (Usuario) auth.getPrincipal())
                .map(Usuario::getId)
                .map(UUID::toString);
    }
}
