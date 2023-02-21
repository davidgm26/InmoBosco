package com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.validation.annotation;

import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.validation.validator.PasswordMatchValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordMatchValidator.class)
@Documented
public @interface PasswordsMatch {

    String message () default "Las contraseñas no coinciden";

    Class <?> [] groups() default {};
    Class <? extends Payload> [] payload() default {};

    String passwordField();
    String verifyPasswordField();
}
