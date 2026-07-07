package com.ufrn.pw.core.domain;

import jakarta.persistence.*;
import org.hibernate.envers.Audited;

@Entity
@Table(name = "resumo_livro")
@Audited
public class ResumoLivro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String texto;

    @OneToOne(mappedBy = "resumoLivro")
    private Livro livro;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTexto() { return texto; }
    public void setTexto(String texto) { this.texto = texto; }
    public Livro getLivro() { return livro; }
    public void setLivro(Livro livro) { this.livro = livro; }
}