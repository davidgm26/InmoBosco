package com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.service;

import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.dto.CrearUsuarioRequest;
import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.exception.SameUserNameException;
import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.model.UserRole;
import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.model.Usuario;
import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final PasswordEncoder passwordEncoder;

    private final UsuarioRepository usuarioRepository;

    public Usuario crearUsuario(CrearUsuarioRequest createUserRequest, EnumSet<UserRole> roles) throws SameUserNameException {
        if (usuarioRepository.findFirstByUsername(createUserRequest.getUserName()).isEmpty()){
            Usuario user = Usuario.builder()
                    .nombre(createUserRequest.getNombre())
                    .apellidos(createUserRequest.getApellidos())
                    .dni(createUserRequest.getDni())
                    .edad(createUserRequest.getEdad())
                    .email(createUserRequest.getEmail())
                    .avatar(createUserRequest.getAvatar())
                    .fechaNacimiento(createUserRequest.getFechaNacimiento())
                    .telefono(createUserRequest.getTelefono())
                    .password(passwordEncoder.encode(createUserRequest.getPassword()))
                    .username(createUserRequest.getUserName())
                    .fechaCreacion(LocalDate.now())
                    .roles(roles)
                    .build();
            return usuarioRepository.save(user);
        }else{
            throw new SameUserNameException();
        }

    }

    public Usuario crearUsuarioUser(CrearUsuarioRequest crearUsuarioRequest) throws SameUserNameException {
        return crearUsuario(crearUsuarioRequest, EnumSet.of(UserRole.USER));
    }

    public Usuario crearUsuarioAdmin(CrearUsuarioRequest crearUsuarioRequest) throws SameUserNameException {
        return crearUsuario(crearUsuarioRequest, EnumSet.of(UserRole.ADMIN));
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
