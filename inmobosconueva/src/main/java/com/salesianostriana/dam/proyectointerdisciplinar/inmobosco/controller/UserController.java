package com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.controller;


import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.dto.CrearUsuarioResponse;
import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.dto.CrearUsuarioRequest;
import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.model.Usuario;
import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.security.jwt.access.JwtProvider;
import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UsuarioService usuarioService;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;


    @PostMapping("/auth/register")
    public ResponseEntity<CrearUsuarioResponse> crearUsuarioConRolUsuario(@RequestBody CrearUsuarioRequest crearUsuarioRequest){

        Usuario user = usuarioService.crearUsuarioUser(crearUsuarioRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(CrearUsuarioResponse.fromUsuario(user));

    }

    @PostMapping("/auth/register/admin")
    public ResponseEntity<CrearUsuarioResponse> createUserWithAdminRole(@RequestBody CrearUsuarioRequest crearUsuarioRequest) {
        Usuario user = usuarioService.crearUsuarioAdmin(crearUsuarioRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(CrearUsuarioResponse.fromUsuario(user));
    }

}
