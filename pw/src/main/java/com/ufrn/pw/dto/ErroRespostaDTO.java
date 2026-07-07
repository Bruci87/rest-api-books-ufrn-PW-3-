package com.ufrn.pw.dto;

import java.time.LocalDateTime;
import java.util.Map;

public class ErroRespostaDTO {
    private LocalDateTime timestamp;
    private Integer status;
    private String titulo;
    private Map<String, String> detalhes;

    public ErroRespostaDTO(Integer status, String titulo, Map<String, String> detalhes) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.titulo = titulo;
        this.detalhes = detalhes;
    }

    // Getters e Setters
    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public Map<String, String> getDetalhes() { return detalhes; }
    public void setDetalhes(Map<String, String> detalhes) { this.detalhes = detalhes; }
}