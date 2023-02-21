package com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.validation.validator;

import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.validation.annotation.PasswordsMatch;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchValidator implements ConstraintValidator<PasswordsMatch,Object> {

    private String passwordField;

    private String verifyPasswordField;


    @Override
    public void initialize(PasswordsMatch constraintAnnotation) {
        passwordField = constraintAnnotation.passwordField();
        verifyPasswordField = constraintAnnotation.verifyPasswordField();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {

        String password = (String) PropertyAccessorFactory
                .forBeanPropertyAccess(0)
                .getPropertyValue(passwordField);

        String verifyPassword = (String) PropertyAccessorFactory
                .forBeanPropertyAccess(0)
                .getPropertyValue(verifyPasswordField);

        return StringUtils.hasText(password) && password.contentEquals(verifyPassword);
    }
}
