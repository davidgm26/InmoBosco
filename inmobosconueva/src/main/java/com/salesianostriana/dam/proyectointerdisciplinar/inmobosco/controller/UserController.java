package com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.controller;


import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.dto.*;
import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.exception.SameUserNameException;
import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.model.Usuario;
import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.security.jwt.access.JwtProvider;
import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UsuarioService usuarioService;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;


    @PostMapping("/auth/register")
    public ResponseEntity<CrearUsuarioResponse> crearUsuarioConRolUsuario(@RequestBody CrearUsuarioRequest crearUsuarioRequest) {

        Usuario user = usuarioService.crearUsuarioUser(crearUsuarioRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(CrearUsuarioResponse.fromUsuario(user));

    }

    @PostMapping("/auth/register/admin")
    public ResponseEntity<CrearUsuarioResponse> createUserWithAdminRole(@RequestBody CrearUsuarioRequest crearUsuarioRequest) throws SameUserNameException {
        Usuario user = usuarioService.crearUsuarioAdmin(crearUsuarioRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(CrearUsuarioResponse.fromUsuario(user));
    }

    @PostMapping("/auth/login")
    public ResponseEntity<JwtUserResponse> login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);

        Usuario user = (Usuario) authentication.getPrincipal();

        return ResponseEntity.status(HttpStatus.CREATED).body(JwtUserResponse.of(user, token));
    }

    @PutMapping("/user/changePassword")
    public ResponseEntity<CrearUsuarioResponse> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest,
                                                       @AuthenticationPrincipal Usuario loggedUser) {
    try {
            if (usuarioService.passwordMatch(loggedUser, changePasswordRequest.getOldPassword())) {
                Optional<Usuario> modified = usuarioService.editPassword(loggedUser.getId(), changePasswordRequest.getNewPassword());
                if (modified.isPresent())
                    return ResponseEntity.ok(CrearUsuarioResponse.fromUsuario(modified.get()));
            } else {
                 throw new RuntimeException();
            }
        } catch (RuntimeException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password Data Error");
        }

        return null;
    }
}
