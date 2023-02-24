package com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.validation.validator;

import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.service.UsuarioService;
import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.validation.annotation.UniqueUserName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUserNameValidator implements ConstraintValidator<UniqueUserName,String> {

    @Autowired
    private UsuarioService usuarioService;


    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return StringUtils.hasText(s) && usuarioService.findByUsername(s) != null;
    }
}
