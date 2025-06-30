package com.renatoas.employeemanagementsystem.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe de tratamento global de exceções.
 * Utilizada para capturar e tratar exceções lançadas em toda a aplicação,
 * permitindo uma resposta consistente e personalizada para erros.
*/
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Método para tratar exceções de validação de argumentos.
     * Captura erros de validação e retorna uma resposta com os detalhes dos erros.
     *
     * @param ex Exceção de validação capturada.
     * @return ResponseEntity contendo os erros de validação e o status HTTP 400 Bad Request.
     */
  @ExceptionHandler(MethodArgumentNotValidException.class)

  //Recebe a exceção de validação de argumentos
  public ResponseEntity<Map<String, Object>> handleValidationErrors(MethodArgumentNotValidException ex) {
    // Cria um mapa para armazenar a resposta
    Map<String, Object> response = new HashMap<>();
    // Cria um mapa para armazenar os erros de campo
    Map<String, Map<String, String>> fieldErrors = new HashMap<>();

    // ex é a exceção que contém os erros de validação
    ex.getBindingResult().getFieldErrors().forEach(error -> {
    // Para cada erro de campo, cria um mapa com detalhes do erro
      Map<String, String> errorDetails = new HashMap<>();
        errorDetails.put("campo", error.getField());
        // Adiciona o valor rejeitado e a mensagem de erro ao mapa de detalhes
      errorDetails.put("rejeitado", String.valueOf(error.getRejectedValue()));
        errorDetails.put("mensagem", error.getDefaultMessage());
        errorDetails.put(error.getField(), errorDetails.toString());
        fieldErrors.put(error.getField(), errorDetails);
    });

    response.put("status", HttpStatus.BAD_REQUEST.value());
    response.put("errors", fieldErrors);

    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }


  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<Map<String, Object>> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
    Map<String, Object> response = new HashMap<>();

    String rootMessage = ex.getRootCause() != null ? ex.getRootCause().getMessage() : ex.getMessage();

    String valor = "desconhecido";

    if (rootMessage != null && rootMessage.contains("Duplicate entry")) {
      String[] partes = rootMessage.split("'");
      if (partes.length >= 4) {
        valor = partes[1];                       // o valor duplicado
        String constraint = partes[3];           // a chave única violada
      }
    }

    response.put("status", 409);
    response.put("erro", "Valor duplicado no banco de dados, o valor deve ser único, por favor, verifique os dados inseridos.");
    response.put("valor", valor);

    return new ResponseEntity<>(response, HttpStatus.CONFLICT);
  }
  //TODO: Verificar a necessidade de dividir o tratamento de exceções em classes separadas e implementar todas as exceções personalizadas necessárias para o sistema em uma interface de tratamento de exceções global

}
