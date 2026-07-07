package com.ufrn.pw.exception;

import com.ufrn.pw.dto.ErroRespostaDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroRespostaDTO> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> furosDeValidacao = new HashMap<>();
        
        ex.getBindingResult().getFieldErrors().forEach(error -> 
            furosDeValidacao.put(error.getField(), error.getDefaultMessage())
        );

        ErroRespostaDTO erro = new ErroRespostaDTO(
            HttpStatus.BAD_REQUEST.value(),
            "Erro de validação nos campos enviados.",
            furosDeValidacao
        );

        return ResponseEntity.badRequest().body(erro);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErroRespostaDTO> handleRuntime(RuntimeException ex) {
        Map<String, String> detalhes = new HashMap<>();
        detalhes.put("mensagem", ex.getMessage());

        ErroRespostaDTO erro = new ErroRespostaDTO(
            HttpStatus.NOT_FOUND.value(),
            "Recurso não encontrado ou operação inválida.",
            detalhes
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }
}