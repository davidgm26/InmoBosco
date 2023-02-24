package com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.controller;


import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.dto.*;
import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.exception.SameUserNameException;
import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.model.Usuario;
import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.security.jwt.access.JwtProvider;
import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UsuarioService usuarioService;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;


    @Operation(summary = "creación de un nuevo usuario")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CrearUsuarioRequest.class),
                    examples = {@ExampleObject(
                            value = """
                                        {
                                            "nombre":"David",
                                            "apellidos":"García María",
                                            "password":"12345678",
                                            "passwordRepeat":"12345678",
                                            "userName": "Martinex",
                                            "dni":"85995544X",
                                            "edad":21,
                                            "avatar":"https://robohash.org/81.36.52.170.png",\s
                                            "telefono":"648627905",
                                            "email": "vivaelsevilla@gmail.com"
                                        }
                                    """
                    )})}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha creado correctamente el nuevo usuario con rol de usuario",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CrearUsuarioResponse.class),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "id": "ac1d8001-8680-169d-8186-8017ac5f0000",
                                                "userName": "Martinex",
                                                "avatar": "https://robohash.org/81.36.52.170.png",
                                                "nombre": "David",
                                                "apellido": "García María",
                                                "role": "USER",
                                                "fechaCreacion": "2023-02-23"
                                            }
                                                                                        """
                            )})}),
            @ApiResponse(responseCode = "400",
                    description = "Ha habido algún error al intentar crear al nuevo usuario",
                    content = @Content)
    })
    @PostMapping("/auth/register")
    public ResponseEntity<CrearUsuarioResponse> crearUsuarioConRolUsuario(@Valid @RequestBody CrearUsuarioRequest crearUsuarioRequest) throws SameUserNameException {

        Usuario user = usuarioService.crearUsuarioUser(crearUsuarioRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(CrearUsuarioResponse.fromUsuario(user));

    }

    @Operation(summary = "creación de un nuevo usuario admin")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CrearUsuarioRequest.class),
                    examples = {@ExampleObject(
                            value = """
                                        {
                                            "nombre":"David",
                                            "apellidos":"García María",
                                            "password":"12345678",
                                            "passwordRepeat":"12345678",
                                            "userName": "Martinex",
                                            "dni":"85995544X",
                                            "edad":21,
                                            "avatar":"https://robohash.org/81.36.52.80.png",
                                            "telefono":"648627905",
                                            "email": "vivaelsevilla@gmail.com"
                                        }
                                    """
                    )})}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha creado correctamente el nuevo usuario con rol de administrador",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CrearUsuarioResponse.class),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "id": "ac1d8001-8680-169d-8186-8017ac5f0000",
                                                "userName": "Martinex",
                                                "avatar": "https://robohash.org/81.36.52.80.png",
                                                "nombre": "David",
                                                "apellido": "García María",
                                                "role": "Admin",
                                                "fechaCreacion": "2023-02-23"
                                            }
                                                                                        """
                            )})}),
            @ApiResponse(responseCode = "400",
                    description = "Ha habido algún error al intentar crear al nuevo usuario",
                    content = @Content)
    })
    @PostMapping("/auth/register/admin")
    public ResponseEntity<CrearUsuarioResponse> createUserWithAdminRole(@RequestBody CrearUsuarioRequest crearUsuarioRequest) throws SameUserNameException {
        Usuario user = usuarioService.crearUsuarioAdmin(crearUsuarioRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(CrearUsuarioResponse.fromUsuario(user));
    }


    @Operation(summary = "Endpoint para realizar el login de un usuario")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = LoginRequest.class),
                    examples = {@ExampleObject(
                            value = """
                                    {
                                        "username": "Martinex",
                                        "password": "12345678"
                                    }
                                    """
                    )})}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = JwtUserResponse.class),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                 "id": "ac1d8001-8680-169d-8186-8017ac5f0000",
                                                 "userName": "Martinex",
                                                 "avatar": "https://robohash.org/81.36.52.170.png",
                                                 "nombre": "David",
                                                 "apellido": "García María",
                                                 "fechaCreacion": "2023-02-23",
                                                 "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhYzFkODAwMS04NjgwLTE2OWQtODE4Ni04MDE3YWM1ZjAwMDAiLCJpYXQiOjE2NzcxODYyODgsImV4cCI6MTY3NzI3MjY4OH0.t6CNlftomn0jwfrqNRt4bWCNf4FOLF_exDzSFSgxuMKd4NvIOROtRlBWEwvurlV1bUgVI66TpLd36E8I9fTkxA"
                                             }
                                            """
                            )})}),
            @ApiResponse(responseCode = "401",
                    description = "Las credenciales que se han introducido son erróneas",
                    content = @Content)
    })
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

    @GetMapping("/me")
    public CrearUsuarioResponse profile(@AuthenticationPrincipal Usuario loggedUser) {
        return CrearUsuarioResponse.fromUsuario(loggedUser);
    }
}
