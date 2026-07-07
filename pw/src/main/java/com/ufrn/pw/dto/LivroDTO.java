package com.ufrn.pw.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class LivroDTO {

    @NotBlank(message = "O título é obrigatório.")
    private String titulo;

    @NotBlank(message = "O ISBN é obrigatório.")
    @Pattern(regexp = "^(?:ISBN(?:-1[03])?:? )?(?=[0-9X]{10}$|(?=(?:[0-9]{3}-)?[0-9]{1,5}-)[0-9-]{13}$|(?=(?:[0-9]{3} )?[0-9]{1,5} )[0-9 ]{13}$)[0-9]{1,5}[- ]?[0-9]+[- ]?[0-9]+[- ]?[0-9X]$", 
             message = "O formato do ISBN informado é inválido.")
    private String isbn;

    @NotNull(message = "O ano de publicação é obrigatório.")
    private Integer anoPublicacao;

    private Long editoraId;
    private Long resumoId;

    // Getters e Setters
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
    public Integer getAnoPublicacao() { return anoPublicacao; }
    public void setAnoPublicacao(Integer anoPublicacao) { this.anoPublicacao = anoPublicacao; }
    public Long getEditoraId() { return editoraId; }
    public void setEditoraId(Long editoraId) { this.editoraId = editoraId; }
    public Long getResumoId() { return resumoId; }
    public void setResumoId(Long resumoId) { this.resumoId = resumoId; }
}