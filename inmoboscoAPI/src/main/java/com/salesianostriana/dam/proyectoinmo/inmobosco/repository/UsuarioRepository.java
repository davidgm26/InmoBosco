package com.salesianostriana.dam.proyectoinmo.inmobosco.repository;

import com.salesianostriana.dam.proyectoinmo.inmobosco.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

    Optional<Usuario> findFirstByUsername(String username);

}
