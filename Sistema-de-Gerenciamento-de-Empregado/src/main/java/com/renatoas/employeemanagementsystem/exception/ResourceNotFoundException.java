// Exceção personalizada para recursos não encontrados.
// Quando lançada, retorna o status HTTP 404 (NOT_FOUND).
package com.renatoas.employeemanagementsystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Anotação que define o status HTTP 404 para esta exceção.
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    // Construtor que recebe uma mensagem de erro personalizada e retorna a superclasse RuntimeException.
    public ResourceNotFoundException(String message) {
        super(message);
        System.out.println(message);
    }

}

