package com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.error.controller;

import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.error.impl.ApiErrorImpl;
import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.error.impl.ApiValidationSubError;
import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.exception.EmptyInmuebleListException;
import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.exception.InmuebleNotFoundException;
import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.exception.SameUserNameException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalRestControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler({InmuebleNotFoundException.class, EmptyInmuebleListException.class})
    public ResponseEntity<?> handleNotFoundException(EntityNotFoundException exception, WebRequest request) {
        return buildApiError(exception, request, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler({SameUserNameException.class})
    public ResponseEntity<?> handleSameUsernameException(SameUserNameException exception, WebRequest request) {
        return buildApiError(exception, request, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return buildApiErrorWithSubErrors("Validation error.Please check something",request,status,ex.getAllErrors());
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return buildApiError(ex, request, status);
    }

    private final ResponseEntity<Object> buildApiError(Exception ex, WebRequest request, HttpStatus status) {
        return ResponseEntity.status(status)
                .body(ApiErrorImpl.builder()
                        .status(status)
                        .message(ex.getMessage())
                        .path(((ServletWebRequest) request).getRequest().getRequestURI())
                        .build());
    }

    private final ResponseEntity<Object> buildApiErrorWithSubErrors(String message, WebRequest request, HttpStatus status, List<ObjectError> subErrors) {
        return ResponseEntity.status(status)
                .body(
                        ApiErrorImpl.builder()
                                .status(status)
                                .message(message)
                                .path(((ServletWebRequest) request).getRequest().getRequestURI())
                                .subErrors(
                                        subErrors.stream()
                                                .map(ApiValidationSubError::fromObjectError)
                                                .collect(Collectors.toList()))
                                .build()
                );
    }

}
