package com.ufrn.pw.core.service;

import com.ufrn.pw.core.domain.Autor;
import com.ufrn.pw.core.domain.Livro;
import com.ufrn.pw.dto.LivroDTO;
import com.ufrn.pw.core.repository.AutorRepository;
import com.ufrn.pw.core.repository.EditoraRepository;
import com.ufrn.pw.core.repository.LivroRepository;
import com.ufrn.pw.core.repository.ResumoLivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.history.Revision;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private EditoraRepository editoraRepository;

    @Autowired
    private ResumoLivroRepository resumoLivroRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Transactional(readOnly = true)
    public Page<Livro> listarTodos(Pageable pageable) {
        return livroRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Page<Livro> buscarPorTitulo(String titulo, Pageable pageable) {
        return livroRepository.findByTituloContainingIgnoreCase(titulo, pageable);
    }

    @Transactional(readOnly = true)
    public List<Revision<Integer, Livro>> obterHistorico(Long id) {
        if (!livroRepository.existsById(id)) {
            throw new RuntimeException("Livro com ID " + id + " não encontrado.");
        }
        return livroRepository.findRevisions(id).getContent();
    }

    @Transactional
    public Livro criar(LivroDTO dto) {
        Livro livro = new Livro();
        livro.setTitulo(dto.getTitulo());
        livro.setIsbn(dto.getIsbn());
        livro.setAnoPublicacao(dto.getAnoPublicacao());

        if (dto.getEditoraId() != null) {
            livro.setEditora(editoraRepository.findById(dto.getEditoraId())
                .orElseThrow(() -> new RuntimeException("Editora não encontrada.")));
        }

        if (dto.getResumoId() != null) {
            livro.setResumoLivro(resumoLivroRepository.findById(dto.getResumoId())
                .orElseThrow(() -> new RuntimeException("Resumo não encontrado.")));
        }

        return livroRepository.save(livro);
    }

    @Transactional
    public Livro atualizar(Long id, LivroDTO dto) {
        Livro livro = livroRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Livro não encontrado."));

        livro.setTitulo(dto.getTitulo());
        livro.setIsbn(dto.getIsbn());
        livro.setAnoPublicacao(dto.getAnoPublicacao());

        if (dto.getEditoraId() != null) {
            livro.setEditora(editoraRepository.findById(dto.getEditoraId())
                .orElseThrow(() -> new RuntimeException("Editora não encontrada.")));
        }

        if (dto.getResumoId() != null) {
            livro.setResumoLivro(resumoLivroRepository.findById(dto.getResumoId())
                .orElseThrow(() -> new RuntimeException("Resumo não encontrado.")));
        }

        return livroRepository.save(livro);
    }

    @Transactional
    public void deletar(Long id) {
        if (!livroRepository.existsById(id)) {
            throw new RuntimeException("Livro não encontrado para deleção.");
        }
        livroRepository.deleteById(id);
    }

    @Transactional
    public void associarAutor(Long livroId, Long autorId) {
        Livro livro = livroRepository.findById(livroId)
            .orElseThrow(() -> new RuntimeException("Livro não encontrado."));
        Autor autor = autorRepository.findById(autorId)
            .orElseThrow(() -> new RuntimeException("Autor não encontrado."));

        if (!livro.getAutores().contains(autor)) {
            livro.getAutores().add(autor);
            livroRepository.save(livro);
        }
    }
}