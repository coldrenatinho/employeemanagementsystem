package com.renatoas.employeemanagementsystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Exceção personalizada para conflitos de recursos.
@ResponseStatus(value = HttpStatus.CONFLICT)
public class ResourceConflictException extends RuntimeException {

    // Construtor que recebe uma mensagem de erro personalizada e retorna a superclasse RuntimeException.
    public ResourceConflictException(String message) {
        super(message);
    }
}
