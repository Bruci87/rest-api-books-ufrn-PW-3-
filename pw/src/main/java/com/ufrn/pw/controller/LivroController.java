package com.ufrn.pw.controller;

import com.ufrn.pw.core.domain.Livro;
import com.ufrn.pw.core.service.LivroService;
import com.ufrn.pw.dto.LivroDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.history.Revision;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/livros")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @GetMapping
    public ResponseEntity<Page<Livro>> listarTodos(Pageable pageable) {
        return ResponseEntity.ok(livroService.listarTodos(pageable));
    }

    @GetMapping("/busca")
    public ResponseEntity<Page<Livro>> buscarPorTitulo(@RequestParam String titulo, Pageable pageable) {
        return ResponseEntity.ok(livroService.buscarPorTitulo(titulo, pageable));
    }

    @GetMapping("/{id}/historico")
    public ResponseEntity<List<Revision<Integer, Livro>>> obterHistorico(@PathVariable Long id) {
        return ResponseEntity.ok(livroService.obterHistorico(id));
    }

    @PostMapping
    public ResponseEntity<Livro> criar(@Valid @RequestBody LivroDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(livroService.criar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livro> atualizar(@PathVariable Long id, @Valid @RequestBody LivroDTO dto) {
        return ResponseEntity.ok(livroService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        livroService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{livroId}/autores/{autorId}")
    public ResponseEntity<Void> associarAutor(@PathVariable Long livroId, @PathVariable Long autorId) {
        livroService.associarAutor(livroId, autorId);
        return ResponseEntity.noContent().build();
    }
}