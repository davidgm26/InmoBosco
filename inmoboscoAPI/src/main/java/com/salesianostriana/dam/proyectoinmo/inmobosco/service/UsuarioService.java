package com.salesianostriana.dam.proyectoinmo.inmobosco.service;

import com.salesianostriana.dam.proyectoinmo.inmobosco.dto.CreateUsuarioRequest;
import com.salesianostriana.dam.proyectoinmo.inmobosco.model.UserRole;
import com.salesianostriana.dam.proyectoinmo.inmobosco.model.Usuario;
import com.salesianostriana.dam.proyectoinmo.inmobosco.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final PasswordEncoder passwordEncoder;

    private final UsuarioRepository usuarioRepository;

    public Usuario crearUsuario(CreateUsuarioRequest createUserRequest, EnumSet<UserRole> roles) {
        Usuario user = Usuario.builder()
                .nombre(createUserRequest.getNombre())
                .apellidos(createUserRequest.getApellidos())
                .dni(createUserRequest.getDni())
                .edad(createUserRequest.getEdad())
                .email(createUserRequest.getEmail())
                .avatar(createUserRequest.getAvatar())
                .fechaNacimiento(createUserRequest.getFechaNacimiento())
                .telefono(createUserRequest.getTelefono())
                .password(createUserRequest.getPassword())
                .username(createUserRequest.getUsername())
                .roles(roles)
                .build();
        return usuarioRepository.save(user);
    }

    public Usuario crearUsuarioUser(CreateUsuarioRequest createUsuarioRequest) {
        return crearUsuario(createUsuarioRequest, EnumSet.of(UserRole.USER));
    }

    public Usuario crearUsuarioAdmin(CreateUsuarioRequest createUsuarioRequest) {
        return crearUsuario(createUsuarioRequest, EnumSet.of(UserRole.ADMIN));
    }

    public List<Usuario> findAll(){
        return usuarioRepository.findAll();
    }
    public Optional<Usuario> findById(UUID id){
        return usuarioRepository.findById(id);
    }
    public Optional<Usuario> findByUsername(String username){
        return usuarioRepository.findFirstByUsername(username);
    }

    public Optional<Usuario> edit(Usuario user){
        return usuarioRepository.findById(user.getId())
                .map(u -> {
                    u.setAvatar(user.getAvatar());
                    u.setNombre(user.getNombre());
                    return usuarioRepository.save(u);
                }).or(() -> Optional.empty());
    }

    public Optional<Usuario> editPassword(UUID userId, String newPassword) {
        return usuarioRepository.findById(userId)
                .map(u->{
                    u.setPassword(passwordEncoder.encode(newPassword));
                    return usuarioRepository.save(u);
                }).or(()-> Optional.empty());
    }

    public void borrarUsuario(Usuario user){
        deleteById(user.getId());
    }

    public void deleteById(UUID id) {
        if (usuarioRepository.existsById(id))
            usuarioRepository.deleteById(id);
    }

    public boolean passwordMatch(Usuario user, String clearPassword) {
        return passwordEncoder.matches(clearPassword, user.getPassword());
    }
}
