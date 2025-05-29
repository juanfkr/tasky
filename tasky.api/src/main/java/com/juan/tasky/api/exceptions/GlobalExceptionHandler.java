package com.juan.tasky.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // mensagem para erros de recurso não encontrado
    @ExceptionHandler(TaskNotFound.class)
    public ResponseEntity<Object> handleTaskNotFound(TaskNotFound exception) {
        // body do JSON em HashMap para par de chave/valor.
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.NOT_FOUND.value());
        body.put("message", exception.getMessage());
        // retorno do corpo e erro do JSON.
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    // entidades já existentes
    @ExceptionHandler(TaskAlreadyExists.class)
    public ResponseEntity<Object> handleTaskAlreadyExists(TaskAlreadyExists exception) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.CONFLICT.value());
        body.put("message", exception.getMessage());
        return new ResponseEntity<>(body, HttpStatus.CONFLICT);
    }

    // nomes inválidos
    @ExceptionHandler(InvalidTaskName.class)
    public ResponseEntity<Object> handleInvalidTaskName(InvalidTaskName exception) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("message", exception.getMessage());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    // erros genéricos
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleTaskNotFound(Exception exception) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        body.put("message", exception.getMessage());
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
