package com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.validation.annotation;

import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PasswordsMatch {

    String message () default "Las contraseñas no coinciden";

    Class <?> [] groups() default {};
    Class <? extends Payload> [] payload() default {};

    String passwordField();
    String verifyPasswordField();
}
